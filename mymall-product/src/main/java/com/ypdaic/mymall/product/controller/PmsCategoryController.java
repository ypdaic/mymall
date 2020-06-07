package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IPmsCategoryService;
import com.ypdaic.mymall.product.vo.PmsCategoryDto;
import com.ypdaic.mymall.product.entity.PmsCategory;

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
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/product/pms-category")
public class PmsCategoryController extends BaseController {

    @Autowired
    IPmsCategoryService pmsCategoryService;

    /**
     *
     * 新增商品三级分类
     * @param pmsCategoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PmsCategory> add(@RequestBody @Validated PmsCategoryDto pmsCategoryDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PmsCategory pmsCategory = pmsCategoryService.add(pmsCategoryDto);

        return ResultUtil.successOfInsert(pmsCategory);
    }

    /**
     *
     * 修改商品三级分类
     * @param pmsCategoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PmsCategory> update(@RequestBody @Validated PmsCategoryDto pmsCategoryDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PmsCategory pmsCategory = pmsCategoryService.update(pmsCategoryDto);
        if (pmsCategory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(pmsCategory);
    }

    /**
     *
     * 删除商品三级分类
     * @param pmsCategoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PmsCategory> delete(@RequestBody PmsCategoryDto pmsCategoryDto, HttpServletRequest httpServletRequest) {
        PmsCategory pmsCategory = pmsCategoryService.delete(pmsCategoryDto);
        if (pmsCategory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(pmsCategory);
    }

    /**
     *
     * 分页查询商品三级分类
     * @param pmsCategoryDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PmsCategory>> queryPage(@RequestBody PmsCategoryDto pmsCategoryDto) {
        Page<PmsCategory> pmsCategoryPage = new Page<>(pmsCategoryDto.getPageIndex(), pmsCategoryDto.getPageSize());
        IPage<PmsCategory> page = pmsCategoryService.queryPage(pmsCategoryDto, pmsCategoryPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询商品三级分类
     * @param pmsCategoryDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PmsCategory> queryById(@RequestBody PmsCategoryDto pmsCategoryDto) {
        PmsCategory pmsCategory = pmsCategoryService.getById(pmsCategoryDto.getId());
        if (pmsCategory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(pmsCategory);
    }


    /**
     *
     * 查询所有商品三级分类
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PmsCategory>> queryAll(@RequestBody PmsCategoryDto pmsCategoryDto) {
        List<PmsCategory> list = pmsCategoryService.queryAll(pmsCategoryDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验商品三级分类名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PmsCategoryDto pmsCategoryDto) {

        if (Objects.isNull(pmsCategoryDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (pmsCategoryService.checkName(pmsCategoryDto, pmsCategoryDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "商品三级分类名称已存在！");
    }

}

