package com.ypdaic.mymall.coupon.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.ICouponSpuRelationService;
import com.ypdaic.mymall.coupon.vo.CouponSpuRelationDto;
import com.ypdaic.mymall.coupon.entity.CouponSpuRelation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * 优惠券与产品关联 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/coupon-spu-relation")
public class CouponSpuRelationController extends BaseController {

    @Autowired
    ICouponSpuRelationService couponSpuRelationService;

    /**
     *
     * 新增优惠券与产品关联
     * @param couponSpuRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<CouponSpuRelation> add(@RequestBody @Validated CouponSpuRelationDto couponSpuRelationDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        CouponSpuRelation couponSpuRelation = couponSpuRelationService.add(couponSpuRelationDto);

        return ResultUtil.successOfInsert(couponSpuRelation);
    }

    /**
     *
     * 修改优惠券与产品关联
     * @param couponSpuRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<CouponSpuRelation> update(@RequestBody @Validated CouponSpuRelationDto couponSpuRelationDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        CouponSpuRelation couponSpuRelation = couponSpuRelationService.update(couponSpuRelationDto);
        if (couponSpuRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(couponSpuRelation);
    }

    /**
     *
     * 删除优惠券与产品关联
     * @param couponSpuRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<CouponSpuRelation> delete(@RequestBody CouponSpuRelationDto couponSpuRelationDto, HttpServletRequest httpServletRequest) {
        CouponSpuRelation couponSpuRelation = couponSpuRelationService.delete(couponSpuRelationDto);
        if (couponSpuRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(couponSpuRelation);
    }

    /**
     *
     * 分页查询优惠券与产品关联
     * @param couponSpuRelationDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<CouponSpuRelation>> queryPage(@RequestBody CouponSpuRelationDto couponSpuRelationDto) {
        Page<CouponSpuRelation> couponSpuRelationPage = new Page<>(couponSpuRelationDto.getPageIndex(), couponSpuRelationDto.getPageSize());
        IPage<CouponSpuRelation> page = couponSpuRelationService.queryPage(couponSpuRelationDto, couponSpuRelationPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询优惠券与产品关联
     * @param couponSpuRelationDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<CouponSpuRelation> queryById(@RequestBody CouponSpuRelationDto couponSpuRelationDto) {
        CouponSpuRelation couponSpuRelation = couponSpuRelationService.getById(couponSpuRelationDto.getId());
        if (couponSpuRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(couponSpuRelation);
    }


    /**
     *
     * 查询所有优惠券与产品关联
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<CouponSpuRelation>> queryAll(@RequestBody CouponSpuRelationDto couponSpuRelationDto) {
        List<CouponSpuRelation> list = couponSpuRelationService.queryAll(couponSpuRelationDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验优惠券与产品关联名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody CouponSpuRelationDto couponSpuRelationDto) {

        if (Objects.isNull(couponSpuRelationDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (couponSpuRelationService.checkName(couponSpuRelationDto, couponSpuRelationDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "优惠券与产品关联名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:couponspurelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponSpuRelationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:couponspurelation:info")
    public R info(@PathVariable("id") Long id){
        CouponSpuRelation couponSpuRelation = couponSpuRelationService.getById(id);

        return R.ok().put("couponSpuRelation", couponSpuRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:couponspurelation:save")
    public R save(@RequestBody CouponSpuRelation couponSpuRelation){
        couponSpuRelationService.save(couponSpuRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:couponspurelation:update")
    public R update(@RequestBody CouponSpuRelation couponSpuRelation){
        couponSpuRelationService.updateById(couponSpuRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:couponspurelation:delete")
    public R delete(@RequestBody Long[] ids){
        couponSpuRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

