package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IPmsProductAttrValueService;
import com.ypdaic.mymall.product.vo.PmsProductAttrValueDto;
import com.ypdaic.mymall.product.entity.PmsProductAttrValue;

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
 * spu属性值 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/product/pms-product-attr-value")
public class PmsProductAttrValueController extends BaseController {

    @Autowired
    IPmsProductAttrValueService pmsProductAttrValueService;

    /**
     *
     * 新增spu属性值
     * @param pmsProductAttrValueDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PmsProductAttrValue> add(@RequestBody @Validated PmsProductAttrValueDto pmsProductAttrValueDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PmsProductAttrValue pmsProductAttrValue = pmsProductAttrValueService.add(pmsProductAttrValueDto);

        return ResultUtil.successOfInsert(pmsProductAttrValue);
    }

    /**
     *
     * 修改spu属性值
     * @param pmsProductAttrValueDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PmsProductAttrValue> update(@RequestBody @Validated PmsProductAttrValueDto pmsProductAttrValueDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PmsProductAttrValue pmsProductAttrValue = pmsProductAttrValueService.update(pmsProductAttrValueDto);
        if (pmsProductAttrValue == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(pmsProductAttrValue);
    }

    /**
     *
     * 删除spu属性值
     * @param pmsProductAttrValueDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PmsProductAttrValue> delete(@RequestBody PmsProductAttrValueDto pmsProductAttrValueDto, HttpServletRequest httpServletRequest) {
        PmsProductAttrValue pmsProductAttrValue = pmsProductAttrValueService.delete(pmsProductAttrValueDto);
        if (pmsProductAttrValue == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(pmsProductAttrValue);
    }

    /**
     *
     * 分页查询spu属性值
     * @param pmsProductAttrValueDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PmsProductAttrValue>> queryPage(@RequestBody PmsProductAttrValueDto pmsProductAttrValueDto) {
        Page<PmsProductAttrValue> pmsProductAttrValuePage = new Page<>(pmsProductAttrValueDto.getPageIndex(), pmsProductAttrValueDto.getPageSize());
        IPage<PmsProductAttrValue> page = pmsProductAttrValueService.queryPage(pmsProductAttrValueDto, pmsProductAttrValuePage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询spu属性值
     * @param pmsProductAttrValueDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PmsProductAttrValue> queryById(@RequestBody PmsProductAttrValueDto pmsProductAttrValueDto) {
        PmsProductAttrValue pmsProductAttrValue = pmsProductAttrValueService.getById(pmsProductAttrValueDto.getId());
        if (pmsProductAttrValue == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(pmsProductAttrValue);
    }


    /**
     *
     * 查询所有spu属性值
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PmsProductAttrValue>> queryAll(@RequestBody PmsProductAttrValueDto pmsProductAttrValueDto) {
        List<PmsProductAttrValue> list = pmsProductAttrValueService.queryAll(pmsProductAttrValueDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验spu属性值名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PmsProductAttrValueDto pmsProductAttrValueDto) {

        if (Objects.isNull(pmsProductAttrValueDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (pmsProductAttrValueService.checkName(pmsProductAttrValueDto, pmsProductAttrValueDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "spu属性值名称已存在！");
    }

}

