package com.ypdaic.mymall.product.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IProductAttrValueService;
import com.ypdaic.mymall.product.vo.ProductAttrValueDto;
import com.ypdaic.mymall.product.entity.ProductAttrValue;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * spu属性值 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/product-attr-value")
public class ProductAttrValueController extends BaseController {

    @Autowired
    IProductAttrValueService productAttrValueService;

    /**
     *
     * 新增spu属性值
     * @param productAttrValueDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<ProductAttrValue> add(@RequestBody @Validated ProductAttrValueDto productAttrValueDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        ProductAttrValue productAttrValue = productAttrValueService.add(productAttrValueDto);

        return ResultUtil.successOfInsert(productAttrValue);
    }

    /**
     *
     * 修改spu属性值
     * @param productAttrValueDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<ProductAttrValue> update(@RequestBody @Validated ProductAttrValueDto productAttrValueDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        ProductAttrValue productAttrValue = productAttrValueService.update(productAttrValueDto);
        if (productAttrValue == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(productAttrValue);
    }

    /**
     *
     * 删除spu属性值
     * @param productAttrValueDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<ProductAttrValue> delete(@RequestBody ProductAttrValueDto productAttrValueDto, HttpServletRequest httpServletRequest) {
        ProductAttrValue productAttrValue = productAttrValueService.delete(productAttrValueDto);
        if (productAttrValue == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(productAttrValue);
    }

    /**
     *
     * 分页查询spu属性值
     * @param productAttrValueDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<ProductAttrValue>> queryPage(@RequestBody ProductAttrValueDto productAttrValueDto) {
        Page<ProductAttrValue> productAttrValuePage = new Page<>(productAttrValueDto.getPageIndex(), productAttrValueDto.getPageSize());
        IPage<ProductAttrValue> page = productAttrValueService.queryPage(productAttrValueDto, productAttrValuePage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询spu属性值
     * @param productAttrValueDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<ProductAttrValue> queryById(@RequestBody ProductAttrValueDto productAttrValueDto) {
        ProductAttrValue productAttrValue = productAttrValueService.getById(productAttrValueDto.getId());
        if (productAttrValue == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(productAttrValue);
    }


    /**
     *
     * 查询所有spu属性值
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<ProductAttrValue>> queryAll(@RequestBody ProductAttrValueDto productAttrValueDto) {
        List<ProductAttrValue> list = productAttrValueService.queryAll(productAttrValueDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验spu属性值名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody ProductAttrValueDto productAttrValueDto) {

        if (Objects.isNull(productAttrValueDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (productAttrValueService.checkName(productAttrValueDto, productAttrValueDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "spu属性值名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:productattrvalue:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productAttrValueService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:productattrvalue:info")
    public R info(@PathVariable("id") Long id){
        ProductAttrValue productAttrValue = productAttrValueService.getById(id);

        return R.ok().put("productAttrValue", productAttrValue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:productattrvalue:save")
    public R save(@RequestBody ProductAttrValue productAttrValue){
        productAttrValueService.save(productAttrValue);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:productattrvalue:update")
    public R update(@RequestBody ProductAttrValue productAttrValue){
        productAttrValueService.updateById(productAttrValue);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:productattrvalue:delete")
    public R delete(@RequestBody Long[] ids){
        productAttrValueService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

