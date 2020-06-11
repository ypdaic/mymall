package com.ypdaic.mymall.member.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.member.service.IGrowthChangeHistoryService;
import com.ypdaic.mymall.member.vo.GrowthChangeHistoryDto;
import com.ypdaic.mymall.member.entity.GrowthChangeHistory;

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
 * 成长值变化历史记录 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/member/growth-change-history")
public class GrowthChangeHistoryController extends BaseController {

    @Autowired
    IGrowthChangeHistoryService growthChangeHistoryService;

    /**
     *
     * 新增成长值变化历史记录
     * @param growthChangeHistoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<GrowthChangeHistory> add(@RequestBody @Validated GrowthChangeHistoryDto growthChangeHistoryDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        GrowthChangeHistory growthChangeHistory = growthChangeHistoryService.add(growthChangeHistoryDto);

        return ResultUtil.successOfInsert(growthChangeHistory);
    }

    /**
     *
     * 修改成长值变化历史记录
     * @param growthChangeHistoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<GrowthChangeHistory> update(@RequestBody @Validated GrowthChangeHistoryDto growthChangeHistoryDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        GrowthChangeHistory growthChangeHistory = growthChangeHistoryService.update(growthChangeHistoryDto);
        if (growthChangeHistory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(growthChangeHistory);
    }

    /**
     *
     * 删除成长值变化历史记录
     * @param growthChangeHistoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<GrowthChangeHistory> delete(@RequestBody GrowthChangeHistoryDto growthChangeHistoryDto, HttpServletRequest httpServletRequest) {
        GrowthChangeHistory growthChangeHistory = growthChangeHistoryService.delete(growthChangeHistoryDto);
        if (growthChangeHistory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(growthChangeHistory);
    }

    /**
     *
     * 分页查询成长值变化历史记录
     * @param growthChangeHistoryDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<GrowthChangeHistory>> queryPage(@RequestBody GrowthChangeHistoryDto growthChangeHistoryDto) {
        Page<GrowthChangeHistory> growthChangeHistoryPage = new Page<>(growthChangeHistoryDto.getPageIndex(), growthChangeHistoryDto.getPageSize());
        IPage<GrowthChangeHistory> page = growthChangeHistoryService.queryPage(growthChangeHistoryDto, growthChangeHistoryPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询成长值变化历史记录
     * @param growthChangeHistoryDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<GrowthChangeHistory> queryById(@RequestBody GrowthChangeHistoryDto growthChangeHistoryDto) {
        GrowthChangeHistory growthChangeHistory = growthChangeHistoryService.getById(growthChangeHistoryDto.getId());
        if (growthChangeHistory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(growthChangeHistory);
    }


    /**
     *
     * 查询所有成长值变化历史记录
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<GrowthChangeHistory>> queryAll(@RequestBody GrowthChangeHistoryDto growthChangeHistoryDto) {
        List<GrowthChangeHistory> list = growthChangeHistoryService.queryAll(growthChangeHistoryDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验成长值变化历史记录名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody GrowthChangeHistoryDto growthChangeHistoryDto) {

        if (Objects.isNull(growthChangeHistoryDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (growthChangeHistoryService.checkName(growthChangeHistoryDto, growthChangeHistoryDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "成长值变化历史记录名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:growthchangehistory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = growthChangeHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:growthchangehistory:info")
    public R info(@PathVariable("id") Long id){
        GrowthChangeHistory growthChangeHistory = growthChangeHistoryService.getById(id);

        return R.ok().put("growthChangeHistory", growthChangeHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:growthchangehistory:save")
    public R save(@RequestBody GrowthChangeHistory growthChangeHistory){
        growthChangeHistoryService.save(growthChangeHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:growthchangehistory:update")
    public R update(@RequestBody GrowthChangeHistory growthChangeHistory){
        growthChangeHistoryService.updateById(growthChangeHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:growthchangehistory:delete")
    public R delete(@RequestBody Long[] ids){
        growthChangeHistoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

