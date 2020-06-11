package com.ypdaic.mymall.order.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.order.service.IOrderReturnApplyService;
import com.ypdaic.mymall.order.vo.OrderReturnApplyDto;
import com.ypdaic.mymall.order.entity.OrderReturnApply;

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
 * 订单退货申请 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/order/orderreturnapply")
public class OrderReturnApplyController extends BaseController {

    @Autowired
    IOrderReturnApplyService orderReturnApplyService;

    /**
     *
     * 新增订单退货申请
     * @param orderReturnApplyDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<OrderReturnApply> add(@RequestBody @Validated OrderReturnApplyDto orderReturnApplyDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        OrderReturnApply orderReturnApply = orderReturnApplyService.add(orderReturnApplyDto);

        return ResultUtil.successOfInsert(orderReturnApply);
    }

    /**
     *
     * 修改订单退货申请
     * @param orderReturnApplyDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<OrderReturnApply> update(@RequestBody @Validated OrderReturnApplyDto orderReturnApplyDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        OrderReturnApply orderReturnApply = orderReturnApplyService.update(orderReturnApplyDto);
        if (orderReturnApply == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(orderReturnApply);
    }

    /**
     *
     * 删除订单退货申请
     * @param orderReturnApplyDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<OrderReturnApply> delete(@RequestBody OrderReturnApplyDto orderReturnApplyDto, HttpServletRequest httpServletRequest) {
        OrderReturnApply orderReturnApply = orderReturnApplyService.delete(orderReturnApplyDto);
        if (orderReturnApply == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(orderReturnApply);
    }

    /**
     *
     * 分页查询订单退货申请
     * @param orderReturnApplyDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<OrderReturnApply>> queryPage(@RequestBody OrderReturnApplyDto orderReturnApplyDto) {
        Page<OrderReturnApply> orderReturnApplyPage = new Page<>(orderReturnApplyDto.getPageIndex(), orderReturnApplyDto.getPageSize());
        IPage<OrderReturnApply> page = orderReturnApplyService.queryPage(orderReturnApplyDto, orderReturnApplyPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询订单退货申请
     * @param orderReturnApplyDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<OrderReturnApply> queryById(@RequestBody OrderReturnApplyDto orderReturnApplyDto) {
        OrderReturnApply orderReturnApply = orderReturnApplyService.getById(orderReturnApplyDto.getId());
        if (orderReturnApply == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(orderReturnApply);
    }


    /**
     *
     * 查询所有订单退货申请
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<OrderReturnApply>> queryAll(@RequestBody OrderReturnApplyDto orderReturnApplyDto) {
        List<OrderReturnApply> list = orderReturnApplyService.queryAll(orderReturnApplyDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验订单退货申请名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody OrderReturnApplyDto orderReturnApplyDto) {

        if (Objects.isNull(orderReturnApplyDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (orderReturnApplyService.checkName(orderReturnApplyDto, orderReturnApplyDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "订单退货申请名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:orderreturnapply:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderReturnApplyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:orderreturnapply:info")
    public R info(@PathVariable("id") Long id){
        OrderReturnApply orderReturnApply = orderReturnApplyService.getById(id);

        return R.ok().put("orderReturnApply", orderReturnApply);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:orderreturnapply:save")
    public R save(@RequestBody OrderReturnApply orderReturnApply){
        orderReturnApplyService.save(orderReturnApply);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:orderreturnapply:update")
    public R update(@RequestBody OrderReturnApply orderReturnApply){
        orderReturnApplyService.updateById(orderReturnApply);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:orderreturnapply:delete")
    public R delete(@RequestBody Long[] ids){
        orderReturnApplyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

