package com.ypdaic.mymall.order.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.order.service.IOrderReturnReasonService;
import com.ypdaic.mymall.order.vo.OrderReturnReasonDto;
import com.ypdaic.mymall.order.entity.OrderReturnReason;

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
 * 退货原因 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/order/order-return-reason")
public class OrderReturnReasonController extends BaseController {

    @Autowired
    IOrderReturnReasonService orderReturnReasonService;

    /**
     *
     * 新增退货原因
     * @param orderReturnReasonDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<OrderReturnReason> add(@RequestBody @Validated OrderReturnReasonDto orderReturnReasonDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        OrderReturnReason orderReturnReason = orderReturnReasonService.add(orderReturnReasonDto);

        return ResultUtil.successOfInsert(orderReturnReason);
    }

    /**
     *
     * 修改退货原因
     * @param orderReturnReasonDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<OrderReturnReason> update(@RequestBody @Validated OrderReturnReasonDto orderReturnReasonDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        OrderReturnReason orderReturnReason = orderReturnReasonService.update(orderReturnReasonDto);
        if (orderReturnReason == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(orderReturnReason);
    }

    /**
     *
     * 删除退货原因
     * @param orderReturnReasonDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<OrderReturnReason> delete(@RequestBody OrderReturnReasonDto orderReturnReasonDto, HttpServletRequest httpServletRequest) {
        OrderReturnReason orderReturnReason = orderReturnReasonService.delete(orderReturnReasonDto);
        if (orderReturnReason == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(orderReturnReason);
    }

    /**
     *
     * 分页查询退货原因
     * @param orderReturnReasonDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<OrderReturnReason>> queryPage(@RequestBody OrderReturnReasonDto orderReturnReasonDto) {
        Page<OrderReturnReason> orderReturnReasonPage = new Page<>(orderReturnReasonDto.getPageIndex(), orderReturnReasonDto.getPageSize());
        IPage<OrderReturnReason> page = orderReturnReasonService.queryPage(orderReturnReasonDto, orderReturnReasonPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询退货原因
     * @param orderReturnReasonDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<OrderReturnReason> queryById(@RequestBody OrderReturnReasonDto orderReturnReasonDto) {
        OrderReturnReason orderReturnReason = orderReturnReasonService.getById(orderReturnReasonDto.getId());
        if (orderReturnReason == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(orderReturnReason);
    }


    /**
     *
     * 查询所有退货原因
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<OrderReturnReason>> queryAll(@RequestBody OrderReturnReasonDto orderReturnReasonDto) {
        List<OrderReturnReason> list = orderReturnReasonService.queryAll(orderReturnReasonDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验退货原因名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody OrderReturnReasonDto orderReturnReasonDto) {

        if (Objects.isNull(orderReturnReasonDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (orderReturnReasonService.checkName(orderReturnReasonDto, orderReturnReasonDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "退货原因名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:orderreturnreason:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderReturnReasonService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:orderreturnreason:info")
    public R info(@PathVariable("id") Long id){
        OrderReturnReason orderReturnReason = orderReturnReasonService.getById(id);

        return R.ok().put("orderReturnReason", orderReturnReason);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:orderreturnreason:save")
    public R save(@RequestBody OrderReturnReason orderReturnReason){
        orderReturnReasonService.save(orderReturnReason);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:orderreturnreason:update")
    public R update(@RequestBody OrderReturnReason orderReturnReason){
        orderReturnReasonService.updateById(orderReturnReason);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:orderreturnreason:delete")
    public R delete(@RequestBody Long[] ids){
        orderReturnReasonService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

