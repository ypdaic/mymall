package com.ypdaic.mymall.order.service.impl;

import com.ypdaic.mymall.order.entity.Order;
import com.ypdaic.mymall.order.mapper.OrderMapper;
import com.ypdaic.mymall.order.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.order.vo.OrderDto;
import com.ypdaic.mymall.order.enums.OrderExcelHeadersEnum;
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
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {


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

}
