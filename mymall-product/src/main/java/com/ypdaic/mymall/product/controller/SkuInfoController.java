package com.ypdaic.mymall.product.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.ISkuInfoService;
import com.ypdaic.mymall.product.vo.SkuInfoDto;
import com.ypdaic.mymall.product.entity.SkuInfo;

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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * sku信息 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/skuinfo")
public class SkuInfoController extends BaseController {

    @Autowired
    ISkuInfoService skuInfoService;

    /**
     *
     * 新增sku信息
     * @param skuInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SkuInfo> add(@RequestBody @Validated SkuInfoDto skuInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SkuInfo skuInfo = skuInfoService.add(skuInfoDto);

        return ResultUtil.successOfInsert(skuInfo);
    }

    /**
     *
     * 修改sku信息
     * @param skuInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SkuInfo> update(@RequestBody @Validated SkuInfoDto skuInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SkuInfo skuInfo = skuInfoService.update(skuInfoDto);
        if (skuInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(skuInfo);
    }

    /**
     *
     * 删除sku信息
     * @param skuInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SkuInfo> delete(@RequestBody SkuInfoDto skuInfoDto, HttpServletRequest httpServletRequest) {
        SkuInfo skuInfo = skuInfoService.delete(skuInfoDto);
        if (skuInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(skuInfo);
    }

    /**
     *
     * 分页查询sku信息
     * @param skuInfoDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SkuInfo>> queryPage(@RequestBody SkuInfoDto skuInfoDto) {
        Page<SkuInfo> skuInfoPage = new Page<>(skuInfoDto.getPageIndex(), skuInfoDto.getPageSize());
        IPage<SkuInfo> page = skuInfoService.queryPage(skuInfoDto, skuInfoPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询sku信息
     * @param skuInfoDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SkuInfo> queryById(@RequestBody SkuInfoDto skuInfoDto) {
        SkuInfo skuInfo = skuInfoService.getById(skuInfoDto.getId());
        if (skuInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(skuInfo);
    }


    /**
     *
     * 查询所有sku信息
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SkuInfo>> queryAll(@RequestBody SkuInfoDto skuInfoDto) {
        List<SkuInfo> list = skuInfoService.queryAll(skuInfoDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验sku信息名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SkuInfoDto skuInfoDto) {

        if (Objects.isNull(skuInfoDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (skuInfoService.checkName(skuInfoDto, skuInfoDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "sku信息名称已存在！");
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:skuinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuInfoService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{skuId}")
    //@RequiresPermissions("product:skuinfo:info")
    public R info(@PathVariable("skuId") Long skuId){
        SkuInfo skuInfo = skuInfoService.getById(skuId);

        return R.ok().setData(skuInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:skuinfo:save")
    public R save(@RequestBody SkuInfo skuInfo){
        skuInfoService.save(skuInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:skuinfo:update")
    public R update(@RequestBody SkuInfo skuInfo){
        skuInfoService.updateById(skuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:skuinfo:delete")
    public R delete(@RequestBody Long[] skuIds){
        skuInfoService.removeByIds(Arrays.asList(skuIds));

        return R.ok();
    }

    @GetMapping("/{skuId}/price")
    public R getPrice(@PathVariable("skuId") Long skuId) {
        SkuInfo skuInfo = skuInfoService.getById(skuId);
        BigDecimal price = skuInfo.getPrice();
        return R.ok().setData(price);

    }

}

