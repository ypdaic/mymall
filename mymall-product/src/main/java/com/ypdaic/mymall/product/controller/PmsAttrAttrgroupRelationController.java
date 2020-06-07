package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IPmsAttrAttrgroupRelationService;
import com.ypdaic.mymall.product.vo.PmsAttrAttrgroupRelationDto;
import com.ypdaic.mymall.product.entity.PmsAttrAttrgroupRelation;

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
 * 属性&属性分组关联 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/product/pms-attr-attrgroup-relation")
public class PmsAttrAttrgroupRelationController extends BaseController {

    @Autowired
    IPmsAttrAttrgroupRelationService pmsAttrAttrgroupRelationService;

    /**
     *
     * 新增属性&属性分组关联
     * @param pmsAttrAttrgroupRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PmsAttrAttrgroupRelation> add(@RequestBody @Validated PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = pmsAttrAttrgroupRelationService.add(pmsAttrAttrgroupRelationDto);

        return ResultUtil.successOfInsert(pmsAttrAttrgroupRelation);
    }

    /**
     *
     * 修改属性&属性分组关联
     * @param pmsAttrAttrgroupRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PmsAttrAttrgroupRelation> update(@RequestBody @Validated PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = pmsAttrAttrgroupRelationService.update(pmsAttrAttrgroupRelationDto);
        if (pmsAttrAttrgroupRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(pmsAttrAttrgroupRelation);
    }

    /**
     *
     * 删除属性&属性分组关联
     * @param pmsAttrAttrgroupRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PmsAttrAttrgroupRelation> delete(@RequestBody PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto, HttpServletRequest httpServletRequest) {
        PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = pmsAttrAttrgroupRelationService.delete(pmsAttrAttrgroupRelationDto);
        if (pmsAttrAttrgroupRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(pmsAttrAttrgroupRelation);
    }

    /**
     *
     * 分页查询属性&属性分组关联
     * @param pmsAttrAttrgroupRelationDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PmsAttrAttrgroupRelation>> queryPage(@RequestBody PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto) {
        Page<PmsAttrAttrgroupRelation> pmsAttrAttrgroupRelationPage = new Page<>(pmsAttrAttrgroupRelationDto.getPageIndex(), pmsAttrAttrgroupRelationDto.getPageSize());
        IPage<PmsAttrAttrgroupRelation> page = pmsAttrAttrgroupRelationService.queryPage(pmsAttrAttrgroupRelationDto, pmsAttrAttrgroupRelationPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询属性&属性分组关联
     * @param pmsAttrAttrgroupRelationDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PmsAttrAttrgroupRelation> queryById(@RequestBody PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto) {
        PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = pmsAttrAttrgroupRelationService.getById(pmsAttrAttrgroupRelationDto.getId());
        if (pmsAttrAttrgroupRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(pmsAttrAttrgroupRelation);
    }


    /**
     *
     * 查询所有属性&属性分组关联
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PmsAttrAttrgroupRelation>> queryAll(@RequestBody PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto) {
        List<PmsAttrAttrgroupRelation> list = pmsAttrAttrgroupRelationService.queryAll(pmsAttrAttrgroupRelationDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验属性&属性分组关联名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto) {

        if (Objects.isNull(pmsAttrAttrgroupRelationDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (pmsAttrAttrgroupRelationService.checkName(pmsAttrAttrgroupRelationDto, pmsAttrAttrgroupRelationDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "属性&属性分组关联名称已存在！");
    }

}

