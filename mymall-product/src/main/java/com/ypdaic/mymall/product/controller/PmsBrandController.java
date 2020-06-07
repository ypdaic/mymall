package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IPmsBrandService;
import com.ypdaic.mymall.product.vo.PmsBrandDto;
import com.ypdaic.mymall.product.entity.PmsBrand;

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
 * 品牌 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/product/pms-brand")
public class PmsBrandController extends BaseController {

    @Autowired
    IPmsBrandService pmsBrandService;

    /**
     *
     * 新增品牌
     * @param pmsBrandDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PmsBrand> add(@RequestBody @Validated PmsBrandDto pmsBrandDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PmsBrand pmsBrand = pmsBrandService.add(pmsBrandDto);

        return ResultUtil.successOfInsert(pmsBrand);
    }

    /**
     *
     * 修改品牌
     * @param pmsBrandDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PmsBrand> update(@RequestBody @Validated PmsBrandDto pmsBrandDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PmsBrand pmsBrand = pmsBrandService.update(pmsBrandDto);
        if (pmsBrand == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(pmsBrand);
    }

    /**
     *
     * 删除品牌
     * @param pmsBrandDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PmsBrand> delete(@RequestBody PmsBrandDto pmsBrandDto, HttpServletRequest httpServletRequest) {
        PmsBrand pmsBrand = pmsBrandService.delete(pmsBrandDto);
        if (pmsBrand == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(pmsBrand);
    }

    /**
     *
     * 分页查询品牌
     * @param pmsBrandDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PmsBrand>> queryPage(@RequestBody PmsBrandDto pmsBrandDto) {
        Page<PmsBrand> pmsBrandPage = new Page<>(pmsBrandDto.getPageIndex(), pmsBrandDto.getPageSize());
        IPage<PmsBrand> page = pmsBrandService.queryPage(pmsBrandDto, pmsBrandPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询品牌
     * @param pmsBrandDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PmsBrand> queryById(@RequestBody PmsBrandDto pmsBrandDto) {
        PmsBrand pmsBrand = pmsBrandService.getById(pmsBrandDto.getId());
        if (pmsBrand == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(pmsBrand);
    }


    /**
     *
     * 查询所有品牌
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PmsBrand>> queryAll(@RequestBody PmsBrandDto pmsBrandDto) {
        List<PmsBrand> list = pmsBrandService.queryAll(pmsBrandDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验品牌名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PmsBrandDto pmsBrandDto) {

        if (Objects.isNull(pmsBrandDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (pmsBrandService.checkName(pmsBrandDto, pmsBrandDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "品牌名称已存在！");
    }

}

