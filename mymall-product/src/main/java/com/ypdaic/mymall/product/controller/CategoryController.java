package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.ICategoryService;
import com.ypdaic.mymall.product.vo.CategoryDto;
import com.ypdaic.mymall.product.entity.Category;

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
 * 商品三级分类 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/category")
public class CategoryController extends BaseController {

    @Autowired
    ICategoryService categoryService;

    /**
     *
     * 新增商品三级分类
     * @param categoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<Category> add(@RequestBody @Validated CategoryDto categoryDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        Category category = categoryService.add(categoryDto);

        return ResultUtil.successOfInsert(category);
    }

    /**
     *
     * 修改商品三级分类
     * @param categoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<Category> update(@RequestBody @Validated CategoryDto categoryDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        Category category = categoryService.update(categoryDto);
        if (category == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(category);
    }

    /**
     *
     * 删除商品三级分类
     * @param categoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<Category> delete(@RequestBody CategoryDto categoryDto, HttpServletRequest httpServletRequest) {
        Category category = categoryService.delete(categoryDto);
        if (category == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(category);
    }

    /**
     *
     * 分页查询商品三级分类
     * @param categoryDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<Category>> queryPage(@RequestBody CategoryDto categoryDto) {
        Page<Category> categoryPage = new Page<>(categoryDto.getPageIndex(), categoryDto.getPageSize());
        IPage<Category> page = categoryService.queryPage(categoryDto, categoryPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询商品三级分类
     * @param categoryDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<Category> queryById(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.getById(categoryDto.getId());
        if (category == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(category);
    }


    /**
     *
     * 查询所有商品三级分类
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<Category>> queryAll(@RequestBody CategoryDto categoryDto) {
        List<Category> list = categoryService.queryAll(categoryDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验商品三级分类名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody CategoryDto categoryDto) {

        if (Objects.isNull(categoryDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (categoryService.checkName(categoryDto, categoryDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "商品三级分类名称已存在！");
    }

}

