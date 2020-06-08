package com.ypdaic.mymall.ware.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.ware.service.IPurchaseService;
import com.ypdaic.mymall.ware.vo.PurchaseDto;
import com.ypdaic.mymall.ware.entity.Purchase;

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
 * 采购信息 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/ware/purchase")
public class PurchaseController extends BaseController {

    @Autowired
    IPurchaseService purchaseService;

    /**
     *
     * 新增采购信息
     * @param purchaseDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<Purchase> add(@RequestBody @Validated PurchaseDto purchaseDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        Purchase purchase = purchaseService.add(purchaseDto);

        return ResultUtil.successOfInsert(purchase);
    }

    /**
     *
     * 修改采购信息
     * @param purchaseDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<Purchase> update(@RequestBody @Validated PurchaseDto purchaseDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        Purchase purchase = purchaseService.update(purchaseDto);
        if (purchase == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(purchase);
    }

    /**
     *
     * 删除采购信息
     * @param purchaseDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<Purchase> delete(@RequestBody PurchaseDto purchaseDto, HttpServletRequest httpServletRequest) {
        Purchase purchase = purchaseService.delete(purchaseDto);
        if (purchase == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(purchase);
    }

    /**
     *
     * 分页查询采购信息
     * @param purchaseDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<Purchase>> queryPage(@RequestBody PurchaseDto purchaseDto) {
        Page<Purchase> purchasePage = new Page<>(purchaseDto.getPageIndex(), purchaseDto.getPageSize());
        IPage<Purchase> page = purchaseService.queryPage(purchaseDto, purchasePage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询采购信息
     * @param purchaseDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<Purchase> queryById(@RequestBody PurchaseDto purchaseDto) {
        Purchase purchase = purchaseService.getById(purchaseDto.getId());
        if (purchase == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(purchase);
    }


    /**
     *
     * 查询所有采购信息
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<Purchase>> queryAll(@RequestBody PurchaseDto purchaseDto) {
        List<Purchase> list = purchaseService.queryAll(purchaseDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验采购信息名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PurchaseDto purchaseDto) {

        if (Objects.isNull(purchaseDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (purchaseService.checkName(purchaseDto, purchaseDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "采购信息名称已存在！");
    }

}

