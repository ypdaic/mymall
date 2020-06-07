package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.ISkuSaleAttrValueService;
import com.ypdaic.mymall.product.vo.SkuSaleAttrValueDto;
import com.ypdaic.mymall.product.entity.SkuSaleAttrValue;

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
 * sku销售属性&值 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/sku-sale-attr-value")
public class SkuSaleAttrValueController extends BaseController {

    @Autowired
    ISkuSaleAttrValueService skuSaleAttrValueService;

    /**
     *
     * 新增sku销售属性&值
     * @param skuSaleAttrValueDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SkuSaleAttrValue> add(@RequestBody @Validated SkuSaleAttrValueDto skuSaleAttrValueDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueService.add(skuSaleAttrValueDto);

        return ResultUtil.successOfInsert(skuSaleAttrValue);
    }

    /**
     *
     * 修改sku销售属性&值
     * @param skuSaleAttrValueDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SkuSaleAttrValue> update(@RequestBody @Validated SkuSaleAttrValueDto skuSaleAttrValueDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueService.update(skuSaleAttrValueDto);
        if (skuSaleAttrValue == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(skuSaleAttrValue);
    }

    /**
     *
     * 删除sku销售属性&值
     * @param skuSaleAttrValueDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SkuSaleAttrValue> delete(@RequestBody SkuSaleAttrValueDto skuSaleAttrValueDto, HttpServletRequest httpServletRequest) {
        SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueService.delete(skuSaleAttrValueDto);
        if (skuSaleAttrValue == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(skuSaleAttrValue);
    }

    /**
     *
     * 分页查询sku销售属性&值
     * @param skuSaleAttrValueDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SkuSaleAttrValue>> queryPage(@RequestBody SkuSaleAttrValueDto skuSaleAttrValueDto) {
        Page<SkuSaleAttrValue> skuSaleAttrValuePage = new Page<>(skuSaleAttrValueDto.getPageIndex(), skuSaleAttrValueDto.getPageSize());
        IPage<SkuSaleAttrValue> page = skuSaleAttrValueService.queryPage(skuSaleAttrValueDto, skuSaleAttrValuePage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询sku销售属性&值
     * @param skuSaleAttrValueDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SkuSaleAttrValue> queryById(@RequestBody SkuSaleAttrValueDto skuSaleAttrValueDto) {
        SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueService.getById(skuSaleAttrValueDto.getId());
        if (skuSaleAttrValue == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(skuSaleAttrValue);
    }


    /**
     *
     * 查询所有sku销售属性&值
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SkuSaleAttrValue>> queryAll(@RequestBody SkuSaleAttrValueDto skuSaleAttrValueDto) {
        List<SkuSaleAttrValue> list = skuSaleAttrValueService.queryAll(skuSaleAttrValueDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验sku销售属性&值名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SkuSaleAttrValueDto skuSaleAttrValueDto) {

        if (Objects.isNull(skuSaleAttrValueDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (skuSaleAttrValueService.checkName(skuSaleAttrValueDto, skuSaleAttrValueDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "sku销售属性&值名称已存在！");
    }

}

