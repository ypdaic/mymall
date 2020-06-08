package com.ypdaic.mymall.order.service.impl;

import com.ypdaic.mymall.order.entity.PaymentInfo;
import com.ypdaic.mymall.order.mapper.PaymentInfoMapper;
import com.ypdaic.mymall.order.service.IPaymentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.order.vo.PaymentInfoDto;
import com.ypdaic.mymall.order.enums.PaymentInfoExcelHeadersEnum;
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
 * 支付信息表 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements IPaymentInfoService {


    /**
     * 新增支付信息表
     * @param paymentInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PaymentInfo add(PaymentInfoDto paymentInfoDto) {

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderSn(paymentInfoDto.getOrderSn());
        paymentInfo.setOrderId(paymentInfoDto.getOrderId());
        paymentInfo.setAlipayTradeNo(paymentInfoDto.getAlipayTradeNo());
        paymentInfo.setTotalAmount(paymentInfoDto.getTotalAmount());
        paymentInfo.setSubject(paymentInfoDto.getSubject());
        paymentInfo.setPaymentStatus(paymentInfoDto.getPaymentStatus());
        Date date6= new Date();
        paymentInfo.setCreateTime(date6);
        Date date7= new Date();
        paymentInfo.setConfirmTime(date7);
        paymentInfo.setCallbackContent(paymentInfoDto.getCallbackContent());
        Date date9= new Date();
        paymentInfo.setCallbackTime(date9);
        paymentInfo.insert();
        return paymentInfo;
    }

    /**
     * 更新支付信息表
     * @param paymentInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PaymentInfo update(PaymentInfoDto paymentInfoDto) {
        PaymentInfo paymentInfo = baseMapper.selectById(paymentInfoDto.getId());
        if (paymentInfo == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(paymentInfoDto.getOrderSn(), paymentInfo::setOrderSn);
        JavaUtils.INSTANCE.acceptIfNotNull(paymentInfoDto.getOrderId(), paymentInfo::setOrderId);
        JavaUtils.INSTANCE.acceptIfNotNull(paymentInfoDto.getAlipayTradeNo(), paymentInfo::setAlipayTradeNo);
        JavaUtils.INSTANCE.acceptIfNotNull(paymentInfoDto.getTotalAmount(), paymentInfo::setTotalAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(paymentInfoDto.getSubject(), paymentInfo::setSubject);
        JavaUtils.INSTANCE.acceptIfNotNull(paymentInfoDto.getPaymentStatus(), paymentInfo::setPaymentStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(paymentInfoDto.getConfirmTime(), paymentInfo::setConfirmTime);
        JavaUtils.INSTANCE.acceptIfNotNull(paymentInfoDto.getCallbackContent(), paymentInfo::setCallbackContent);
        JavaUtils.INSTANCE.acceptIfNotNull(paymentInfoDto.getCallbackTime(), paymentInfo::setCallbackTime);
        paymentInfo.updateById();
        return paymentInfo;
    }

    /**
     * 删除支付信息表
     * @param paymentInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PaymentInfo delete(PaymentInfoDto paymentInfoDto) {
        PaymentInfo paymentInfo = baseMapper.selectById(paymentInfoDto.getId());
        if (paymentInfo == null) {
            return null;
        }
        paymentInfo.deleteById();

        return paymentInfo;
    }

    /**
     * 分页查询支付信息表
     * @param paymentInfoDto
     * @param paymentInfoPage
     * @return
     */
    @Override
    public IPage<PaymentInfo> queryPage(PaymentInfoDto paymentInfoDto, Page<PaymentInfo> paymentInfoPage) {

        return baseMapper.queryPage(paymentInfoPage, paymentInfoDto);
    }


    /**
     * 校验支付信息表名称
     * @param paymentInfoDto
     * @return
     */
    @Override
    public boolean checkName(PaymentInfoDto paymentInfoDto, boolean isAdd) {

        QueryWrapper<PaymentInfo> paymentInfoQueryWrapper = new QueryWrapper<PaymentInfo>();
        paymentInfoQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        paymentInfoQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            paymentInfoQueryWrapper.ne("id", paymentInfoDto.getId());
        }

        Integer count = baseMapper.selectCount(paymentInfoQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有支付信息表
     * @return
     */
    public List<PaymentInfo> queryAll(PaymentInfoDto paymentInfoDto) {
        return baseMapper.queryAll(paymentInfoDto);
    }

}
