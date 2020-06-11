package com.ypdaic.mymall.coupon.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.ISeckillPromotionService;
import com.ypdaic.mymall.coupon.vo.SeckillPromotionDto;
import com.ypdaic.mymall.coupon.entity.SeckillPromotion;

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
 * 秒杀活动 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/seckill-promotion")
public class SeckillPromotionController extends BaseController {

    @Autowired
    ISeckillPromotionService seckillPromotionService;

    /**
     *
     * 新增秒杀活动
     * @param seckillPromotionDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SeckillPromotion> add(@RequestBody @Validated SeckillPromotionDto seckillPromotionDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SeckillPromotion seckillPromotion = seckillPromotionService.add(seckillPromotionDto);

        return ResultUtil.successOfInsert(seckillPromotion);
    }

    /**
     *
     * 修改秒杀活动
     * @param seckillPromotionDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SeckillPromotion> update(@RequestBody @Validated SeckillPromotionDto seckillPromotionDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SeckillPromotion seckillPromotion = seckillPromotionService.update(seckillPromotionDto);
        if (seckillPromotion == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(seckillPromotion);
    }

    /**
     *
     * 删除秒杀活动
     * @param seckillPromotionDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SeckillPromotion> delete(@RequestBody SeckillPromotionDto seckillPromotionDto, HttpServletRequest httpServletRequest) {
        SeckillPromotion seckillPromotion = seckillPromotionService.delete(seckillPromotionDto);
        if (seckillPromotion == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(seckillPromotion);
    }

    /**
     *
     * 分页查询秒杀活动
     * @param seckillPromotionDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SeckillPromotion>> queryPage(@RequestBody SeckillPromotionDto seckillPromotionDto) {
        Page<SeckillPromotion> seckillPromotionPage = new Page<>(seckillPromotionDto.getPageIndex(), seckillPromotionDto.getPageSize());
        IPage<SeckillPromotion> page = seckillPromotionService.queryPage(seckillPromotionDto, seckillPromotionPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询秒杀活动
     * @param seckillPromotionDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SeckillPromotion> queryById(@RequestBody SeckillPromotionDto seckillPromotionDto) {
        SeckillPromotion seckillPromotion = seckillPromotionService.getById(seckillPromotionDto.getId());
        if (seckillPromotion == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(seckillPromotion);
    }


    /**
     *
     * 查询所有秒杀活动
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SeckillPromotion>> queryAll(@RequestBody SeckillPromotionDto seckillPromotionDto) {
        List<SeckillPromotion> list = seckillPromotionService.queryAll(seckillPromotionDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验秒杀活动名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SeckillPromotionDto seckillPromotionDto) {

        if (Objects.isNull(seckillPromotionDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (seckillPromotionService.checkName(seckillPromotionDto, seckillPromotionDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "秒杀活动名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:seckillpromotion:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillPromotionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:seckillpromotion:info")
    public R info(@PathVariable("id") Long id){
        SeckillPromotion seckillPromotion = seckillPromotionService.getById(id);

        return R.ok().put("seckillPromotion", seckillPromotion);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:seckillpromotion:save")
    public R save(@RequestBody SeckillPromotion seckillPromotion){
        seckillPromotionService.save(seckillPromotion);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:seckillpromotion:update")
    public R update(@RequestBody SeckillPromotion seckillPromotion){
        seckillPromotionService.updateById(seckillPromotion);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:seckillpromotion:delete")
    public R delete(@RequestBody Long[] ids){
        seckillPromotionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

