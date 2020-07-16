package com.ypdaic.mymall.order.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.order.entity.OrderItem;
import com.ypdaic.mymall.order.mapper.OrderItemMapper;
import com.ypdaic.mymall.order.service.IOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.order.vo.OrderItemDto;
import com.ypdaic.mymall.order.enums.OrderItemExcelHeadersEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypdaic.mymall.common.util.ExcelUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.util.JavaUtils;

/**
 * <p>
 * 订单项信息 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements IOrderItemService {


    /**
     * 新增订单项信息
     * @param orderItemDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderItem add(OrderItemDto orderItemDto) {

        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setOrderId(orderItemDto.getOrderId());
        orderItem.setOrderSn(orderItemDto.getOrderSn());
        orderItem.setSpuId(orderItemDto.getSpuId());
        orderItem.setSpuName(orderItemDto.getSpuName());
        orderItem.setSpuPic(orderItemDto.getSpuPic());
        orderItem.setSpuBrand(orderItemDto.getSpuBrand());
        orderItem.setCategoryId(orderItemDto.getCategoryId());
        orderItem.setSkuId(orderItemDto.getSkuId());
        orderItem.setSkuName(orderItemDto.getSkuName());
        orderItem.setSkuPic(orderItemDto.getSkuPic());
        orderItem.setSkuPrice(orderItemDto.getSkuPrice());
        orderItem.setSkuQuantity(orderItemDto.getSkuQuantity());
        orderItem.setSkuAttrsVals(orderItemDto.getSkuAttrsVals());
        orderItem.setPromotionAmount(orderItemDto.getPromotionAmount());
        orderItem.setCouponAmount(orderItemDto.getCouponAmount());
        orderItem.setIntegrationAmount(orderItemDto.getIntegrationAmount());
        orderItem.setRealAmount(orderItemDto.getRealAmount());
        orderItem.setGiftIntegration(orderItemDto.getGiftIntegration());
        orderItem.setGiftGrowth(orderItemDto.getGiftGrowth());
        orderItem.insert();
        return orderItem;
    }

    /**
     * 更新订单项信息
     * @param orderItemDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderItem update(OrderItemDto orderItemDto) {
        OrderItem orderItem = baseMapper.selectById(orderItemDto.getId());
        if (orderItem == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getOrderId(), orderItem::setOrderId);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getOrderSn(), orderItem::setOrderSn);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getSpuId(), orderItem::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getSpuName(), orderItem::setSpuName);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getSpuPic(), orderItem::setSpuPic);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getSpuBrand(), orderItem::setSpuBrand);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getCategoryId(), orderItem::setCategoryId);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getSkuId(), orderItem::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getSkuName(), orderItem::setSkuName);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getSkuPic(), orderItem::setSkuPic);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getSkuPrice(), orderItem::setSkuPrice);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getSkuQuantity(), orderItem::setSkuQuantity);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getSkuAttrsVals(), orderItem::setSkuAttrsVals);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getPromotionAmount(), orderItem::setPromotionAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getCouponAmount(), orderItem::setCouponAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getIntegrationAmount(), orderItem::setIntegrationAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getRealAmount(), orderItem::setRealAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getGiftIntegration(), orderItem::setGiftIntegration);
        JavaUtils.INSTANCE.acceptIfNotNull(orderItemDto.getGiftGrowth(), orderItem::setGiftGrowth);
        orderItem.updateById();
        return orderItem;
    }

    /**
     * 删除订单项信息
     * @param orderItemDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderItem delete(OrderItemDto orderItemDto) {
        OrderItem orderItem = baseMapper.selectById(orderItemDto.getId());
        if (orderItem == null) {
            return null;
        }
        orderItem.deleteById();

        return orderItem;
    }

    /**
     * 分页查询订单项信息
     * @param orderItemDto
     * @param orderItemPage
     * @return
     */
    @Override
    public IPage<OrderItem> queryPage(OrderItemDto orderItemDto, Page<OrderItem> orderItemPage) {

        return baseMapper.queryPage(orderItemPage, orderItemDto);
    }


    /**
     * 校验订单项信息名称
     * @param orderItemDto
     * @return
     */
    @Override
    public boolean checkName(OrderItemDto orderItemDto, boolean isAdd) {

        QueryWrapper<OrderItem> orderItemQueryWrapper = new QueryWrapper<OrderItem>();
        orderItemQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        orderItemQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            orderItemQueryWrapper.ne("id", orderItemDto.getId());
        }

        Integer count = baseMapper.selectCount(orderItemQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有订单项信息
     * @return
     */
    public List<OrderItem> queryAll(OrderItemDto orderItemDto) {
        return baseMapper.queryAll(orderItemDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItem> page = this.page(
                new Query<OrderItem>().getPage(params),
                new QueryWrapper<OrderItem>()
        );

        return new PageUtils(page);
    }

}
