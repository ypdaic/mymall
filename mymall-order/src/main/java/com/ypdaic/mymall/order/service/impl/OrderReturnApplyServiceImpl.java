package com.ypdaic.mymall.order.service.impl;

import com.ypdaic.mymall.order.entity.OrderReturnApply;
import com.ypdaic.mymall.order.mapper.OrderReturnApplyMapper;
import com.ypdaic.mymall.order.service.IOrderReturnApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.order.vo.OrderReturnApplyDto;
import com.ypdaic.mymall.order.enums.OrderReturnApplyExcelHeadersEnum;
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

/**
 * <p>
 * 订单退货申请 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApply> implements IOrderReturnApplyService {


    /**
     * 新增订单退货申请
     * @param orderReturnApplyDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderReturnApply add(OrderReturnApplyDto orderReturnApplyDto) {

        OrderReturnApply orderReturnApply = new OrderReturnApply();
        orderReturnApply.setOrderId(orderReturnApplyDto.getOrderId());
        orderReturnApply.setSkuId(orderReturnApplyDto.getSkuId());
        orderReturnApply.setOrderSn(orderReturnApplyDto.getOrderSn());
        Date date3= new Date();
        orderReturnApply.setCreateTime(date3);
        orderReturnApply.setMemberUsername(orderReturnApplyDto.getMemberUsername());
        orderReturnApply.setReturnAmount(orderReturnApplyDto.getReturnAmount());
        orderReturnApply.setReturnName(orderReturnApplyDto.getReturnName());
        orderReturnApply.setReturnPhone(orderReturnApplyDto.getReturnPhone());
        orderReturnApply.setStatus(orderReturnApplyDto.getStatus());
        Date date9= new Date();
        orderReturnApply.setHandleTime(date9);
        orderReturnApply.setSkuImg(orderReturnApplyDto.getSkuImg());
        orderReturnApply.setSkuName(orderReturnApplyDto.getSkuName());
        orderReturnApply.setSkuBrand(orderReturnApplyDto.getSkuBrand());
        orderReturnApply.setSkuAttrsVals(orderReturnApplyDto.getSkuAttrsVals());
        orderReturnApply.setSkuCount(orderReturnApplyDto.getSkuCount());
        orderReturnApply.setSkuPrice(orderReturnApplyDto.getSkuPrice());
        orderReturnApply.setSkuRealPrice(orderReturnApplyDto.getSkuRealPrice());
        orderReturnApply.setReason(orderReturnApplyDto.getReason());
        orderReturnApply.setDescription述(orderReturnApplyDto.getDescription述());
        orderReturnApply.setDescPics(orderReturnApplyDto.getDescPics());
        orderReturnApply.setHandleNote(orderReturnApplyDto.getHandleNote());
        orderReturnApply.setHandleMan(orderReturnApplyDto.getHandleMan());
        orderReturnApply.setReceiveMan(orderReturnApplyDto.getReceiveMan());
        Date date23= new Date();
        orderReturnApply.setReceiveTime(date23);
        orderReturnApply.setReceiveNote(orderReturnApplyDto.getReceiveNote());
        orderReturnApply.setReceivePhone(orderReturnApplyDto.getReceivePhone());
        orderReturnApply.setCompanyAddress(orderReturnApplyDto.getCompanyAddress());
        orderReturnApply.insert();
        return orderReturnApply;
    }

    /**
     * 更新订单退货申请
     * @param orderReturnApplyDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderReturnApply update(OrderReturnApplyDto orderReturnApplyDto) {
        OrderReturnApply orderReturnApply = baseMapper.selectById(orderReturnApplyDto.getId());
        if (orderReturnApply == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getOrderId(), orderReturnApply::setOrderId);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getSkuId(), orderReturnApply::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getOrderSn(), orderReturnApply::setOrderSn);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getMemberUsername(), orderReturnApply::setMemberUsername);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getReturnAmount(), orderReturnApply::setReturnAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getReturnName(), orderReturnApply::setReturnName);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getReturnPhone(), orderReturnApply::setReturnPhone);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getStatus(), orderReturnApply::setStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getHandleTime(), orderReturnApply::setHandleTime);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getSkuImg(), orderReturnApply::setSkuImg);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getSkuName(), orderReturnApply::setSkuName);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getSkuBrand(), orderReturnApply::setSkuBrand);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getSkuAttrsVals(), orderReturnApply::setSkuAttrsVals);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getSkuCount(), orderReturnApply::setSkuCount);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getSkuPrice(), orderReturnApply::setSkuPrice);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getSkuRealPrice(), orderReturnApply::setSkuRealPrice);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getReason(), orderReturnApply::setReason);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getDescription述(), orderReturnApply::setDescription述);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getDescPics(), orderReturnApply::setDescPics);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getHandleNote(), orderReturnApply::setHandleNote);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getHandleMan(), orderReturnApply::setHandleMan);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getReceiveMan(), orderReturnApply::setReceiveMan);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getReceiveTime(), orderReturnApply::setReceiveTime);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getReceiveNote(), orderReturnApply::setReceiveNote);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getReceivePhone(), orderReturnApply::setReceivePhone);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnApplyDto.getCompanyAddress(), orderReturnApply::setCompanyAddress);
        orderReturnApply.updateById();
        return orderReturnApply;
    }

    /**
     * 删除订单退货申请
     * @param orderReturnApplyDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderReturnApply delete(OrderReturnApplyDto orderReturnApplyDto) {
        OrderReturnApply orderReturnApply = baseMapper.selectById(orderReturnApplyDto.getId());
        if (orderReturnApply == null) {
            return null;
        }
        orderReturnApply.deleteById();

        return orderReturnApply;
    }

    /**
     * 分页查询订单退货申请
     * @param orderReturnApplyDto
     * @param orderReturnApplyPage
     * @return
     */
    @Override
    public IPage<OrderReturnApply> queryPage(OrderReturnApplyDto orderReturnApplyDto, Page<OrderReturnApply> orderReturnApplyPage) {

        return baseMapper.queryPage(orderReturnApplyPage, orderReturnApplyDto);
    }


    /**
     * 校验订单退货申请名称
     * @param orderReturnApplyDto
     * @return
     */
    @Override
    public boolean checkName(OrderReturnApplyDto orderReturnApplyDto, boolean isAdd) {

        QueryWrapper<OrderReturnApply> orderReturnApplyQueryWrapper = new QueryWrapper<OrderReturnApply>();
        orderReturnApplyQueryWrapper.eq("member_username", orderReturnApplyDto.getMemberUsername());
        orderReturnApplyQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        orderReturnApplyQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            orderReturnApplyQueryWrapper.ne("id", orderReturnApplyDto.getId());
        }

        Integer count = baseMapper.selectCount(orderReturnApplyQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有订单退货申请
     * @return
     */
    public List<OrderReturnApply> queryAll(OrderReturnApplyDto orderReturnApplyDto) {
        return baseMapper.queryAll(orderReturnApplyDto);
    }

}
