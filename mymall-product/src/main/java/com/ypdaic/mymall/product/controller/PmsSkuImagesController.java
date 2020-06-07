package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IPmsSkuImagesService;
import com.ypdaic.mymall.product.vo.PmsSkuImagesDto;
import com.ypdaic.mymall.product.entity.PmsSkuImages;

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
 * sku图片 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/product/pms-sku-images")
public class PmsSkuImagesController extends BaseController {

    @Autowired
    IPmsSkuImagesService pmsSkuImagesService;

    /**
     *
     * 新增sku图片
     * @param pmsSkuImagesDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PmsSkuImages> add(@RequestBody @Validated PmsSkuImagesDto pmsSkuImagesDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PmsSkuImages pmsSkuImages = pmsSkuImagesService.add(pmsSkuImagesDto);

        return ResultUtil.successOfInsert(pmsSkuImages);
    }

    /**
     *
     * 修改sku图片
     * @param pmsSkuImagesDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PmsSkuImages> update(@RequestBody @Validated PmsSkuImagesDto pmsSkuImagesDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PmsSkuImages pmsSkuImages = pmsSkuImagesService.update(pmsSkuImagesDto);
        if (pmsSkuImages == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(pmsSkuImages);
    }

    /**
     *
     * 删除sku图片
     * @param pmsSkuImagesDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PmsSkuImages> delete(@RequestBody PmsSkuImagesDto pmsSkuImagesDto, HttpServletRequest httpServletRequest) {
        PmsSkuImages pmsSkuImages = pmsSkuImagesService.delete(pmsSkuImagesDto);
        if (pmsSkuImages == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(pmsSkuImages);
    }

    /**
     *
     * 分页查询sku图片
     * @param pmsSkuImagesDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PmsSkuImages>> queryPage(@RequestBody PmsSkuImagesDto pmsSkuImagesDto) {
        Page<PmsSkuImages> pmsSkuImagesPage = new Page<>(pmsSkuImagesDto.getPageIndex(), pmsSkuImagesDto.getPageSize());
        IPage<PmsSkuImages> page = pmsSkuImagesService.queryPage(pmsSkuImagesDto, pmsSkuImagesPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询sku图片
     * @param pmsSkuImagesDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PmsSkuImages> queryById(@RequestBody PmsSkuImagesDto pmsSkuImagesDto) {
        PmsSkuImages pmsSkuImages = pmsSkuImagesService.getById(pmsSkuImagesDto.getId());
        if (pmsSkuImages == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(pmsSkuImages);
    }


    /**
     *
     * 查询所有sku图片
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PmsSkuImages>> queryAll(@RequestBody PmsSkuImagesDto pmsSkuImagesDto) {
        List<PmsSkuImages> list = pmsSkuImagesService.queryAll(pmsSkuImagesDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验sku图片名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PmsSkuImagesDto pmsSkuImagesDto) {

        if (Objects.isNull(pmsSkuImagesDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (pmsSkuImagesService.checkName(pmsSkuImagesDto, pmsSkuImagesDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "sku图片名称已存在！");
    }

}

