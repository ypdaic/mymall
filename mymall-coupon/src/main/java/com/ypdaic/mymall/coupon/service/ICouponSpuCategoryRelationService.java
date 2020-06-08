package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.coupon.entity.CouponSpuCategoryRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.CouponSpuCategoryRelationDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 优惠券分类关联 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ICouponSpuCategoryRelationService extends IService<CouponSpuCategoryRelation> {

    /**
     * 新增优惠券分类关联
     * @param couponSpuCategoryRelationDto
     * @return
     */
    CouponSpuCategoryRelation add(CouponSpuCategoryRelationDto couponSpuCategoryRelationDto);

    /**
     * 更新优惠券分类关联
     * @param couponSpuCategoryRelationDto
     * @return
     */
    CouponSpuCategoryRelation update(CouponSpuCategoryRelationDto couponSpuCategoryRelationDto);

    /**
     * 删除优惠券分类关联
     * @param couponSpuCategoryRelationDto
     * @return
     */
    CouponSpuCategoryRelation delete(CouponSpuCategoryRelationDto couponSpuCategoryRelationDto);

    /**
     * 分页查询优惠券分类关联
     * @param couponSpuCategoryRelationDto
     * @param couponSpuCategoryRelationPage
     * @return
     */
    IPage<CouponSpuCategoryRelation> queryPage(CouponSpuCategoryRelationDto couponSpuCategoryRelationDto, Page<CouponSpuCategoryRelation> couponSpuCategoryRelationPage);


    /**
     * 校验优惠券分类关联名称
     * @param couponSpuCategoryRelationDto
     * @return
     */
    boolean checkName(CouponSpuCategoryRelationDto couponSpuCategoryRelationDto, boolean isAdd);

    /**
     *
     * 查询所有优惠券分类关联
     * @return
     */
    List<CouponSpuCategoryRelation> queryAll(CouponSpuCategoryRelationDto couponSpuCategoryRelationDto);
}
