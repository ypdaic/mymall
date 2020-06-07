package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.ICategoryBrandRelationService;
import com.ypdaic.mymall.product.vo.CategoryBrandRelationDto;
import com.ypdaic.mymall.product.entity.CategoryBrandRelation;

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
 * 品牌分类关联 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/category-brand-relation")
public class CategoryBrandRelationController extends BaseController {

    @Autowired
    ICategoryBrandRelationService categoryBrandRelationService;

    /**
     *
     * 新增品牌分类关联
     * @param categoryBrandRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<CategoryBrandRelation> add(@RequestBody @Validated CategoryBrandRelationDto categoryBrandRelationDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        CategoryBrandRelation categoryBrandRelation = categoryBrandRelationService.add(categoryBrandRelationDto);

        return ResultUtil.successOfInsert(categoryBrandRelation);
    }

    /**
     *
     * 修改品牌分类关联
     * @param categoryBrandRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<CategoryBrandRelation> update(@RequestBody @Validated CategoryBrandRelationDto categoryBrandRelationDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        CategoryBrandRelation categoryBrandRelation = categoryBrandRelationService.update(categoryBrandRelationDto);
        if (categoryBrandRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(categoryBrandRelation);
    }

    /**
     *
     * 删除品牌分类关联
     * @param categoryBrandRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<CategoryBrandRelation> delete(@RequestBody CategoryBrandRelationDto categoryBrandRelationDto, HttpServletRequest httpServletRequest) {
        CategoryBrandRelation categoryBrandRelation = categoryBrandRelationService.delete(categoryBrandRelationDto);
        if (categoryBrandRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(categoryBrandRelation);
    }

    /**
     *
     * 分页查询品牌分类关联
     * @param categoryBrandRelationDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<CategoryBrandRelation>> queryPage(@RequestBody CategoryBrandRelationDto categoryBrandRelationDto) {
        Page<CategoryBrandRelation> categoryBrandRelationPage = new Page<>(categoryBrandRelationDto.getPageIndex(), categoryBrandRelationDto.getPageSize());
        IPage<CategoryBrandRelation> page = categoryBrandRelationService.queryPage(categoryBrandRelationDto, categoryBrandRelationPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询品牌分类关联
     * @param categoryBrandRelationDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<CategoryBrandRelation> queryById(@RequestBody CategoryBrandRelationDto categoryBrandRelationDto) {
        CategoryBrandRelation categoryBrandRelation = categoryBrandRelationService.getById(categoryBrandRelationDto.getId());
        if (categoryBrandRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(categoryBrandRelation);
    }


    /**
     *
     * 查询所有品牌分类关联
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<CategoryBrandRelation>> queryAll(@RequestBody CategoryBrandRelationDto categoryBrandRelationDto) {
        List<CategoryBrandRelation> list = categoryBrandRelationService.queryAll(categoryBrandRelationDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验品牌分类关联名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody CategoryBrandRelationDto categoryBrandRelationDto) {

        if (Objects.isNull(categoryBrandRelationDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (categoryBrandRelationService.checkName(categoryBrandRelationDto, categoryBrandRelationDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "品牌分类关联名称已存在！");
    }

}

