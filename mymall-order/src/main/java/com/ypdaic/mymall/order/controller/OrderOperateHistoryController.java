package com.ypdaic.mymall.order.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.order.service.IOrderOperateHistoryService;
import com.ypdaic.mymall.order.vo.OrderOperateHistoryDto;
import com.ypdaic.mymall.order.entity.OrderOperateHistory;

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
 * 订单操作历史记录 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/order/order-operate-history")
public class OrderOperateHistoryController extends BaseController {

    @Autowired
    IOrderOperateHistoryService orderOperateHistoryService;

    /**
     *
     * 新增订单操作历史记录
     * @param orderOperateHistoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<OrderOperateHistory> add(@RequestBody @Validated OrderOperateHistoryDto orderOperateHistoryDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        OrderOperateHistory orderOperateHistory = orderOperateHistoryService.add(orderOperateHistoryDto);

        return ResultUtil.successOfInsert(orderOperateHistory);
    }

    /**
     *
     * 修改订单操作历史记录
     * @param orderOperateHistoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<OrderOperateHistory> update(@RequestBody @Validated OrderOperateHistoryDto orderOperateHistoryDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        OrderOperateHistory orderOperateHistory = orderOperateHistoryService.update(orderOperateHistoryDto);
        if (orderOperateHistory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(orderOperateHistory);
    }

    /**
     *
     * 删除订单操作历史记录
     * @param orderOperateHistoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<OrderOperateHistory> delete(@RequestBody OrderOperateHistoryDto orderOperateHistoryDto, HttpServletRequest httpServletRequest) {
        OrderOperateHistory orderOperateHistory = orderOperateHistoryService.delete(orderOperateHistoryDto);
        if (orderOperateHistory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(orderOperateHistory);
    }

    /**
     *
     * 分页查询订单操作历史记录
     * @param orderOperateHistoryDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<OrderOperateHistory>> queryPage(@RequestBody OrderOperateHistoryDto orderOperateHistoryDto) {
        Page<OrderOperateHistory> orderOperateHistoryPage = new Page<>(orderOperateHistoryDto.getPageIndex(), orderOperateHistoryDto.getPageSize());
        IPage<OrderOperateHistory> page = orderOperateHistoryService.queryPage(orderOperateHistoryDto, orderOperateHistoryPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询订单操作历史记录
     * @param orderOperateHistoryDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<OrderOperateHistory> queryById(@RequestBody OrderOperateHistoryDto orderOperateHistoryDto) {
        OrderOperateHistory orderOperateHistory = orderOperateHistoryService.getById(orderOperateHistoryDto.getId());
        if (orderOperateHistory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(orderOperateHistory);
    }


    /**
     *
     * 查询所有订单操作历史记录
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<OrderOperateHistory>> queryAll(@RequestBody OrderOperateHistoryDto orderOperateHistoryDto) {
        List<OrderOperateHistory> list = orderOperateHistoryService.queryAll(orderOperateHistoryDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验订单操作历史记录名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody OrderOperateHistoryDto orderOperateHistoryDto) {

        if (Objects.isNull(orderOperateHistoryDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (orderOperateHistoryService.checkName(orderOperateHistoryDto, orderOperateHistoryDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "订单操作历史记录名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:orderoperatehistory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderOperateHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:orderoperatehistory:info")
    public R info(@PathVariable("id") Long id){
        OrderOperateHistory orderOperateHistory = orderOperateHistoryService.getById(id);

        return R.ok().put("orderOperateHistory", orderOperateHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:orderoperatehistory:save")
    public R save(@RequestBody OrderOperateHistory orderOperateHistory){
        orderOperateHistoryService.save(orderOperateHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:orderoperatehistory:update")
    public R update(@RequestBody OrderOperateHistory orderOperateHistory){
        orderOperateHistoryService.updateById(orderOperateHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:orderoperatehistory:delete")
    public R delete(@RequestBody Long[] ids){
        orderOperateHistoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

