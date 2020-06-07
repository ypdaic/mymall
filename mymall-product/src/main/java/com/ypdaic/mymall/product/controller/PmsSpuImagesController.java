package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IPmsSpuImagesService;
import com.ypdaic.mymall.product.vo.PmsSpuImagesDto;
import com.ypdaic.mymall.product.entity.PmsSpuImages;

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
 * spu图片 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/product/pms-spu-images")
public class PmsSpuImagesController extends BaseController {

    @Autowired
    IPmsSpuImagesService pmsSpuImagesService;

    /**
     *
     * 新增spu图片
     * @param pmsSpuImagesDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PmsSpuImages> add(@RequestBody @Validated PmsSpuImagesDto pmsSpuImagesDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PmsSpuImages pmsSpuImages = pmsSpuImagesService.add(pmsSpuImagesDto);

        return ResultUtil.successOfInsert(pmsSpuImages);
    }

    /**
     *
     * 修改spu图片
     * @param pmsSpuImagesDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PmsSpuImages> update(@RequestBody @Validated PmsSpuImagesDto pmsSpuImagesDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PmsSpuImages pmsSpuImages = pmsSpuImagesService.update(pmsSpuImagesDto);
        if (pmsSpuImages == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(pmsSpuImages);
    }

    /**
     *
     * 删除spu图片
     * @param pmsSpuImagesDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PmsSpuImages> delete(@RequestBody PmsSpuImagesDto pmsSpuImagesDto, HttpServletRequest httpServletRequest) {
        PmsSpuImages pmsSpuImages = pmsSpuImagesService.delete(pmsSpuImagesDto);
        if (pmsSpuImages == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(pmsSpuImages);
    }

    /**
     *
     * 分页查询spu图片
     * @param pmsSpuImagesDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PmsSpuImages>> queryPage(@RequestBody PmsSpuImagesDto pmsSpuImagesDto) {
        Page<PmsSpuImages> pmsSpuImagesPage = new Page<>(pmsSpuImagesDto.getPageIndex(), pmsSpuImagesDto.getPageSize());
        IPage<PmsSpuImages> page = pmsSpuImagesService.queryPage(pmsSpuImagesDto, pmsSpuImagesPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询spu图片
     * @param pmsSpuImagesDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PmsSpuImages> queryById(@RequestBody PmsSpuImagesDto pmsSpuImagesDto) {
        PmsSpuImages pmsSpuImages = pmsSpuImagesService.getById(pmsSpuImagesDto.getId());
        if (pmsSpuImages == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(pmsSpuImages);
    }


    /**
     *
     * 查询所有spu图片
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PmsSpuImages>> queryAll(@RequestBody PmsSpuImagesDto pmsSpuImagesDto) {
        List<PmsSpuImages> list = pmsSpuImagesService.queryAll(pmsSpuImagesDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验spu图片名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PmsSpuImagesDto pmsSpuImagesDto) {

        if (Objects.isNull(pmsSpuImagesDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (pmsSpuImagesService.checkName(pmsSpuImagesDto, pmsSpuImagesDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "spu图片名称已存在！");
    }

}

