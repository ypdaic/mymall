package com.ypdaic.mymall.order.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.order.entity.PaymentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.order.vo.PaymentInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 支付信息表 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IPaymentInfoService extends IService<PaymentInfo> {

    /**
     * 新增支付信息表
     * @param paymentInfoDto
     * @return
     */
    PaymentInfo add(PaymentInfoDto paymentInfoDto);

    /**
     * 更新支付信息表
     * @param paymentInfoDto
     * @return
     */
    PaymentInfo update(PaymentInfoDto paymentInfoDto);

    /**
     * 删除支付信息表
     * @param paymentInfoDto
     * @return
     */
    PaymentInfo delete(PaymentInfoDto paymentInfoDto);

    /**
     * 分页查询支付信息表
     * @param paymentInfoDto
     * @param paymentInfoPage
     * @return
     */
    IPage<PaymentInfo> queryPage(PaymentInfoDto paymentInfoDto, Page<PaymentInfo> paymentInfoPage);


    /**
     * 校验支付信息表名称
     * @param paymentInfoDto
     * @return
     */
    boolean checkName(PaymentInfoDto paymentInfoDto, boolean isAdd);

    /**
     *
     * 查询所有支付信息表
     * @return
     */
    List<PaymentInfo> queryAll(PaymentInfoDto paymentInfoDto);

    PageUtils queryPage(Map<String, Object> params);
}
