package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IAttrAttrgroupRelationService;
import com.ypdaic.mymall.product.vo.AttrAttrgroupRelationDto;
import com.ypdaic.mymall.product.entity.AttrAttrgroupRelation;

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
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/attr-attrgroup-relation")
public class AttrAttrgroupRelationController extends BaseController {

    @Autowired
    IAttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     *
     * 新增属性&属性分组关联
     * @param attrAttrgroupRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<AttrAttrgroupRelation> add(@RequestBody @Validated AttrAttrgroupRelationDto attrAttrgroupRelationDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationService.add(attrAttrgroupRelationDto);

        return ResultUtil.successOfInsert(attrAttrgroupRelation);
    }

    /**
     *
     * 修改属性&属性分组关联
     * @param attrAttrgroupRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<AttrAttrgroupRelation> update(@RequestBody @Validated AttrAttrgroupRelationDto attrAttrgroupRelationDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationService.update(attrAttrgroupRelationDto);
        if (attrAttrgroupRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(attrAttrgroupRelation);
    }

    /**
     *
     * 删除属性&属性分组关联
     * @param attrAttrgroupRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<AttrAttrgroupRelation> delete(@RequestBody AttrAttrgroupRelationDto attrAttrgroupRelationDto, HttpServletRequest httpServletRequest) {
        AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationService.delete(attrAttrgroupRelationDto);
        if (attrAttrgroupRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(attrAttrgroupRelation);
    }

    /**
     *
     * 分页查询属性&属性分组关联
     * @param attrAttrgroupRelationDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<AttrAttrgroupRelation>> queryPage(@RequestBody AttrAttrgroupRelationDto attrAttrgroupRelationDto) {
        Page<AttrAttrgroupRelation> attrAttrgroupRelationPage = new Page<>(attrAttrgroupRelationDto.getPageIndex(), attrAttrgroupRelationDto.getPageSize());
        IPage<AttrAttrgroupRelation> page = attrAttrgroupRelationService.queryPage(attrAttrgroupRelationDto, attrAttrgroupRelationPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询属性&属性分组关联
     * @param attrAttrgroupRelationDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<AttrAttrgroupRelation> queryById(@RequestBody AttrAttrgroupRelationDto attrAttrgroupRelationDto) {
        AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationService.getById(attrAttrgroupRelationDto.getId());
        if (attrAttrgroupRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(attrAttrgroupRelation);
    }


    /**
     *
     * 查询所有属性&属性分组关联
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<AttrAttrgroupRelation>> queryAll(@RequestBody AttrAttrgroupRelationDto attrAttrgroupRelationDto) {
        List<AttrAttrgroupRelation> list = attrAttrgroupRelationService.queryAll(attrAttrgroupRelationDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验属性&属性分组关联名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody AttrAttrgroupRelationDto attrAttrgroupRelationDto) {

        if (Objects.isNull(attrAttrgroupRelationDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (attrAttrgroupRelationService.checkName(attrAttrgroupRelationDto, attrAttrgroupRelationDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "属性&属性分组关联名称已存在！");
    }

}

