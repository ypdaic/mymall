package com.ypdaic.mymall.ware.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.ware.service.IPurchaseDetailService;
import com.ypdaic.mymall.ware.vo.PurchaseDetailDto;
import com.ypdaic.mymall.ware.entity.PurchaseDetail;

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
 *  前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/ware/purchase-detail")
public class PurchaseDetailController extends BaseController {

    @Autowired
    IPurchaseDetailService purchaseDetailService;

    /**
     *
     * 新增
     * @param purchaseDetailDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PurchaseDetail> add(@RequestBody @Validated PurchaseDetailDto purchaseDetailDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PurchaseDetail purchaseDetail = purchaseDetailService.add(purchaseDetailDto);

        return ResultUtil.successOfInsert(purchaseDetail);
    }

    /**
     *
     * 修改
     * @param purchaseDetailDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PurchaseDetail> update(@RequestBody @Validated PurchaseDetailDto purchaseDetailDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PurchaseDetail purchaseDetail = purchaseDetailService.update(purchaseDetailDto);
        if (purchaseDetail == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(purchaseDetail);
    }

    /**
     *
     * 删除
     * @param purchaseDetailDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PurchaseDetail> delete(@RequestBody PurchaseDetailDto purchaseDetailDto, HttpServletRequest httpServletRequest) {
        PurchaseDetail purchaseDetail = purchaseDetailService.delete(purchaseDetailDto);
        if (purchaseDetail == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(purchaseDetail);
    }

    /**
     *
     * 分页查询
     * @param purchaseDetailDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PurchaseDetail>> queryPage(@RequestBody PurchaseDetailDto purchaseDetailDto) {
        Page<PurchaseDetail> purchaseDetailPage = new Page<>(purchaseDetailDto.getPageIndex(), purchaseDetailDto.getPageSize());
        IPage<PurchaseDetail> page = purchaseDetailService.queryPage(purchaseDetailDto, purchaseDetailPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询
     * @param purchaseDetailDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PurchaseDetail> queryById(@RequestBody PurchaseDetailDto purchaseDetailDto) {
        PurchaseDetail purchaseDetail = purchaseDetailService.getById(purchaseDetailDto.getId());
        if (purchaseDetail == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(purchaseDetail);
    }


    /**
     *
     * 查询所有
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PurchaseDetail>> queryAll(@RequestBody PurchaseDetailDto purchaseDetailDto) {
        List<PurchaseDetail> list = purchaseDetailService.queryAll(purchaseDetailDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PurchaseDetailDto purchaseDetailDto) {

        if (Objects.isNull(purchaseDetailDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (purchaseDetailService.checkName(purchaseDetailDto, purchaseDetailDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "名称已存在！");
    }

}

