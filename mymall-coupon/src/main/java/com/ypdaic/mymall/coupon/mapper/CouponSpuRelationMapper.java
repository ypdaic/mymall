package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.CouponSpuRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.CouponSpuRelationDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 优惠券与产品关联 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface CouponSpuRelationMapper extends BaseMapper<CouponSpuRelation> {

    /**
     * 分页查询优惠券与产品关联
     * @param couponSpuRelationPage
     * @param couponSpuRelationDto
     * @return
     */
    IPage<CouponSpuRelation> queryPage(Page<CouponSpuRelation> couponSpuRelationPage, @Param("dto") CouponSpuRelationDto couponSpuRelationDto);

    /**
     * 导出查询数量
     * @param couponSpuRelationDto
     * @return
     */
    Integer queryCount(@Param("dto") CouponSpuRelationDto couponSpuRelationDto);

    /**
     * 导出分页查询优惠券与产品关联
     * @param couponSpuRelationPage
     * @param couponSpuRelationDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> couponSpuRelationPage, @Param("dto") CouponSpuRelationDto couponSpuRelationDto);

    /**
     *
     * 查询所有优惠券与产品关联
     * @return
     */
    List<CouponSpuRelation> queryAll(@Param("dto") CouponSpuRelationDto couponSpuRelationDto);

}
