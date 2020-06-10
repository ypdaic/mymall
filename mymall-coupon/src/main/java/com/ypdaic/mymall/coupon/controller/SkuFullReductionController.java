package com.ypdaic.mymall.coupon.controller;


import com.ypdaic.mymall.common.to.SkuReductionTo;
import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.coupon.entity.SkuLadder;
import com.ypdaic.mymall.coupon.service.IMemberPriceService;
import com.ypdaic.mymall.coupon.service.ISkuLadderService;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.ISkuFullReductionService;
import com.ypdaic.mymall.coupon.vo.SkuFullReductionDto;
import com.ypdaic.mymall.coupon.entity.SkuFullReduction;

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
 * 商品满减信息 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/skufullreduction")
public class SkuFullReductionController extends BaseController {

    @Autowired
    ISkuFullReductionService skuFullReductionService;

    /**
     *
     * 新增商品满减信息
     * @param skuFullReductionDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SkuFullReduction> add(@RequestBody @Validated SkuFullReductionDto skuFullReductionDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SkuFullReduction skuFullReduction = skuFullReductionService.add(skuFullReductionDto);

        return ResultUtil.successOfInsert(skuFullReduction);
    }

    /**
     *
     * 修改商品满减信息
     * @param skuFullReductionDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SkuFullReduction> update(@RequestBody @Validated SkuFullReductionDto skuFullReductionDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SkuFullReduction skuFullReduction = skuFullReductionService.update(skuFullReductionDto);
        if (skuFullReduction == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(skuFullReduction);
    }

    /**
     *
     * 删除商品满减信息
     * @param skuFullReductionDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SkuFullReduction> delete(@RequestBody SkuFullReductionDto skuFullReductionDto, HttpServletRequest httpServletRequest) {
        SkuFullReduction skuFullReduction = skuFullReductionService.delete(skuFullReductionDto);
        if (skuFullReduction == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(skuFullReduction);
    }

    /**
     *
     * 分页查询商品满减信息
     * @param skuFullReductionDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SkuFullReduction>> queryPage(@RequestBody SkuFullReductionDto skuFullReductionDto) {
        Page<SkuFullReduction> skuFullReductionPage = new Page<>(skuFullReductionDto.getPageIndex(), skuFullReductionDto.getPageSize());
        IPage<SkuFullReduction> page = skuFullReductionService.queryPage(skuFullReductionDto, skuFullReductionPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询商品满减信息
     * @param skuFullReductionDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SkuFullReduction> queryById(@RequestBody SkuFullReductionDto skuFullReductionDto) {
        SkuFullReduction skuFullReduction = skuFullReductionService.getById(skuFullReductionDto.getId());
        if (skuFullReduction == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(skuFullReduction);
    }


    /**
     *
     * 查询所有商品满减信息
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SkuFullReduction>> queryAll(@RequestBody SkuFullReductionDto skuFullReductionDto) {
        List<SkuFullReduction> list = skuFullReductionService.queryAll(skuFullReductionDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验商品满减信息名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SkuFullReductionDto skuFullReductionDto) {

        if (Objects.isNull(skuFullReductionDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (skuFullReductionService.checkName(skuFullReductionDto, skuFullReductionDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "商品满减信息名称已存在！");
    }

    @PostMapping("/saveinfo")
    public R saveInfo(@RequestBody SkuReductionTo reductionTo){

        skuFullReductionService.saveSkuReduction(reductionTo);
        return R.ok();
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:skufullreduction:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuFullReductionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:skufullreduction:info")
    public R info(@PathVariable("id") Long id){
        SkuFullReduction skuFullReduction = skuFullReductionService.getById(id);

        return R.ok().put("skuFullReduction", skuFullReduction);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:skufullreduction:save")
    public R save(@RequestBody SkuFullReduction skuFullReduction){
        skuFullReductionService.save(skuFullReduction);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:skufullreduction:update")
    public R update(@RequestBody SkuFullReduction skuFullReduction){
        skuFullReductionService.updateById(skuFullReduction);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:skufullreduction:delete")
    public R delete(@RequestBody Long[] ids){
        skuFullReductionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

