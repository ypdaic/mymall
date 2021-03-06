package com.ypdaic.mymall.coupon.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.ICouponHistoryService;
import com.ypdaic.mymall.coupon.vo.CouponHistoryDto;
import com.ypdaic.mymall.coupon.entity.CouponHistory;

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
 * 优惠券领取历史记录 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/couponhistory")
public class CouponHistoryController extends BaseController {

    @Autowired
    ICouponHistoryService couponHistoryService;

    /**
     *
     * 新增优惠券领取历史记录
     * @param couponHistoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<CouponHistory> add(@RequestBody @Validated CouponHistoryDto couponHistoryDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        CouponHistory couponHistory = couponHistoryService.add(couponHistoryDto);

        return ResultUtil.successOfInsert(couponHistory);
    }

    /**
     *
     * 修改优惠券领取历史记录
     * @param couponHistoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<CouponHistory> update(@RequestBody @Validated CouponHistoryDto couponHistoryDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        CouponHistory couponHistory = couponHistoryService.update(couponHistoryDto);
        if (couponHistory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(couponHistory);
    }

    /**
     *
     * 删除优惠券领取历史记录
     * @param couponHistoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<CouponHistory> delete(@RequestBody CouponHistoryDto couponHistoryDto, HttpServletRequest httpServletRequest) {
        CouponHistory couponHistory = couponHistoryService.delete(couponHistoryDto);
        if (couponHistory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(couponHistory);
    }

    /**
     *
     * 分页查询优惠券领取历史记录
     * @param couponHistoryDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<CouponHistory>> queryPage(@RequestBody CouponHistoryDto couponHistoryDto) {
        Page<CouponHistory> couponHistoryPage = new Page<>(couponHistoryDto.getPageIndex(), couponHistoryDto.getPageSize());
        IPage<CouponHistory> page = couponHistoryService.queryPage(couponHistoryDto, couponHistoryPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询优惠券领取历史记录
     * @param couponHistoryDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<CouponHistory> queryById(@RequestBody CouponHistoryDto couponHistoryDto) {
        CouponHistory couponHistory = couponHistoryService.getById(couponHistoryDto.getId());
        if (couponHistory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(couponHistory);
    }


    /**
     *
     * 查询所有优惠券领取历史记录
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<CouponHistory>> queryAll(@RequestBody CouponHistoryDto couponHistoryDto) {
        List<CouponHistory> list = couponHistoryService.queryAll(couponHistoryDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验优惠券领取历史记录名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody CouponHistoryDto couponHistoryDto) {

        if (Objects.isNull(couponHistoryDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (couponHistoryService.checkName(couponHistoryDto, couponHistoryDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "优惠券领取历史记录名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:couponhistory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:couponhistory:info")
    public R info(@PathVariable("id") Long id){
        CouponHistory couponHistory = couponHistoryService.getById(id);

        return R.ok().put("couponHistory", couponHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:couponhistory:save")
    public R save(@RequestBody CouponHistory couponHistory){
        couponHistoryService.save(couponHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:couponhistory:update")
    public R update(@RequestBody CouponHistory couponHistory){
        couponHistoryService.updateById(couponHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:couponhistory:delete")
    public R delete(@RequestBody Long[] ids){
        couponHistoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

