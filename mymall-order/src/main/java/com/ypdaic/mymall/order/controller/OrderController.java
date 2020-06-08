package com.ypdaic.mymall.order.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.order.service.IOrderService;
import com.ypdaic.mymall.order.vo.OrderDto;
import com.ypdaic.mymall.order.entity.Order;

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
 * 订单 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/order/order")
public class OrderController extends BaseController {

    @Autowired
    IOrderService orderService;

    /**
     *
     * 新增订单
     * @param orderDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<Order> add(@RequestBody @Validated OrderDto orderDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        Order order = orderService.add(orderDto);

        return ResultUtil.successOfInsert(order);
    }

    /**
     *
     * 修改订单
     * @param orderDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<Order> update(@RequestBody @Validated OrderDto orderDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        Order order = orderService.update(orderDto);
        if (order == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(order);
    }

    /**
     *
     * 删除订单
     * @param orderDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<Order> delete(@RequestBody OrderDto orderDto, HttpServletRequest httpServletRequest) {
        Order order = orderService.delete(orderDto);
        if (order == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(order);
    }

    /**
     *
     * 分页查询订单
     * @param orderDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<Order>> queryPage(@RequestBody OrderDto orderDto) {
        Page<Order> orderPage = new Page<>(orderDto.getPageIndex(), orderDto.getPageSize());
        IPage<Order> page = orderService.queryPage(orderDto, orderPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询订单
     * @param orderDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<Order> queryById(@RequestBody OrderDto orderDto) {
        Order order = orderService.getById(orderDto.getId());
        if (order == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(order);
    }


    /**
     *
     * 查询所有订单
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<Order>> queryAll(@RequestBody OrderDto orderDto) {
        List<Order> list = orderService.queryAll(orderDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验订单名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody OrderDto orderDto) {

        if (Objects.isNull(orderDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (orderService.checkName(orderDto, orderDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "订单名称已存在！");
    }

}

