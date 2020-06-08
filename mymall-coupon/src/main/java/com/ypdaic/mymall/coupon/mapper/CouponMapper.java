package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.Coupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.CouponDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 优惠券信息 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface CouponMapper extends BaseMapper<Coupon> {

    /**
     * 分页查询优惠券信息
     * @param couponPage
     * @param couponDto
     * @return
     */
    IPage<Coupon> queryPage(Page<Coupon> couponPage, @Param("dto") CouponDto couponDto);

    /**
     * 导出查询数量
     * @param couponDto
     * @return
     */
    Integer queryCount(@Param("dto") CouponDto couponDto);

    /**
     * 导出分页查询优惠券信息
     * @param couponPage
     * @param couponDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> couponPage, @Param("dto") CouponDto couponDto);

    /**
     *
     * 查询所有优惠券信息
     * @return
     */
    List<Coupon> queryAll(@Param("dto") CouponDto couponDto);

}
