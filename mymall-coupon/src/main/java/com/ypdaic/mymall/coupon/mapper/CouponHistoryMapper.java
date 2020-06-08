package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.CouponHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.CouponHistoryDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 优惠券领取历史记录 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface CouponHistoryMapper extends BaseMapper<CouponHistory> {

    /**
     * 分页查询优惠券领取历史记录
     * @param couponHistoryPage
     * @param couponHistoryDto
     * @return
     */
    IPage<CouponHistory> queryPage(Page<CouponHistory> couponHistoryPage, @Param("dto") CouponHistoryDto couponHistoryDto);

    /**
     * 导出查询数量
     * @param couponHistoryDto
     * @return
     */
    Integer queryCount(@Param("dto") CouponHistoryDto couponHistoryDto);

    /**
     * 导出分页查询优惠券领取历史记录
     * @param couponHistoryPage
     * @param couponHistoryDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> couponHistoryPage, @Param("dto") CouponHistoryDto couponHistoryDto);

    /**
     *
     * 查询所有优惠券领取历史记录
     * @return
     */
    List<CouponHistory> queryAll(@Param("dto") CouponHistoryDto couponHistoryDto);

}
