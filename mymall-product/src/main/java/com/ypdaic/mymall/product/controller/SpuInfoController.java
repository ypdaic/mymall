package com.ypdaic.mymall.product.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.product.vo.SpuSaveVo;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.ISpuInfoService;
import com.ypdaic.mymall.product.vo.SpuInfoDto;
import com.ypdaic.mymall.product.entity.SpuInfo;

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
 * spu信息 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/spuinfo")
public class SpuInfoController extends BaseController {

    @Autowired
    ISpuInfoService spuInfoService;

    /**
     *
     * 新增spu信息
     * @param spuInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SpuInfo> add(@RequestBody @Validated SpuInfoDto spuInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SpuInfo spuInfo = spuInfoService.add(spuInfoDto);

        return ResultUtil.successOfInsert(spuInfo);
    }

    /**
     *
     * 修改spu信息
     * @param spuInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SpuInfo> update(@RequestBody @Validated SpuInfoDto spuInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SpuInfo spuInfo = spuInfoService.update(spuInfoDto);
        if (spuInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(spuInfo);
    }

    /**
     *
     * 删除spu信息
     * @param spuInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SpuInfo> delete(@RequestBody SpuInfoDto spuInfoDto, HttpServletRequest httpServletRequest) {
        SpuInfo spuInfo = spuInfoService.delete(spuInfoDto);
        if (spuInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(spuInfo);
    }

    /**
     *
     * 分页查询spu信息
     * @param spuInfoDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SpuInfo>> queryPage(@RequestBody SpuInfoDto spuInfoDto) {
        Page<SpuInfo> spuInfoPage = new Page<>(spuInfoDto.getPageIndex(), spuInfoDto.getPageSize());
        IPage<SpuInfo> page = spuInfoService.queryPage(spuInfoDto, spuInfoPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询spu信息
     * @param spuInfoDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SpuInfo> queryById(@RequestBody SpuInfoDto spuInfoDto) {
        SpuInfo spuInfo = spuInfoService.getById(spuInfoDto.getId());
        if (spuInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(spuInfo);
    }


    /**
     *
     * 查询所有spu信息
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SpuInfo>> queryAll(@RequestBody SpuInfoDto spuInfoDto) {
        List<SpuInfo> list = spuInfoService.queryAll(spuInfoDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验spu信息名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SpuInfoDto spuInfoDto) {

        if (Objects.isNull(spuInfoDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (spuInfoService.checkName(spuInfoDto, spuInfoDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "spu信息名称已存在！");
    }

    /**
     * 列表，查询spu信息
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:spuinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuInfoService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:spuinfo:info")
    public R info(@PathVariable("id") Long id){
        SpuInfo spuInfo = spuInfoService.getById(id);

        return R.ok().put("spuInfo", spuInfo);
    }

    /**
     * 商品上架功能：https://easydoc.xyz/doc/75716633/ZUqEdvA4/DhOtFr4A
     * @return
     */
    @PostMapping("{spuId}/up")
    public R spuUp(@PathVariable("spuId") Long spuId){
        spuInfoService.up(spuId);
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:spuinfo:save")
    public R save(@RequestBody SpuSaveVo vo){
        //spuInfoService.save(spuInfo);

        spuInfoService.saveSpuInfo(vo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:spuinfo:update")
    public R update(@RequestBody SpuInfo spuInfo){
        spuInfoService.updateById(spuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:spuinfo:delete")
    public R delete(@RequestBody Long[] ids){
        spuInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

