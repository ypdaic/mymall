package com.ypdaic.mymall.coupon.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.ISkuLadderService;
import com.ypdaic.mymall.coupon.vo.SkuLadderDto;
import com.ypdaic.mymall.coupon.entity.SkuLadder;

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
 * 商品阶梯价格 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/sku-ladder")
public class SkuLadderController extends BaseController {

    @Autowired
    ISkuLadderService skuLadderService;

    /**
     *
     * 新增商品阶梯价格
     * @param skuLadderDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SkuLadder> add(@RequestBody @Validated SkuLadderDto skuLadderDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SkuLadder skuLadder = skuLadderService.add(skuLadderDto);

        return ResultUtil.successOfInsert(skuLadder);
    }

    /**
     *
     * 修改商品阶梯价格
     * @param skuLadderDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SkuLadder> update(@RequestBody @Validated SkuLadderDto skuLadderDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SkuLadder skuLadder = skuLadderService.update(skuLadderDto);
        if (skuLadder == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(skuLadder);
    }

    /**
     *
     * 删除商品阶梯价格
     * @param skuLadderDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SkuLadder> delete(@RequestBody SkuLadderDto skuLadderDto, HttpServletRequest httpServletRequest) {
        SkuLadder skuLadder = skuLadderService.delete(skuLadderDto);
        if (skuLadder == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(skuLadder);
    }

    /**
     *
     * 分页查询商品阶梯价格
     * @param skuLadderDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SkuLadder>> queryPage(@RequestBody SkuLadderDto skuLadderDto) {
        Page<SkuLadder> skuLadderPage = new Page<>(skuLadderDto.getPageIndex(), skuLadderDto.getPageSize());
        IPage<SkuLadder> page = skuLadderService.queryPage(skuLadderDto, skuLadderPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询商品阶梯价格
     * @param skuLadderDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SkuLadder> queryById(@RequestBody SkuLadderDto skuLadderDto) {
        SkuLadder skuLadder = skuLadderService.getById(skuLadderDto.getId());
        if (skuLadder == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(skuLadder);
    }


    /**
     *
     * 查询所有商品阶梯价格
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SkuLadder>> queryAll(@RequestBody SkuLadderDto skuLadderDto) {
        List<SkuLadder> list = skuLadderService.queryAll(skuLadderDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验商品阶梯价格名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SkuLadderDto skuLadderDto) {

        if (Objects.isNull(skuLadderDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (skuLadderService.checkName(skuLadderDto, skuLadderDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "商品阶梯价格名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:skuladder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuLadderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:skuladder:info")
    public R info(@PathVariable("id") Long id){
        SkuLadder skuLadder = skuLadderService.getById(id);

        return R.ok().put("skuLadder", skuLadder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:skuladder:save")
    public R save(@RequestBody SkuLadder skuLadder){
        skuLadderService.save(skuLadder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:skuladder:update")
    public R update(@RequestBody SkuLadder skuLadder){
        skuLadderService.updateById(skuLadder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:skuladder:delete")
    public R delete(@RequestBody Long[] ids){
        skuLadderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}

