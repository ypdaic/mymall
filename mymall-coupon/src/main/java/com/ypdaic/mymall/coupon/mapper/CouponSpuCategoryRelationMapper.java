package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.CouponSpuCategoryRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.CouponSpuCategoryRelationDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 优惠券分类关联 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface CouponSpuCategoryRelationMapper extends BaseMapper<CouponSpuCategoryRelation> {

    /**
     * 分页查询优惠券分类关联
     * @param couponSpuCategoryRelationPage
     * @param couponSpuCategoryRelationDto
     * @return
     */
    IPage<CouponSpuCategoryRelation> queryPage(Page<CouponSpuCategoryRelation> couponSpuCategoryRelationPage, @Param("dto") CouponSpuCategoryRelationDto couponSpuCategoryRelationDto);

    /**
     * 导出查询数量
     * @param couponSpuCategoryRelationDto
     * @return
     */
    Integer queryCount(@Param("dto") CouponSpuCategoryRelationDto couponSpuCategoryRelationDto);

    /**
     * 导出分页查询优惠券分类关联
     * @param couponSpuCategoryRelationPage
     * @param couponSpuCategoryRelationDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> couponSpuCategoryRelationPage, @Param("dto") CouponSpuCategoryRelationDto couponSpuCategoryRelationDto);

    /**
     *
     * 查询所有优惠券分类关联
     * @return
     */
    List<CouponSpuCategoryRelation> queryAll(@Param("dto") CouponSpuCategoryRelationDto couponSpuCategoryRelationDto);

}
