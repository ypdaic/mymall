package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.coupon.entity.CouponHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.CouponHistoryDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 优惠券领取历史记录 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ICouponHistoryService extends IService<CouponHistory> {

    /**
     * 新增优惠券领取历史记录
     * @param couponHistoryDto
     * @return
     */
    CouponHistory add(CouponHistoryDto couponHistoryDto);

    /**
     * 更新优惠券领取历史记录
     * @param couponHistoryDto
     * @return
     */
    CouponHistory update(CouponHistoryDto couponHistoryDto);

    /**
     * 删除优惠券领取历史记录
     * @param couponHistoryDto
     * @return
     */
    CouponHistory delete(CouponHistoryDto couponHistoryDto);

    /**
     * 分页查询优惠券领取历史记录
     * @param couponHistoryDto
     * @param couponHistoryPage
     * @return
     */
    IPage<CouponHistory> queryPage(CouponHistoryDto couponHistoryDto, Page<CouponHistory> couponHistoryPage);


    /**
     * 校验优惠券领取历史记录名称
     * @param couponHistoryDto
     * @return
     */
    boolean checkName(CouponHistoryDto couponHistoryDto, boolean isAdd);

    /**
     *
     * 查询所有优惠券领取历史记录
     * @return
     */
    List<CouponHistory> queryAll(CouponHistoryDto couponHistoryDto);
}
