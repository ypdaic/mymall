package com.ypdaic.mymall.order.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.order.service.IOrderSettingService;
import com.ypdaic.mymall.order.vo.OrderSettingDto;
import com.ypdaic.mymall.order.entity.OrderSetting;

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
 * 订单配置信息 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/order/ordersetting")
public class OrderSettingController extends BaseController {

    @Autowired
    IOrderSettingService orderSettingService;

    /**
     *
     * 新增订单配置信息
     * @param orderSettingDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<OrderSetting> add(@RequestBody @Validated OrderSettingDto orderSettingDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        OrderSetting orderSetting = orderSettingService.add(orderSettingDto);

        return ResultUtil.successOfInsert(orderSetting);
    }

    /**
     *
     * 修改订单配置信息
     * @param orderSettingDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<OrderSetting> update(@RequestBody @Validated OrderSettingDto orderSettingDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        OrderSetting orderSetting = orderSettingService.update(orderSettingDto);
        if (orderSetting == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(orderSetting);
    }

    /**
     *
     * 删除订单配置信息
     * @param orderSettingDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<OrderSetting> delete(@RequestBody OrderSettingDto orderSettingDto, HttpServletRequest httpServletRequest) {
        OrderSetting orderSetting = orderSettingService.delete(orderSettingDto);
        if (orderSetting == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(orderSetting);
    }

    /**
     *
     * 分页查询订单配置信息
     * @param orderSettingDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<OrderSetting>> queryPage(@RequestBody OrderSettingDto orderSettingDto) {
        Page<OrderSetting> orderSettingPage = new Page<>(orderSettingDto.getPageIndex(), orderSettingDto.getPageSize());
        IPage<OrderSetting> page = orderSettingService.queryPage(orderSettingDto, orderSettingPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询订单配置信息
     * @param orderSettingDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<OrderSetting> queryById(@RequestBody OrderSettingDto orderSettingDto) {
        OrderSetting orderSetting = orderSettingService.getById(orderSettingDto.getId());
        if (orderSetting == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(orderSetting);
    }


    /**
     *
     * 查询所有订单配置信息
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<OrderSetting>> queryAll(@RequestBody OrderSettingDto orderSettingDto) {
        List<OrderSetting> list = orderSettingService.queryAll(orderSettingDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验订单配置信息名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody OrderSettingDto orderSettingDto) {

        if (Objects.isNull(orderSettingDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (orderSettingService.checkName(orderSettingDto, orderSettingDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "订单配置信息名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:ordersetting:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderSettingService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:ordersetting:info")
    public R info(@PathVariable("id") Long id){
        OrderSetting orderSetting = orderSettingService.getById(id);

        return R.ok().put("orderSetting", orderSetting);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:ordersetting:save")
    public R save(@RequestBody OrderSetting orderSetting){
        orderSettingService.save(orderSetting);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:ordersetting:update")
    public R update(@RequestBody OrderSetting orderSetting){
        orderSettingService.updateById(orderSetting);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:ordersetting:delete")
    public R delete(@RequestBody Long[] ids){
        orderSettingService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

