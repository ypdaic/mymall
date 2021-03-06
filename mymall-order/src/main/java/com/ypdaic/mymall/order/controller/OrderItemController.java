package com.ypdaic.mymall.order.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.order.service.IOrderItemService;
import com.ypdaic.mymall.order.vo.OrderItemDto;
import com.ypdaic.mymall.order.entity.OrderItem;

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
 * 订单项信息 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/order/order-item")
public class OrderItemController extends BaseController {

    @Autowired
    IOrderItemService orderItemService;

    /**
     *
     * 新增订单项信息
     * @param orderItemDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<OrderItem> add(@RequestBody @Validated OrderItemDto orderItemDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        OrderItem orderItem = orderItemService.add(orderItemDto);

        return ResultUtil.successOfInsert(orderItem);
    }

    /**
     *
     * 修改订单项信息
     * @param orderItemDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<OrderItem> update(@RequestBody @Validated OrderItemDto orderItemDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        OrderItem orderItem = orderItemService.update(orderItemDto);
        if (orderItem == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(orderItem);
    }

    /**
     *
     * 删除订单项信息
     * @param orderItemDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<OrderItem> delete(@RequestBody OrderItemDto orderItemDto, HttpServletRequest httpServletRequest) {
        OrderItem orderItem = orderItemService.delete(orderItemDto);
        if (orderItem == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(orderItem);
    }

    /**
     *
     * 分页查询订单项信息
     * @param orderItemDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<OrderItem>> queryPage(@RequestBody OrderItemDto orderItemDto) {
        Page<OrderItem> orderItemPage = new Page<>(orderItemDto.getPageIndex(), orderItemDto.getPageSize());
        IPage<OrderItem> page = orderItemService.queryPage(orderItemDto, orderItemPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询订单项信息
     * @param orderItemDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<OrderItem> queryById(@RequestBody OrderItemDto orderItemDto) {
        OrderItem orderItem = orderItemService.getById(orderItemDto.getId());
        if (orderItem == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(orderItem);
    }


    /**
     *
     * 查询所有订单项信息
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<OrderItem>> queryAll(@RequestBody OrderItemDto orderItemDto) {
        List<OrderItem> list = orderItemService.queryAll(orderItemDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验订单项信息名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody OrderItemDto orderItemDto) {

        if (Objects.isNull(orderItemDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (orderItemService.checkName(orderItemDto, orderItemDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "订单项信息名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:orderitem:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderItemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:orderitem:info")
    public R info(@PathVariable("id") Long id){
        OrderItem orderItem = orderItemService.getById(id);

        return R.ok().put("orderItem", orderItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:orderitem:save")
    public R save(@RequestBody OrderItem orderItem){
        orderItemService.save(orderItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:orderitem:update")
    public R update(@RequestBody OrderItem orderItem){
        orderItemService.updateById(orderItem);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:orderitem:delete")
    public R delete(@RequestBody Long[] ids){
        orderItemService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

