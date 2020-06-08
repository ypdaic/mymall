package com.ypdaic.mymall.coupon.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.ICouponService;
import com.ypdaic.mymall.coupon.vo.CouponDto;
import com.ypdaic.mymall.coupon.entity.Coupon;

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
 * 优惠券信息 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/coupon")
public class CouponController extends BaseController {

    @Autowired
    ICouponService couponService;

    /**
     *
     * 新增优惠券信息
     * @param couponDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<Coupon> add(@RequestBody @Validated CouponDto couponDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        Coupon coupon = couponService.add(couponDto);

        return ResultUtil.successOfInsert(coupon);
    }

    /**
     *
     * 修改优惠券信息
     * @param couponDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<Coupon> update(@RequestBody @Validated CouponDto couponDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        Coupon coupon = couponService.update(couponDto);
        if (coupon == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(coupon);
    }

    /**
     *
     * 删除优惠券信息
     * @param couponDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<Coupon> delete(@RequestBody CouponDto couponDto, HttpServletRequest httpServletRequest) {
        Coupon coupon = couponService.delete(couponDto);
        if (coupon == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(coupon);
    }

    /**
     *
     * 分页查询优惠券信息
     * @param couponDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<Coupon>> queryPage(@RequestBody CouponDto couponDto) {
        Page<Coupon> couponPage = new Page<>(couponDto.getPageIndex(), couponDto.getPageSize());
        IPage<Coupon> page = couponService.queryPage(couponDto, couponPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询优惠券信息
     * @param couponDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<Coupon> queryById(@RequestBody CouponDto couponDto) {
        Coupon coupon = couponService.getById(couponDto.getId());
        if (coupon == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(coupon);
    }


    /**
     *
     * 查询所有优惠券信息
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<Coupon>> queryAll(@RequestBody CouponDto couponDto) {
        List<Coupon> list = couponService.queryAll(couponDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验优惠券信息名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody CouponDto couponDto) {

        if (Objects.isNull(couponDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (couponService.checkName(couponDto, couponDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "优惠券信息名称已存在！");
    }

}

