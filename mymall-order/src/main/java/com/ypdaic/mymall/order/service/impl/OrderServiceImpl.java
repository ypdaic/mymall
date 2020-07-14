package com.ypdaic.mymall.order.service.impl;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.to.MemberRespVo;
import com.ypdaic.mymall.common.to.SkuHasStockVo;
import com.ypdaic.mymall.common.to.WareSkuLockVo;
import com.ypdaic.mymall.common.to.mq.OrderTo;
import com.ypdaic.mymall.common.util.JavaUtils;
import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.fegin.cart.ICartFeginService;
import com.ypdaic.mymall.fegin.member.IMemberFeginService;
import com.ypdaic.mymall.fegin.product.IProductFeignService;
import com.ypdaic.mymall.fegin.ware.IWareFeignService;
import com.ypdaic.mymall.order.entity.Order;
import com.ypdaic.mymall.order.entity.OrderItem;
import com.ypdaic.mymall.order.enums.OrderStatusEnum;
import com.ypdaic.mymall.order.interceptor.LoginUserInterceptor;
import com.ypdaic.mymall.order.mapper.OrderMapper;
import com.ypdaic.mymall.order.service.IOrderItemService;
import com.ypdaic.mymall.order.service.IOrderService;
import com.ypdaic.mymall.order.vo.*;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.ypdaic.mymall.order.vo.OrderCreateTo;

import static org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    IMemberFeginService memberFeginService;

    @Autowired
    ICartFeginService cartFeginService;

    @Autowired
    @Qualifier(APPLICATION_TASK_EXECUTOR_BEAN_NAME)
    Executor executor;

    @Autowired
    IWareFeignService wareFeignService;


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("orderTokenCheckScript")
    RedisScript<Boolean> redisScript;

    private static final String TOKEN = "ORDER_TOKEN:%s";

    @Autowired
    IProductFeignService productFeignService;

    @Autowired
    IOrderItemService orderItemService;

    @Autowired
    RabbitTemplate rabbitTemplate;


    /**
     * 新增订单
     * @param orderDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Order add(OrderDto orderDto) {

        Order order = new Order();
        order.setMemberId(orderDto.getMemberId());
        order.setOrderSn(orderDto.getOrderSn());
        order.setCouponId(orderDto.getCouponId());
        Date date3= new Date();
        order.setCreateTime(date3);
        order.setMemberUsername(orderDto.getMemberUsername());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setPayAmount(orderDto.getPayAmount());
        order.setFreightAmount(orderDto.getFreightAmount());
        order.setPromotionAmount(orderDto.getPromotionAmount());
        order.setIntegrationAmount(orderDto.getIntegrationAmount());
        order.setCouponAmount(orderDto.getCouponAmount());
        order.setDiscountAmount(orderDto.getDiscountAmount());
        order.setPayType(orderDto.getPayType());
        order.setSourceType(orderDto.getSourceType());
        order.setStatus(orderDto.getStatus());
        order.setDeliveryCompany(orderDto.getDeliveryCompany());
        order.setDeliverySn(orderDto.getDeliverySn());
        order.setAutoConfirmDay(orderDto.getAutoConfirmDay());
        order.setIntegration(orderDto.getIntegration());
        order.setGrowth(orderDto.getGrowth());
        order.setBillType(orderDto.getBillType());
        order.setBillHeader(orderDto.getBillHeader());
        order.setBillContent(orderDto.getBillContent());
        order.setBillReceiverPhone(orderDto.getBillReceiverPhone());
        order.setBillReceiverEmail(orderDto.getBillReceiverEmail());
        order.setReceiverName(orderDto.getReceiverName());
        order.setReceiverPhone(orderDto.getReceiverPhone());
        order.setReceiverPostCode(orderDto.getReceiverPostCode());
        order.setReceiverProvince(orderDto.getReceiverProvince());
        order.setReceiverCity(orderDto.getReceiverCity());
        order.setReceiverRegion(orderDto.getReceiverRegion());
        order.setReceiverDetailAddress(orderDto.getReceiverDetailAddress());
        order.setNote(orderDto.getNote());
        order.setConfirmStatus(orderDto.getConfirmStatus());
        order.setDeleteStatus(orderDto.getDeleteStatus());
        order.setUseIntegration(orderDto.getUseIntegration());
        Date date36= new Date();
        order.setPaymentTime(date36);
        Date date37= new Date();
        order.setDeliveryTime(date37);
        Date date38= new Date();
        order.setReceiveTime(date38);
        Date date39= new Date();
        order.setCommentTime(date39);
        Date date40= new Date();
        order.setModifyTime(date40);
        order.insert();
        return order;
    }

    /**
     * 更新订单
     * @param orderDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Order update(OrderDto orderDto) {
        Order order = baseMapper.selectById(orderDto.getId());
        if (order == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getMemberId(), order::setMemberId);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getOrderSn(), order::setOrderSn);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getCouponId(), order::setCouponId);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getMemberUsername(), order::setMemberUsername);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getTotalAmount(), order::setTotalAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getPayAmount(), order::setPayAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getFreightAmount(), order::setFreightAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getPromotionAmount(), order::setPromotionAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getIntegrationAmount(), order::setIntegrationAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getCouponAmount(), order::setCouponAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getDiscountAmount(), order::setDiscountAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getPayType(), order::setPayType);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getSourceType(), order::setSourceType);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getStatus(), order::setStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getDeliveryCompany(), order::setDeliveryCompany);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getDeliverySn(), order::setDeliverySn);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getAutoConfirmDay(), order::setAutoConfirmDay);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getIntegration(), order::setIntegration);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getGrowth(), order::setGrowth);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getBillType(), order::setBillType);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getBillHeader(), order::setBillHeader);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getBillContent(), order::setBillContent);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getBillReceiverPhone(), order::setBillReceiverPhone);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getBillReceiverEmail(), order::setBillReceiverEmail);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getReceiverName(), order::setReceiverName);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getReceiverPhone(), order::setReceiverPhone);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getReceiverPostCode(), order::setReceiverPostCode);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getReceiverProvince(), order::setReceiverProvince);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getReceiverCity(), order::setReceiverCity);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getReceiverRegion(), order::setReceiverRegion);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getReceiverDetailAddress(), order::setReceiverDetailAddress);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getNote(), order::setNote);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getConfirmStatus(), order::setConfirmStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getDeleteStatus(), order::setDeleteStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getUseIntegration(), order::setUseIntegration);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getPaymentTime(), order::setPaymentTime);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getDeliveryTime(), order::setDeliveryTime);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getReceiveTime(), order::setReceiveTime);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getCommentTime(), order::setCommentTime);
        JavaUtils.INSTANCE.acceptIfNotNull(orderDto.getModifyTime(), order::setModifyTime);
        order.updateById();
        return order;
    }

    /**
     * 删除订单
     * @param orderDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Order delete(OrderDto orderDto) {
        Order order = baseMapper.selectById(orderDto.getId());
        if (order == null) {
            return null;
        }
        order.deleteById();

        return order;
    }

    /**
     * 分页查询订单
     * @param orderDto
     * @param orderPage
     * @return
     */
    @Override
    public IPage<Order> queryPage(OrderDto orderDto, Page<Order> orderPage) {

        return baseMapper.queryPage(orderPage, orderDto);
    }


    /**
     * 校验订单名称
     * @param orderDto
     * @return
     */
    @Override
    public boolean checkName(OrderDto orderDto, boolean isAdd) {

        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<Order>();
        orderQueryWrapper.eq("member_username", orderDto.getMemberUsername());
        orderQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        orderQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            orderQueryWrapper.ne("id", orderDto.getId());
        }

        Integer count = baseMapper.selectCount(orderQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有订单
     * @return
     */
    public List<Order> queryAll(OrderDto orderDto) {
        return baseMapper.queryAll(orderDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Order> page = this.page(
                new Query<Order>().getPage(params),
                new QueryWrapper<Order>()
        );

        return new PageUtils(page);
    }

    @Override
    public OrderConfirmVo confirmOrder() {
        MemberRespVo memberRespVo = LoginUserInterceptor.threadLocal.get();
        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        log.warn("当前线程id：{}, 当前线程名称：{}", Thread.currentThread().getId(), Thread.currentThread().getName());
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            try {
                RequestContextHolder.setRequestAttributes(requestAttributes);
                // 远程获取用户收货地址
                R address = memberFeginService.getAddress(memberRespVo.getId());
                List<MemberReceiveAddressVo> memberReceiveAddressVos = address.getData(new TypeReference<List<MemberReceiveAddressVo>>() {
                });
                orderConfirmVo.setMemberReceiveAddressVoList(memberReceiveAddressVos);
            } catch (Exception e) {
                log.error("获取收货地址异常", e);
                throw new RuntimeException("获取收货地址异常");
            }

        }, executor);

        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(() -> {
            try {
                RequestContextHolder.setRequestAttributes(requestAttributes);
                // 远程查询购物车所有选中的购物项
                R currentUserCartItems = cartFeginService.getCurrentUserCartItems();
                List<OrderItemVo> orderItemVo = currentUserCartItems.getData(new TypeReference<List<OrderItemVo>>() {
                });
                orderConfirmVo.setOrderItemVos(orderItemVo);
            } catch (Exception e) {
                log.error("获取购物车信息异常", e);
                throw new RuntimeException("获取购物车信息异常");
            }

        }, executor).thenRunAsync(() -> {
            log.warn("调用远程服务：当前线程id：{}, 当前线程名称：{}", Thread.currentThread().getId(), Thread.currentThread().getName());
            List<OrderItemVo> orderItemVos = orderConfirmVo.getOrderItemVos();
            if (CollectionUtils.isNotEmpty(orderItemVos)){
                List<Long> collect = orderItemVos.stream().map(orderItemVo -> orderItemVo.getSkuId()).collect(Collectors.toList());
                R skuHasStock = wareFeignService.getSkuHasStock(collect);
                List<SkuHasStockVo> data = skuHasStock.getData(new TypeReference<List<SkuHasStockVo>>() {
                });
                if (CollectionUtils.isNotEmpty(data)) {
                    for (OrderItemVo orderItemVo : orderItemVos) {
                        for (SkuHasStockVo datum : data) {
                            if (datum.getSkuId().equals(orderItemVo.getSkuId())) {
                                orderItemVo.setHasStock(datum.getHasStock());
                            }
                        }
                    }
                }

            }

        }, executor);



        Integer integration = memberRespVo.getIntegration();
        orderConfirmVo.setIntegration(integration);


        try {
            CompletableFuture.allOf(voidCompletableFuture, voidCompletableFuture1).get();
        } catch (Exception e) {
            log.error("调用远程服务异常", e);
            throw new RuntimeException("调用远程服务异常");
        }
        // TODO 防重令牌
        String uuid = UUID.fastUUID().toString();
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(String.format(TOKEN, memberRespVo.getId().toString()));
        boundValueOperations.set(uuid, 30, TimeUnit.MINUTES);
        orderConfirmVo.setOrderToken(uuid);
        return orderConfirmVo;
    }

    /**
     * 下单，这里使用seata 分布式事务不能保证高并发
     * @param orderSubmitVo
     * @return
     */
    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SubmitResponVo submitOrder(OrderSubmitVo orderSubmitVo) {
        SubmitResponVo submitResponVo = new SubmitResponVo();
        submitResponVo.setCode(0);
        MemberRespVo memberRespVo = LoginUserInterceptor.threadLocal.get();
        // 1.验证令牌
        Boolean result = (Boolean) redisTemplate.execute(redisScript, Collections.singletonList(String.format(TOKEN, memberRespVo.getId())), orderSubmitVo.getOrderToken());
        // 令牌不通过
        if (!result) {
            submitResponVo.setCode(1);
        } else {
            OrderCreateTo order = createOrder(orderSubmitVo);
            // 2.验价
            BigDecimal payAmount = order.getOrder().getPayAmount();
            BigDecimal payPrice = orderSubmitVo.getPayPrice();
            if (Math.abs(payAmount.subtract(payPrice).doubleValue()) < 0.01) {
                // 金额对比

                // 3.保存订单
                saveOrder(order);

                // 4.锁库存，订单号，所有订单项（skuid, skuname, num ）
                WareSkuLockVo wareSkuLockVo = new WareSkuLockVo();
                wareSkuLockVo.setOrderSn(order.getOrder().getOrderSn());
                List<com.ypdaic.mymall.common.to.OrderItemVo> collect = order.getOrderItemList().stream().map(orderItem -> {
                    com.ypdaic.mymall.common.to.OrderItemVo orderItemVo = new com.ypdaic.mymall.common.to.OrderItemVo();
                    orderItemVo.setSkuId(orderItem.getSkuId());
                    orderItemVo.setCount(orderItem.getSkuQuantity());
                    orderItemVo.setTitle(orderItem.getSkuName());
                    return orderItemVo;
                }).collect(Collectors.toList());
                wareSkuLockVo.setLocks(collect);
                // 远程锁定库存
                R r = wareFeignService.orderLockStock(wareSkuLockVo);
                if (r.getCode() == 0) {
                    //锁成功了
                    submitResponVo.setOrder(order.getOrder());
//                    throw new RuntimeException("模拟异常");
                } else {
                    throw new RuntimeException("库存锁定失败");
//                    // 锁定失败
//                    submitResponVo.setCode(3);
                }


            } else {
                // 金额对比失败
                submitResponVo.setCode(2);
            }
        }

        return submitResponVo;

    }

    @Override
    public Order getOrderStatus(String orderSn) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderSn(orderSn);
        List<Order> orders = queryAll(orderDto);
        if (CollectionUtils.isNotEmpty(orders)){
            return orders.get(0);
        }
        return null;
    }

    @Override
    public void closeOrder(Order order) {
        Order order1 = baseMapper.selectById(order.getId());
        // 查询订单当前状态
        if (order1.getStatus() == OrderStatusEnum.CREATE_NEW.getCode()) {
            order1.setStatus(OrderStatusEnum.CANCLED.getCode());
            saveOrUpdate(order1);
            OrderTo orderTo = new OrderTo();
            BeanUtils.copyProperties(order1, orderTo);
            rabbitTemplate.convertAndSend("order.event.exchange", "order.release.other", orderTo);
        }
    }

    @Override
    public PayVo getOrderPay(String orderSn) {
        Order order = this.getOrderStatus(orderSn);
        BigDecimal payAmount = order.getPayAmount();
        BigDecimal bigDecimal = payAmount.setScale(2, BigDecimal.ROUND_UP);
        PayVo payVo = new PayVo();
        payVo.setOut_trade_no(orderSn);

        payVo.setTotal_amount(bigDecimal.toString());

        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setOrderSn(orderSn);

        List<OrderItem> orderItems = orderItemService.queryAll(orderItemDto);
        OrderItem orderItem = orderItems.get(0);

        payVo.setSubject(orderItem.getSkuName());
        payVo.setBody(orderItem.getSkuAttrsVals());
        return payVo;
    }

    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void seataTest() {
        Order order = new Order();
        order.setOrderSn("test");
        order.insert();
        wareFeignService.seateTest();
        throw new RuntimeException();
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderCreateTo order) {
        order.getOrder().setModifyTime(new Date());
        baseMapper.insert(order.getOrder());
        // seata 0.7.1 还不支持批量插入
        List<OrderItem> orderItemList = order.getOrderItemList();
        orderItemList.forEach(orderItem -> {
            orderItem.insert();
        });

        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    rabbitTemplate.convertAndSend("order.event.exchange", "order.create.order", order.getOrder());
                }
            });

        }

    }


    private OrderCreateTo createOrder(OrderSubmitVo orderSubmitVo) {
        OrderCreateTo orderCreateTo = new OrderCreateTo();


        // 创建订单号
        String timeId = IdWorker.getTimeId();
        // 创建订单号
        Order order = buildOrder(orderSubmitVo, timeId);
        // 获取所有的订单项
        List<OrderItem> orderItems = buidOrderItems(timeId);


        // 计算价格相关
        computerPrice(order, orderItems);
        orderCreateTo.setOrder(order);
        orderCreateTo.setOrderItemList(orderItems);
        return orderCreateTo;


    }

    private void computerPrice(Order order, List<OrderItem> orderItems) {
        // 1,订单价格相关，叠加每一个订单项的总额信息
        BigDecimal total = new BigDecimal("0.0");
        BigDecimal coupon = new BigDecimal("0.0");
        BigDecimal integration = new BigDecimal("0.0");
        BigDecimal promotion = new BigDecimal("0.0");
        BigDecimal gift = new BigDecimal("0.0");
        BigDecimal growth = new BigDecimal("0.0");
        for (OrderItem orderItem : orderItems) {
            BigDecimal realAmount = orderItem.getRealAmount();
            total = total.add(realAmount);

            // 商品优惠信息
            coupon = coupon.add(orderItem.getCouponAmount());
            integration = integration.add(orderItem.getIntegrationAmount());
            promotion = promotion.add(orderItem.getPromotionAmount());
            gift = gift.add(new BigDecimal(orderItem.getGiftIntegration()));
            growth = growth.add(new BigDecimal(orderItem.getGiftGrowth()));

        }
        // 1,订单价格相关
        order.setTotalAmount(total);
        // 应付总额
        order.setPayAmount(total.add(order.getFreightAmount()));

        order.setPromotionAmount(promotion);
        order.setCouponAmount(coupon);
        order.setIntegrationAmount(integration);

        // 设置积分,成长值等信息
        order.setGrowth(growth.intValue());
        order.setIntegration(gift.intValue());
        // 设置删除状态
        order.setDeleteStatus(0);
    }

    private Order buildOrder(OrderSubmitVo orderSubmitVo, String timeId) {
        Order order = new Order();
        order.setOrderSn(timeId);
        MemberRespVo memberRespVo = LoginUserInterceptor.threadLocal.get();

        order.setMemberId(memberRespVo.getId());
        // 获取收货地址
        R fare = wareFeignService.getFare(orderSubmitVo.getAddrId());
        FareVo fareVo = fare.getData(new TypeReference<FareVo>() {
        });

        order.setFreightAmount(fareVo.getFare());
        order.setReceiverCity(fareVo.getMemberReceiveAddressVo().getCity());
        order.setReceiverDetailAddress(fareVo.getMemberReceiveAddressVo().getDetailAddress());
        order.setReceiverName(fareVo.getMemberReceiveAddressVo().getName());
        order.setReceiverPhone(fareVo.getMemberReceiveAddressVo().getPhone());
        order.setReceiverPostCode(fareVo.getMemberReceiveAddressVo().getPostCode());
        order.setReceiverProvince(fareVo.getMemberReceiveAddressVo().getProvince());
        order.setReceiverRegion(fareVo.getMemberReceiveAddressVo().getRegion());
        // 设置订单状态
        order.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
        order.setAutoConfirmDay(7);



        return order;
    }

    /**
     * 构建所有订单项数据
     * @return
     */
    private List<OrderItem> buidOrderItems(String timeId) {
        // 获取到所有的订单项
        R currentUserCartItems = cartFeginService.getCurrentUserCartItems();
        List<CartItem> cartItems = currentUserCartItems.getData(new TypeReference<List<CartItem>>(){});
        if (CollectionUtils.isNotEmpty(cartItems)) {
            List<OrderItem> collect = cartItems.stream().map(cartItem -> {

                OrderItem orderItem = buidOrderItem(cartItem);
                // 设置订单号
                orderItem.setOrderSn(timeId);
                return orderItem;
            }).collect(Collectors.toList());

            return collect;
    }

        return null;
    }

    private OrderItem buidOrderItem(CartItem cartItem) {
        OrderItem orderItem = new OrderItem();

        // 商品 sku信息
        orderItem.setSkuId(cartItem.getSkuId());
        orderItem.setSkuName(cartItem.getTitle());
        orderItem.setSkuPic(cartItem.getImage());
        orderItem.setSkuPrice(cartItem.getPrice());
        String atts = StringUtils.collectionToDelimitedString(cartItem.getSkuAttr(), ";");
        orderItem.setSkuAttrsVals(atts);

        // 优惠券信息
        orderItem.setSkuQuantity(cartItem.getCount());

        // 积分信息
        orderItem.setGiftGrowth(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())).intValue());
        orderItem.setGiftIntegration(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())).intValue());

        R spuInfoBySkuId = productFeignService.getSpuInfoBySkuId(cartItem.getSkuId());
        SpuInfoVo spuInfoVo = spuInfoBySkuId.getData(new TypeReference<SpuInfoVo>() {
        });

        // 添加spu信息
        orderItem.setSpuId(spuInfoVo.getId());
        orderItem.setSpuName(spuInfoVo.getSpuName());
        orderItem.setSpuBrand(spuInfoVo.getBrandId().toString());
        orderItem.setCategoryId(spuInfoVo.getCatalogId());
        // 订单项的价格信息
        orderItem.setPromotionAmount(new BigDecimal(0));
        orderItem.setCouponAmount(new BigDecimal(0));
        orderItem.setIntegrationAmount(new BigDecimal(0));
        // 当前订单的实际价格，减去优惠信息
        BigDecimal multiply = orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuQuantity()));
        BigDecimal subtract = multiply.subtract(orderItem.getCouponAmount()).subtract(orderItem.getPromotionAmount()).subtract(orderItem.getIntegrationAmount());
        orderItem.setRealAmount(subtract);


        return orderItem;
    }


}
