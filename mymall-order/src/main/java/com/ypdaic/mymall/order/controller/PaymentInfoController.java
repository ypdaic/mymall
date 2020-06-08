package com.ypdaic.mymall.order.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.order.service.IPaymentInfoService;
import com.ypdaic.mymall.order.vo.PaymentInfoDto;
import com.ypdaic.mymall.order.entity.PaymentInfo;

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
 * 支付信息表 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/order/payment-info")
public class PaymentInfoController extends BaseController {

    @Autowired
    IPaymentInfoService paymentInfoService;

    /**
     *
     * 新增支付信息表
     * @param paymentInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PaymentInfo> add(@RequestBody @Validated PaymentInfoDto paymentInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PaymentInfo paymentInfo = paymentInfoService.add(paymentInfoDto);

        return ResultUtil.successOfInsert(paymentInfo);
    }

    /**
     *
     * 修改支付信息表
     * @param paymentInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PaymentInfo> update(@RequestBody @Validated PaymentInfoDto paymentInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PaymentInfo paymentInfo = paymentInfoService.update(paymentInfoDto);
        if (paymentInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(paymentInfo);
    }

    /**
     *
     * 删除支付信息表
     * @param paymentInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PaymentInfo> delete(@RequestBody PaymentInfoDto paymentInfoDto, HttpServletRequest httpServletRequest) {
        PaymentInfo paymentInfo = paymentInfoService.delete(paymentInfoDto);
        if (paymentInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(paymentInfo);
    }

    /**
     *
     * 分页查询支付信息表
     * @param paymentInfoDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PaymentInfo>> queryPage(@RequestBody PaymentInfoDto paymentInfoDto) {
        Page<PaymentInfo> paymentInfoPage = new Page<>(paymentInfoDto.getPageIndex(), paymentInfoDto.getPageSize());
        IPage<PaymentInfo> page = paymentInfoService.queryPage(paymentInfoDto, paymentInfoPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询支付信息表
     * @param paymentInfoDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PaymentInfo> queryById(@RequestBody PaymentInfoDto paymentInfoDto) {
        PaymentInfo paymentInfo = paymentInfoService.getById(paymentInfoDto.getId());
        if (paymentInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(paymentInfo);
    }


    /**
     *
     * 查询所有支付信息表
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PaymentInfo>> queryAll(@RequestBody PaymentInfoDto paymentInfoDto) {
        List<PaymentInfo> list = paymentInfoService.queryAll(paymentInfoDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验支付信息表名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PaymentInfoDto paymentInfoDto) {

        if (Objects.isNull(paymentInfoDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (paymentInfoService.checkName(paymentInfoDto, paymentInfoDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "支付信息表名称已存在！");
    }

}

