package com.ypdaic.mymall.coupon.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.IHomeAdvService;
import com.ypdaic.mymall.coupon.vo.HomeAdvDto;
import com.ypdaic.mymall.coupon.entity.HomeAdv;

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
 * 首页轮播广告 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/home-adv")
public class HomeAdvController extends BaseController {

    @Autowired
    IHomeAdvService homeAdvService;

    /**
     *
     * 新增首页轮播广告
     * @param homeAdvDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<HomeAdv> add(@RequestBody @Validated HomeAdvDto homeAdvDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        HomeAdv homeAdv = homeAdvService.add(homeAdvDto);

        return ResultUtil.successOfInsert(homeAdv);
    }

    /**
     *
     * 修改首页轮播广告
     * @param homeAdvDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<HomeAdv> update(@RequestBody @Validated HomeAdvDto homeAdvDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        HomeAdv homeAdv = homeAdvService.update(homeAdvDto);
        if (homeAdv == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(homeAdv);
    }

    /**
     *
     * 删除首页轮播广告
     * @param homeAdvDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<HomeAdv> delete(@RequestBody HomeAdvDto homeAdvDto, HttpServletRequest httpServletRequest) {
        HomeAdv homeAdv = homeAdvService.delete(homeAdvDto);
        if (homeAdv == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(homeAdv);
    }

    /**
     *
     * 分页查询首页轮播广告
     * @param homeAdvDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<HomeAdv>> queryPage(@RequestBody HomeAdvDto homeAdvDto) {
        Page<HomeAdv> homeAdvPage = new Page<>(homeAdvDto.getPageIndex(), homeAdvDto.getPageSize());
        IPage<HomeAdv> page = homeAdvService.queryPage(homeAdvDto, homeAdvPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询首页轮播广告
     * @param homeAdvDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<HomeAdv> queryById(@RequestBody HomeAdvDto homeAdvDto) {
        HomeAdv homeAdv = homeAdvService.getById(homeAdvDto.getId());
        if (homeAdv == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(homeAdv);
    }


    /**
     *
     * 查询所有首页轮播广告
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<HomeAdv>> queryAll(@RequestBody HomeAdvDto homeAdvDto) {
        List<HomeAdv> list = homeAdvService.queryAll(homeAdvDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验首页轮播广告名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody HomeAdvDto homeAdvDto) {

        if (Objects.isNull(homeAdvDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (homeAdvService.checkName(homeAdvDto, homeAdvDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "首页轮播广告名称已存在！");
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:homeadv:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = homeAdvService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:homeadv:info")
    public R info(@PathVariable("id") Long id){
        HomeAdv homeAdv = homeAdvService.getById(id);

        return R.ok().put("homeAdv", homeAdv);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:homeadv:save")
    public R save(@RequestBody HomeAdv homeAdv){
        homeAdvService.save(homeAdv);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:homeadv:update")
    public R update(@RequestBody HomeAdv homeAdv){
        homeAdvService.updateById(homeAdv);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:homeadv:delete")
    public R delete(@RequestBody Long[] ids){
        homeAdvService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

