package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.ISpuInfoDescService;
import com.ypdaic.mymall.product.vo.SpuInfoDescDto;
import com.ypdaic.mymall.product.entity.SpuInfoDesc;

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
 * spu信息介绍 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-10
 */
@RestController
@RequestMapping("/product/spu-info-desc")
public class SpuInfoDescController extends BaseController {

    @Autowired
    ISpuInfoDescService spuInfoDescService;

    /**
     *
     * 新增spu信息介绍
     * @param spuInfoDescDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SpuInfoDesc> add(@RequestBody @Validated SpuInfoDescDto spuInfoDescDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SpuInfoDesc spuInfoDesc = spuInfoDescService.add(spuInfoDescDto);

        return ResultUtil.successOfInsert(spuInfoDesc);
    }

    /**
     *
     * 修改spu信息介绍
     * @param spuInfoDescDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SpuInfoDesc> update(@RequestBody @Validated SpuInfoDescDto spuInfoDescDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SpuInfoDesc spuInfoDesc = spuInfoDescService.update(spuInfoDescDto);
        if (spuInfoDesc == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(spuInfoDesc);
    }

    /**
     *
     * 删除spu信息介绍
     * @param spuInfoDescDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SpuInfoDesc> delete(@RequestBody SpuInfoDescDto spuInfoDescDto, HttpServletRequest httpServletRequest) {
        SpuInfoDesc spuInfoDesc = spuInfoDescService.delete(spuInfoDescDto);
        if (spuInfoDesc == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(spuInfoDesc);
    }

    /**
     *
     * 分页查询spu信息介绍
     * @param spuInfoDescDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SpuInfoDesc>> queryPage(@RequestBody SpuInfoDescDto spuInfoDescDto) {
        Page<SpuInfoDesc> spuInfoDescPage = new Page<>(spuInfoDescDto.getPageIndex(), spuInfoDescDto.getPageSize());
        IPage<SpuInfoDesc> page = spuInfoDescService.queryPage(spuInfoDescDto, spuInfoDescPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询spu信息介绍
     * @param spuInfoDescDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SpuInfoDesc> queryById(@RequestBody SpuInfoDescDto spuInfoDescDto) {
        SpuInfoDesc spuInfoDesc = spuInfoDescService.getById(spuInfoDescDto.getId());
        if (spuInfoDesc == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(spuInfoDesc);
    }


    /**
     *
     * 查询所有spu信息介绍
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SpuInfoDesc>> queryAll(@RequestBody SpuInfoDescDto spuInfoDescDto) {
        List<SpuInfoDesc> list = spuInfoDescService.queryAll(spuInfoDescDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验spu信息介绍名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SpuInfoDescDto spuInfoDescDto) {

        if (Objects.isNull(spuInfoDescDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (spuInfoDescService.checkName(spuInfoDescDto, spuInfoDescDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "spu信息介绍名称已存在！");
    }

}

