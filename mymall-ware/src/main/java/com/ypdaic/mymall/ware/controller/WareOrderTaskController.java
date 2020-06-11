package com.ypdaic.mymall.ware.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.ware.service.IWareOrderTaskService;
import com.ypdaic.mymall.ware.vo.WareOrderTaskDto;
import com.ypdaic.mymall.ware.entity.WareOrderTask;

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
 * 库存工作单 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/ware/wareordertask")
public class WareOrderTaskController extends BaseController {

    @Autowired
    IWareOrderTaskService wareOrderTaskService;

    /**
     *
     * 新增库存工作单
     * @param wareOrderTaskDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<WareOrderTask> add(@RequestBody @Validated WareOrderTaskDto wareOrderTaskDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        WareOrderTask wareOrderTask = wareOrderTaskService.add(wareOrderTaskDto);

        return ResultUtil.successOfInsert(wareOrderTask);
    }

    /**
     *
     * 修改库存工作单
     * @param wareOrderTaskDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<WareOrderTask> update(@RequestBody @Validated WareOrderTaskDto wareOrderTaskDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        WareOrderTask wareOrderTask = wareOrderTaskService.update(wareOrderTaskDto);
        if (wareOrderTask == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(wareOrderTask);
    }

    /**
     *
     * 删除库存工作单
     * @param wareOrderTaskDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<WareOrderTask> delete(@RequestBody WareOrderTaskDto wareOrderTaskDto, HttpServletRequest httpServletRequest) {
        WareOrderTask wareOrderTask = wareOrderTaskService.delete(wareOrderTaskDto);
        if (wareOrderTask == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(wareOrderTask);
    }

    /**
     *
     * 分页查询库存工作单
     * @param wareOrderTaskDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<WareOrderTask>> queryPage(@RequestBody WareOrderTaskDto wareOrderTaskDto) {
        Page<WareOrderTask> wareOrderTaskPage = new Page<>(wareOrderTaskDto.getPageIndex(), wareOrderTaskDto.getPageSize());
        IPage<WareOrderTask> page = wareOrderTaskService.queryPage(wareOrderTaskDto, wareOrderTaskPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询库存工作单
     * @param wareOrderTaskDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<WareOrderTask> queryById(@RequestBody WareOrderTaskDto wareOrderTaskDto) {
        WareOrderTask wareOrderTask = wareOrderTaskService.getById(wareOrderTaskDto.getId());
        if (wareOrderTask == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(wareOrderTask);
    }


    /**
     *
     * 查询所有库存工作单
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<WareOrderTask>> queryAll(@RequestBody WareOrderTaskDto wareOrderTaskDto) {
        List<WareOrderTask> list = wareOrderTaskService.queryAll(wareOrderTaskDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验库存工作单名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody WareOrderTaskDto wareOrderTaskDto) {

        if (Objects.isNull(wareOrderTaskDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (wareOrderTaskService.checkName(wareOrderTaskDto, wareOrderTaskDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "库存工作单名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ware:wareordertask:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wareOrderTaskService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ware:wareordertask:info")
    public R info(@PathVariable("id") Long id){
        WareOrderTask wareOrderTask = wareOrderTaskService.getById(id);

        return R.ok().put("wareOrderTask", wareOrderTask);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ware:wareordertask:save")
    public R save(@RequestBody WareOrderTask wareOrderTask){
        wareOrderTaskService.save(wareOrderTask);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ware:wareordertask:update")
    public R update(@RequestBody WareOrderTask wareOrderTask){
        wareOrderTaskService.updateById(wareOrderTask);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:wareordertask:delete")
    public R delete(@RequestBody Long[] ids){
        wareOrderTaskService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

