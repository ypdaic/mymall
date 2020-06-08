package com.ypdaic.mymall.order.mapper;

import com.ypdaic.mymall.order.entity.PaymentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.order.vo.PaymentInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 支付信息表 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface PaymentInfoMapper extends BaseMapper<PaymentInfo> {

    /**
     * 分页查询支付信息表
     * @param paymentInfoPage
     * @param paymentInfoDto
     * @return
     */
    IPage<PaymentInfo> queryPage(Page<PaymentInfo> paymentInfoPage, @Param("dto") PaymentInfoDto paymentInfoDto);

    /**
     * 导出查询数量
     * @param paymentInfoDto
     * @return
     */
    Integer queryCount(@Param("dto") PaymentInfoDto paymentInfoDto);

    /**
     * 导出分页查询支付信息表
     * @param paymentInfoPage
     * @param paymentInfoDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> paymentInfoPage, @Param("dto") PaymentInfoDto paymentInfoDto);

    /**
     *
     * 查询所有支付信息表
     * @return
     */
    List<PaymentInfo> queryAll(@Param("dto") PaymentInfoDto paymentInfoDto);

}
