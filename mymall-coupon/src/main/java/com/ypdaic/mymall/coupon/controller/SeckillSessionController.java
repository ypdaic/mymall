package com.ypdaic.mymall.coupon.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.ISeckillSessionService;
import com.ypdaic.mymall.coupon.vo.SeckillSessionDto;
import com.ypdaic.mymall.coupon.entity.SeckillSession;

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
 * 秒杀活动场次 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/seckillsession")
public class SeckillSessionController extends BaseController {

    @Autowired
    ISeckillSessionService seckillSessionService;

    /**
     *
     * 新增秒杀活动场次
     * @param seckillSessionDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SeckillSession> add(@RequestBody @Validated SeckillSessionDto seckillSessionDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SeckillSession seckillSession = seckillSessionService.add(seckillSessionDto);

        return ResultUtil.successOfInsert(seckillSession);
    }

    /**
     *
     * 修改秒杀活动场次
     * @param seckillSessionDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SeckillSession> update(@RequestBody @Validated SeckillSessionDto seckillSessionDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SeckillSession seckillSession = seckillSessionService.update(seckillSessionDto);
        if (seckillSession == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(seckillSession);
    }

    /**
     *
     * 删除秒杀活动场次
     * @param seckillSessionDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SeckillSession> delete(@RequestBody SeckillSessionDto seckillSessionDto, HttpServletRequest httpServletRequest) {
        SeckillSession seckillSession = seckillSessionService.delete(seckillSessionDto);
        if (seckillSession == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(seckillSession);
    }

    /**
     *
     * 分页查询秒杀活动场次
     * @param seckillSessionDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SeckillSession>> queryPage(@RequestBody SeckillSessionDto seckillSessionDto) {
        Page<SeckillSession> seckillSessionPage = new Page<>(seckillSessionDto.getPageIndex(), seckillSessionDto.getPageSize());
        IPage<SeckillSession> page = seckillSessionService.queryPage(seckillSessionDto, seckillSessionPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询秒杀活动场次
     * @param seckillSessionDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SeckillSession> queryById(@RequestBody SeckillSessionDto seckillSessionDto) {
        SeckillSession seckillSession = seckillSessionService.getById(seckillSessionDto.getId());
        if (seckillSession == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(seckillSession);
    }


    /**
     *
     * 查询所有秒杀活动场次
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SeckillSession>> queryAll(@RequestBody SeckillSessionDto seckillSessionDto) {
        List<SeckillSession> list = seckillSessionService.queryAll(seckillSessionDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验秒杀活动场次名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SeckillSessionDto seckillSessionDto) {

        if (Objects.isNull(seckillSessionDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (seckillSessionService.checkName(seckillSessionDto, seckillSessionDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "秒杀活动场次名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:seckillsession:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillSessionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:seckillsession:info")
    public R info(@PathVariable("id") Long id){
        SeckillSession seckillSession = seckillSessionService.getById(id);

        return R.ok().put("seckillSession", seckillSession);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:seckillsession:save")
    public R save(@RequestBody SeckillSession seckillSession){
        seckillSessionService.save(seckillSession);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:seckillsession:update")
    public R update(@RequestBody SeckillSession seckillSession){
        seckillSessionService.updateById(seckillSession);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:seckillsession:delete")
    public R delete(@RequestBody Long[] ids){
        seckillSessionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @GetMapping("/getLates3DaySession")
    public R getLates3DaySession() {
        List<SeckillSession> seckillSessions = seckillSessionService.getLates3DaySession();
        return R.ok().setData(seckillSessions);
    }


}

