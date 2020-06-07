package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IBrandService;
import com.ypdaic.mymall.product.vo.BrandDto;
import com.ypdaic.mymall.product.entity.Brand;

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
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/brand")
public class BrandController extends BaseController {

    @Autowired
    IBrandService brandService;

    /**
     *
     * 新增品牌
     * @param brandDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<Brand> add(@RequestBody @Validated BrandDto brandDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        Brand brand = brandService.add(brandDto);

        return ResultUtil.successOfInsert(brand);
    }

    /**
     *
     * 修改品牌
     * @param brandDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<Brand> update(@RequestBody @Validated BrandDto brandDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        Brand brand = brandService.update(brandDto);
        if (brand == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(brand);
    }

    /**
     *
     * 删除品牌
     * @param brandDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<Brand> delete(@RequestBody BrandDto brandDto, HttpServletRequest httpServletRequest) {
        Brand brand = brandService.delete(brandDto);
        if (brand == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(brand);
    }

    /**
     *
     * 分页查询品牌
     * @param brandDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<Brand>> queryPage(@RequestBody BrandDto brandDto) {
        Page<Brand> brandPage = new Page<>(brandDto.getPageIndex(), brandDto.getPageSize());
        IPage<Brand> page = brandService.queryPage(brandDto, brandPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询品牌
     * @param brandDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<Brand> queryById(@RequestBody BrandDto brandDto) {
        Brand brand = brandService.getById(brandDto.getId());
        if (brand == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(brand);
    }


    /**
     *
     * 查询所有品牌
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<Brand>> queryAll(@RequestBody BrandDto brandDto) {
        List<Brand> list = brandService.queryAll(brandDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验品牌名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody BrandDto brandDto) {

        if (Objects.isNull(brandDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (brandService.checkName(brandDto, brandDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "品牌名称已存在！");
    }

}

