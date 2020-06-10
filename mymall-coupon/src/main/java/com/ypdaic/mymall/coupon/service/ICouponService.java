package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.coupon.entity.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.CouponDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ICouponService extends IService<Coupon> {

    /**
     * 新增优惠券信息
     * @param couponDto
     * @return
     */
    Coupon add(CouponDto couponDto);

    /**
     * 更新优惠券信息
     * @param couponDto
     * @return
     */
    Coupon update(CouponDto couponDto);

    /**
     * 删除优惠券信息
     * @param couponDto
     * @return
     */
    Coupon delete(CouponDto couponDto);

    /**
     * 分页查询优惠券信息
     * @param couponDto
     * @param couponPage
     * @return
     */
    IPage<Coupon> queryPage(CouponDto couponDto, Page<Coupon> couponPage);


    /**
     * 校验优惠券信息名称
     * @param couponDto
     * @return
     */
    boolean checkName(CouponDto couponDto, boolean isAdd);

    /**
     *
     * 查询所有优惠券信息
     * @return
     */
    List<Coupon> queryAll(CouponDto couponDto);

    PageUtils queryPage(Map<String, Object> params);
}
