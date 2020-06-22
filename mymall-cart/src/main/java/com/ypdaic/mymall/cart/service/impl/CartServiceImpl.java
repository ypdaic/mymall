package com.ypdaic.mymall.cart.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.ypdaic.mymall.cart.interceptor.CartInterceptor;
import com.ypdaic.mymall.cart.service.ICartService;
import com.ypdaic.mymall.cart.vo.Cart;
import com.ypdaic.mymall.cart.vo.CartItem;
import com.ypdaic.mymall.cart.vo.SkuInfoVo;
import com.ypdaic.mymall.cart.vo.UserInfo;
import com.ypdaic.mymall.common.constant.CartConstant;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.fegin.product.IProductFeignService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    RedisTemplate redisTemplate;

    private final String CART_PREFIX = "mymall:cart:%s";

    @Autowired
    IProductFeignService productFeignService;

    @Autowired
    Executor executor;

    @Override
    public CartItem addToCard(Long skuId, Integer num) {

        BoundHashOperations cartOps = getCartOps();

        Object res = cartOps.get(skuId.toString());
        if (Objects.isNull(res)) {
            // 2.将商品添加到购物车
            CartItem cartItem = new CartItem();
            CompletableFuture<Void> data1 = CompletableFuture.runAsync(() -> {
                try {
                    // 1.调用远程服务查询sku信息
                    R info = productFeignService.info(skuId);
                    SkuInfoVo data = info.getData("skuInfo", new TypeReference<SkuInfoVo>() {
                    });

                    cartItem.setCheck(true);
                    cartItem.setCount(1);
                    cartItem.setImage(data.getSkuDefaultImg());
                    cartItem.setTitle(data.getSkuTitle());
                    cartItem.setSkuId(data.getSkuId());
                    cartItem.setPrice(data.getPrice());
                } catch (Exception e) {
                    log.error("查询sku信息异常", e);
                    throw new RuntimeException("查询sku信息异常");
                }

            }, executor);

            CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
                try {
                    // 3.远程查询sku的组合信息
                    List<String> skuSaleAttrValues = productFeignService.getSkuSaleAttrValues(skuId);
                    cartItem.setSkuAttr(skuSaleAttrValues);
                } catch (Exception e) {
                    log.error("查询sku属性异常", e);
                    throw new RuntimeException("查询sku属性异常");
                }

            }, executor);

            try {
                // 等待异步任务完成
                CompletableFuture.allOf(data1, voidCompletableFuture).get();
            } catch (Exception e) {
                log.error("异步任务出错", e);
            }
            cartOps.put(skuId.toString(), cartItem);
            return cartItem;
        } else {
            // 更新数量
            CartItem cartItem = (CartItem) res;
            cartItem.setCount(cartItem.getCount() + num);
            cartOps.put(skuId.toString(), cartItem);
            return cartItem;
        }


    }

    @Override
    public CartItem getCartItem(Long skuId) {
        BoundHashOperations cartOps = getCartOps();
        Object o = cartOps.get(skuId.toString());
        return (CartItem) o;
    }

    @Override
    public Cart getCart() {
        // 1.登录
        UserInfo userInfo = CartInterceptor.threadLocal.get();
        Cart cart = new Cart();

        if (userInfo.getUserId() != null) {
            // 如果临时购物车没有合并，就合并
            String format = String.format(CART_PREFIX, userInfo.getUserKey());
            BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(format);
            List values = boundHashOperations.values();
            if (CollectionUtils.isNotEmpty(values)) {
                List<CartItem> tempCartItems = values;
                for (CartItem tempCartItem : tempCartItems) {
                    addToCard(tempCartItem.getSkuId(), tempCartItem.getCount());
                }
                // 清空临时购物车
                clearCart(format);
            }

            // 获取合并后的数据
            format = String.format(CART_PREFIX, userInfo.getUserId());
            boundHashOperations = redisTemplate.boundHashOps(format);
            values = boundHashOperations.values();
            cart.setItems(values);
        } else {
            // 临时购物车数据
            String userKey = userInfo.getUserKey();
            String format = String.format(CART_PREFIX, userKey);
            BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(format);
            List values = boundHashOperations.values();
            cart.setItems(values);
        }

        return cart;
    }

    /**
     * 勾选购物想
     * @param skuId
     * @param check
     */
    @Override
    public void checkItem(Long skuId, Integer check) {
        BoundHashOperations cartOps = getCartOps();
        CartItem cartItem = getCartItem(skuId);
        cartItem.setCheck(check == 1 ? true : false);
        cartOps.put(skuId.toString(), cartItem);
    }

    @Override
    public void countItem(Long skuId, Integer num) {
        BoundHashOperations cartOps = getCartOps();
        CartItem cartItem = getCartItem(skuId);
        cartItem.setCount(num);
        cartOps.put(skuId.toString(), cartItem);
    }

    /**
     * 查询购物项
     * @return
     */
    @Override
    public List<CartItem> getUserCartItems() {

        UserInfo userInfo = CartInterceptor.threadLocal.get();
        if (userInfo.getUserId() == null) {
            return null;
        } else {
            String format = String.format(CART_PREFIX, userInfo.getUserId().toString());
            BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(format);
            List<CartItem> values = boundHashOperations.values();
            // 只选中勾选中的商品
            List<CartItem> collect = values.stream().filter(cartItem -> cartItem.getCheck())
                    .map(cartItem -> {

                        R price = productFeignService.getPrice(cartItem.getSkuId());
                        BigDecimal data = price.getData(new TypeReference<BigDecimal>() {
                        });
                        // 获取最新的价格
                        cartItem.setPrice(data);
                        return cartItem;
                    })
                    .collect(Collectors.toList());

            return collect;
        }
    }

    /**
     * 获取我们要操作的购物车
     */
    private BoundHashOperations getCartOps() {
        UserInfo userInfo = CartInterceptor.threadLocal.get();

        String cartKey = "";

        //        1.
        if (userInfo.getUserId() != null) {
            cartKey = String.format(CART_PREFIX, userInfo.getUserId());
        } else {
            cartKey = String.format(CART_PREFIX, userInfo.getUserKey());
        }

        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(cartKey);
        return boundHashOperations;
    }

    public void clearCart(String cartKey) {
        redisTemplate.delete(cartKey);
    }

}
