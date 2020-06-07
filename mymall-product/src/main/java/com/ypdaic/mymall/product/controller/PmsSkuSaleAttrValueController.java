package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IPmsSkuSaleAttrValueService;
import com.ypdaic.mymall.product.vo.PmsSkuSaleAttrValueDto;
import com.ypdaic.mymall.product.entity.PmsSkuSaleAttrValue;

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
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/product/pms-sku-sale-attr-value")
public class PmsSkuSaleAttrValueController extends BaseController {

    @Autowired
    IPmsSkuSaleAttrValueService pmsSkuSaleAttrValueService;

    /**
     *
     * 新增sku销售属性&值
     * @param pmsSkuSaleAttrValueDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PmsSkuSaleAttrValue> add(@RequestBody @Validated PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PmsSkuSaleAttrValue pmsSkuSaleAttrValue = pmsSkuSaleAttrValueService.add(pmsSkuSaleAttrValueDto);

        return ResultUtil.successOfInsert(pmsSkuSaleAttrValue);
    }

    /**
     *
     * 修改sku销售属性&值
     * @param pmsSkuSaleAttrValueDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PmsSkuSaleAttrValue> update(@RequestBody @Validated PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PmsSkuSaleAttrValue pmsSkuSaleAttrValue = pmsSkuSaleAttrValueService.update(pmsSkuSaleAttrValueDto);
        if (pmsSkuSaleAttrValue == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(pmsSkuSaleAttrValue);
    }

    /**
     *
     * 删除sku销售属性&值
     * @param pmsSkuSaleAttrValueDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PmsSkuSaleAttrValue> delete(@RequestBody PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto, HttpServletRequest httpServletRequest) {
        PmsSkuSaleAttrValue pmsSkuSaleAttrValue = pmsSkuSaleAttrValueService.delete(pmsSkuSaleAttrValueDto);
        if (pmsSkuSaleAttrValue == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(pmsSkuSaleAttrValue);
    }

    /**
     *
     * 分页查询sku销售属性&值
     * @param pmsSkuSaleAttrValueDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PmsSkuSaleAttrValue>> queryPage(@RequestBody PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto) {
        Page<PmsSkuSaleAttrValue> pmsSkuSaleAttrValuePage = new Page<>(pmsSkuSaleAttrValueDto.getPageIndex(), pmsSkuSaleAttrValueDto.getPageSize());
        IPage<PmsSkuSaleAttrValue> page = pmsSkuSaleAttrValueService.queryPage(pmsSkuSaleAttrValueDto, pmsSkuSaleAttrValuePage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询sku销售属性&值
     * @param pmsSkuSaleAttrValueDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PmsSkuSaleAttrValue> queryById(@RequestBody PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto) {
        PmsSkuSaleAttrValue pmsSkuSaleAttrValue = pmsSkuSaleAttrValueService.getById(pmsSkuSaleAttrValueDto.getId());
        if (pmsSkuSaleAttrValue == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(pmsSkuSaleAttrValue);
    }


    /**
     *
     * 查询所有sku销售属性&值
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PmsSkuSaleAttrValue>> queryAll(@RequestBody PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto) {
        List<PmsSkuSaleAttrValue> list = pmsSkuSaleAttrValueService.queryAll(pmsSkuSaleAttrValueDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验sku销售属性&值名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto) {

        if (Objects.isNull(pmsSkuSaleAttrValueDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (pmsSkuSaleAttrValueService.checkName(pmsSkuSaleAttrValueDto, pmsSkuSaleAttrValueDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "sku销售属性&值名称已存在！");
    }

}

