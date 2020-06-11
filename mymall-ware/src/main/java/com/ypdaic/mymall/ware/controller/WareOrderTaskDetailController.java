package com.ypdaic.mymall.ware.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.ware.service.IWareOrderTaskDetailService;
import com.ypdaic.mymall.ware.vo.WareOrderTaskDetailDto;
import com.ypdaic.mymall.ware.entity.WareOrderTaskDetail;

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
@RequestMapping("/ware/ware-order-task-detail")
public class WareOrderTaskDetailController extends BaseController {

    @Autowired
    IWareOrderTaskDetailService wareOrderTaskDetailService;

    /**
     *
     * 新增库存工作单
     * @param wareOrderTaskDetailDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<WareOrderTaskDetail> add(@RequestBody @Validated WareOrderTaskDetailDto wareOrderTaskDetailDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        WareOrderTaskDetail wareOrderTaskDetail = wareOrderTaskDetailService.add(wareOrderTaskDetailDto);

        return ResultUtil.successOfInsert(wareOrderTaskDetail);
    }

    /**
     *
     * 修改库存工作单
     * @param wareOrderTaskDetailDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<WareOrderTaskDetail> update(@RequestBody @Validated WareOrderTaskDetailDto wareOrderTaskDetailDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        WareOrderTaskDetail wareOrderTaskDetail = wareOrderTaskDetailService.update(wareOrderTaskDetailDto);
        if (wareOrderTaskDetail == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(wareOrderTaskDetail);
    }

    /**
     *
     * 删除库存工作单
     * @param wareOrderTaskDetailDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<WareOrderTaskDetail> delete(@RequestBody WareOrderTaskDetailDto wareOrderTaskDetailDto, HttpServletRequest httpServletRequest) {
        WareOrderTaskDetail wareOrderTaskDetail = wareOrderTaskDetailService.delete(wareOrderTaskDetailDto);
        if (wareOrderTaskDetail == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(wareOrderTaskDetail);
    }

    /**
     *
     * 分页查询库存工作单
     * @param wareOrderTaskDetailDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<WareOrderTaskDetail>> queryPage(@RequestBody WareOrderTaskDetailDto wareOrderTaskDetailDto) {
        Page<WareOrderTaskDetail> wareOrderTaskDetailPage = new Page<>(wareOrderTaskDetailDto.getPageIndex(), wareOrderTaskDetailDto.getPageSize());
        IPage<WareOrderTaskDetail> page = wareOrderTaskDetailService.queryPage(wareOrderTaskDetailDto, wareOrderTaskDetailPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询库存工作单
     * @param wareOrderTaskDetailDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<WareOrderTaskDetail> queryById(@RequestBody WareOrderTaskDetailDto wareOrderTaskDetailDto) {
        WareOrderTaskDetail wareOrderTaskDetail = wareOrderTaskDetailService.getById(wareOrderTaskDetailDto.getId());
        if (wareOrderTaskDetail == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(wareOrderTaskDetail);
    }


    /**
     *
     * 查询所有库存工作单
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<WareOrderTaskDetail>> queryAll(@RequestBody WareOrderTaskDetailDto wareOrderTaskDetailDto) {
        List<WareOrderTaskDetail> list = wareOrderTaskDetailService.queryAll(wareOrderTaskDetailDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验库存工作单名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody WareOrderTaskDetailDto wareOrderTaskDetailDto) {

        if (Objects.isNull(wareOrderTaskDetailDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (wareOrderTaskDetailService.checkName(wareOrderTaskDetailDto, wareOrderTaskDetailDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "库存工作单名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ware:wareordertaskdetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wareOrderTaskDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ware:wareordertaskdetail:info")
    public R info(@PathVariable("id") Long id){
        WareOrderTaskDetail wareOrderTaskDetail = wareOrderTaskDetailService.getById(id);

        return R.ok().put("wareOrderTaskDetail", wareOrderTaskDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ware:wareordertaskdetail:save")
    public R save(@RequestBody WareOrderTaskDetail wareOrderTaskDetail){
        wareOrderTaskDetailService.save(wareOrderTaskDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ware:wareordertaskdetail:update")
    public R update(@RequestBody WareOrderTaskDetail wareOrderTaskDetail){
        wareOrderTaskDetailService.updateById(wareOrderTaskDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:wareordertaskdetail:delete")
    public R delete(@RequestBody Long[] ids){
        wareOrderTaskDetailService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

