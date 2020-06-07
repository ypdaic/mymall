package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IPmsAttrService;
import com.ypdaic.mymall.product.vo.PmsAttrDto;
import com.ypdaic.mymall.product.entity.PmsAttr;

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
 * 商品属性 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/product/pms-attr")
public class PmsAttrController extends BaseController {

    @Autowired
    IPmsAttrService pmsAttrService;

    /**
     *
     * 新增商品属性
     * @param pmsAttrDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PmsAttr> add(@RequestBody @Validated PmsAttrDto pmsAttrDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PmsAttr pmsAttr = pmsAttrService.add(pmsAttrDto);

        return ResultUtil.successOfInsert(pmsAttr);
    }

    /**
     *
     * 修改商品属性
     * @param pmsAttrDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PmsAttr> update(@RequestBody @Validated PmsAttrDto pmsAttrDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PmsAttr pmsAttr = pmsAttrService.update(pmsAttrDto);
        if (pmsAttr == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(pmsAttr);
    }

    /**
     *
     * 删除商品属性
     * @param pmsAttrDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PmsAttr> delete(@RequestBody PmsAttrDto pmsAttrDto, HttpServletRequest httpServletRequest) {
        PmsAttr pmsAttr = pmsAttrService.delete(pmsAttrDto);
        if (pmsAttr == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(pmsAttr);
    }

    /**
     *
     * 分页查询商品属性
     * @param pmsAttrDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PmsAttr>> queryPage(@RequestBody PmsAttrDto pmsAttrDto) {
        Page<PmsAttr> pmsAttrPage = new Page<>(pmsAttrDto.getPageIndex(), pmsAttrDto.getPageSize());
        IPage<PmsAttr> page = pmsAttrService.queryPage(pmsAttrDto, pmsAttrPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询商品属性
     * @param pmsAttrDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PmsAttr> queryById(@RequestBody PmsAttrDto pmsAttrDto) {
        PmsAttr pmsAttr = pmsAttrService.getById(pmsAttrDto.getId());
        if (pmsAttr == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(pmsAttr);
    }


    /**
     *
     * 查询所有商品属性
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PmsAttr>> queryAll(@RequestBody PmsAttrDto pmsAttrDto) {
        List<PmsAttr> list = pmsAttrService.queryAll(pmsAttrDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验商品属性名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PmsAttrDto pmsAttrDto) {

        if (Objects.isNull(pmsAttrDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (pmsAttrService.checkName(pmsAttrDto, pmsAttrDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "商品属性名称已存在！");
    }

}

