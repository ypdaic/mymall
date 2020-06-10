package com.ypdaic.mymall.product.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.ISkuImagesService;
import com.ypdaic.mymall.product.vo.SkuImagesDto;
import com.ypdaic.mymall.product.entity.SkuImages;

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
 * sku图片 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/sku-images")
public class SkuImagesController extends BaseController {

    @Autowired
    ISkuImagesService skuImagesService;

    /**
     *
     * 新增sku图片
     * @param skuImagesDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SkuImages> add(@RequestBody @Validated SkuImagesDto skuImagesDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SkuImages skuImages = skuImagesService.add(skuImagesDto);

        return ResultUtil.successOfInsert(skuImages);
    }

    /**
     *
     * 修改sku图片
     * @param skuImagesDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SkuImages> update(@RequestBody @Validated SkuImagesDto skuImagesDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SkuImages skuImages = skuImagesService.update(skuImagesDto);
        if (skuImages == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(skuImages);
    }

    /**
     *
     * 删除sku图片
     * @param skuImagesDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SkuImages> delete(@RequestBody SkuImagesDto skuImagesDto, HttpServletRequest httpServletRequest) {
        SkuImages skuImages = skuImagesService.delete(skuImagesDto);
        if (skuImages == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(skuImages);
    }

    /**
     *
     * 分页查询sku图片
     * @param skuImagesDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SkuImages>> queryPage(@RequestBody SkuImagesDto skuImagesDto) {
        Page<SkuImages> skuImagesPage = new Page<>(skuImagesDto.getPageIndex(), skuImagesDto.getPageSize());
        IPage<SkuImages> page = skuImagesService.queryPage(skuImagesDto, skuImagesPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询sku图片
     * @param skuImagesDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SkuImages> queryById(@RequestBody SkuImagesDto skuImagesDto) {
        SkuImages skuImages = skuImagesService.getById(skuImagesDto.getId());
        if (skuImages == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(skuImages);
    }


    /**
     *
     * 查询所有sku图片
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SkuImages>> queryAll(@RequestBody SkuImagesDto skuImagesDto) {
        List<SkuImages> list = skuImagesService.queryAll(skuImagesDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验sku图片名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SkuImagesDto skuImagesDto) {

        if (Objects.isNull(skuImagesDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (skuImagesService.checkName(skuImagesDto, skuImagesDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "sku图片名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:skuimages:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuImagesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:skuimages:info")
    public R info(@PathVariable("id") Long id){
        SkuImages skuImages = skuImagesService.getById(id);

        return R.ok().put("skuImages", skuImages);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:skuimages:save")
    public R save(@RequestBody SkuImages skuImages){
        skuImagesService.save(skuImages);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:skuimages:update")
    public R update(@RequestBody SkuImages skuImages){
        skuImagesService.updateById(skuImages);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:skuimages:delete")
    public R delete(@RequestBody Long[] ids){
        skuImagesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

