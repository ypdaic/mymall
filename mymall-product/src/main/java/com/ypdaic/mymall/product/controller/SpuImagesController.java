package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.ISpuImagesService;
import com.ypdaic.mymall.product.vo.SpuImagesDto;
import com.ypdaic.mymall.product.entity.SpuImages;

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
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/spu-images")
public class SpuImagesController extends BaseController {

    @Autowired
    ISpuImagesService spuImagesService;

    /**
     *
     * 新增spu图片
     * @param spuImagesDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SpuImages> add(@RequestBody @Validated SpuImagesDto spuImagesDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SpuImages spuImages = spuImagesService.add(spuImagesDto);

        return ResultUtil.successOfInsert(spuImages);
    }

    /**
     *
     * 修改spu图片
     * @param spuImagesDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SpuImages> update(@RequestBody @Validated SpuImagesDto spuImagesDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SpuImages spuImages = spuImagesService.update(spuImagesDto);
        if (spuImages == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(spuImages);
    }

    /**
     *
     * 删除spu图片
     * @param spuImagesDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SpuImages> delete(@RequestBody SpuImagesDto spuImagesDto, HttpServletRequest httpServletRequest) {
        SpuImages spuImages = spuImagesService.delete(spuImagesDto);
        if (spuImages == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(spuImages);
    }

    /**
     *
     * 分页查询spu图片
     * @param spuImagesDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SpuImages>> queryPage(@RequestBody SpuImagesDto spuImagesDto) {
        Page<SpuImages> spuImagesPage = new Page<>(spuImagesDto.getPageIndex(), spuImagesDto.getPageSize());
        IPage<SpuImages> page = spuImagesService.queryPage(spuImagesDto, spuImagesPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询spu图片
     * @param spuImagesDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SpuImages> queryById(@RequestBody SpuImagesDto spuImagesDto) {
        SpuImages spuImages = spuImagesService.getById(spuImagesDto.getId());
        if (spuImages == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(spuImages);
    }


    /**
     *
     * 查询所有spu图片
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SpuImages>> queryAll(@RequestBody SpuImagesDto spuImagesDto) {
        List<SpuImages> list = spuImagesService.queryAll(spuImagesDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验spu图片名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SpuImagesDto spuImagesDto) {

        if (Objects.isNull(spuImagesDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (spuImagesService.checkName(spuImagesDto, spuImagesDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "spu图片名称已存在！");
    }

}

