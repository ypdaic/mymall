package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.coupon.entity.CouponSpuRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.CouponSpuRelationDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 优惠券与产品关联 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ICouponSpuRelationService extends IService<CouponSpuRelation> {

    /**
     * 新增优惠券与产品关联
     * @param couponSpuRelationDto
     * @return
     */
    CouponSpuRelation add(CouponSpuRelationDto couponSpuRelationDto);

    /**
     * 更新优惠券与产品关联
     * @param couponSpuRelationDto
     * @return
     */
    CouponSpuRelation update(CouponSpuRelationDto couponSpuRelationDto);

    /**
     * 删除优惠券与产品关联
     * @param couponSpuRelationDto
     * @return
     */
    CouponSpuRelation delete(CouponSpuRelationDto couponSpuRelationDto);

    /**
     * 分页查询优惠券与产品关联
     * @param couponSpuRelationDto
     * @param couponSpuRelationPage
     * @return
     */
    IPage<CouponSpuRelation> queryPage(CouponSpuRelationDto couponSpuRelationDto, Page<CouponSpuRelation> couponSpuRelationPage);


    /**
     * 校验优惠券与产品关联名称
     * @param couponSpuRelationDto
     * @return
     */
    boolean checkName(CouponSpuRelationDto couponSpuRelationDto, boolean isAdd);

    /**
     *
     * 查询所有优惠券与产品关联
     * @return
     */
    List<CouponSpuRelation> queryAll(CouponSpuRelationDto couponSpuRelationDto);

    PageUtils queryPage(Map<String, Object> params);

}
