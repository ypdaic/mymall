package com.ypdaic.mymall.coupon.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.ICouponSpuCategoryRelationService;
import com.ypdaic.mymall.coupon.vo.CouponSpuCategoryRelationDto;
import com.ypdaic.mymall.coupon.entity.CouponSpuCategoryRelation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import com.ypdaic.mymall.common.enums.FailureEnum;
import com.ypdaic.mymall.common.enums.NeedAuthEnum;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.result.Result;
import com.ypdaic.mymall.common.result.ResultUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.ypdaic.mymall.common.annotation.NeedAuth;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import java.util.Objects;

/**
 *
 * 优惠券分类关联 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/coupon-spu-category-relation")
public class CouponSpuCategoryRelationController extends BaseController {

    @Autowired
    ICouponSpuCategoryRelationService couponSpuCategoryRelationService;

    /**
     *
     * 新增优惠券分类关联
     * @param couponSpuCategoryRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<CouponSpuCategoryRelation> add(@RequestBody @Validated CouponSpuCategoryRelationDto couponSpuCategoryRelationDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        CouponSpuCategoryRelation couponSpuCategoryRelation = couponSpuCategoryRelationService.add(couponSpuCategoryRelationDto);

        return ResultUtil.successOfInsert(couponSpuCategoryRelation);
    }

    /**
     *
     * 修改优惠券分类关联
     * @param couponSpuCategoryRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<CouponSpuCategoryRelation> update(@RequestBody @Validated CouponSpuCategoryRelationDto couponSpuCategoryRelationDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        CouponSpuCategoryRelation couponSpuCategoryRelation = couponSpuCategoryRelationService.update(couponSpuCategoryRelationDto);
        if (couponSpuCategoryRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(couponSpuCategoryRelation);
    }

    /**
     *
     * 删除优惠券分类关联
     * @param couponSpuCategoryRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<CouponSpuCategoryRelation> delete(@RequestBody CouponSpuCategoryRelationDto couponSpuCategoryRelationDto, HttpServletRequest httpServletRequest) {
        CouponSpuCategoryRelation couponSpuCategoryRelation = couponSpuCategoryRelationService.delete(couponSpuCategoryRelationDto);
        if (couponSpuCategoryRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(couponSpuCategoryRelation);
    }

    /**
     *
     * 分页查询优惠券分类关联
     * @param couponSpuCategoryRelationDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<CouponSpuCategoryRelation>> queryPage(@RequestBody CouponSpuCategoryRelationDto couponSpuCategoryRelationDto) {
        Page<CouponSpuCategoryRelation> couponSpuCategoryRelationPage = new Page<>(couponSpuCategoryRelationDto.getPageIndex(), couponSpuCategoryRelationDto.getPageSize());
        IPage<CouponSpuCategoryRelation> page = couponSpuCategoryRelationService.queryPage(couponSpuCategoryRelationDto, couponSpuCategoryRelationPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询优惠券分类关联
     * @param couponSpuCategoryRelationDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<CouponSpuCategoryRelation> queryById(@RequestBody CouponSpuCategoryRelationDto couponSpuCategoryRelationDto) {
        CouponSpuCategoryRelation couponSpuCategoryRelation = couponSpuCategoryRelationService.getById(couponSpuCategoryRelationDto.getId());
        if (couponSpuCategoryRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(couponSpuCategoryRelation);
    }


    /**
     *
     * 查询所有优惠券分类关联
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<CouponSpuCategoryRelation>> queryAll(@RequestBody CouponSpuCategoryRelationDto couponSpuCategoryRelationDto) {
        List<CouponSpuCategoryRelation> list = couponSpuCategoryRelationService.queryAll(couponSpuCategoryRelationDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验优惠券分类关联名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody CouponSpuCategoryRelationDto couponSpuCategoryRelationDto) {

        if (Objects.isNull(couponSpuCategoryRelationDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (couponSpuCategoryRelationService.checkName(couponSpuCategoryRelationDto, couponSpuCategoryRelationDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "优惠券分类关联名称已存在！");
    }

}

