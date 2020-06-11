package com.ypdaic.mymall.coupon.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.IHomeSubjectSpuService;
import com.ypdaic.mymall.coupon.vo.HomeSubjectSpuDto;
import com.ypdaic.mymall.coupon.entity.HomeSubjectSpu;

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
 * 专题商品 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/home-subject-spu")
public class HomeSubjectSpuController extends BaseController {

    @Autowired
    IHomeSubjectSpuService homeSubjectSpuService;

    /**
     *
     * 新增专题商品
     * @param homeSubjectSpuDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<HomeSubjectSpu> add(@RequestBody @Validated HomeSubjectSpuDto homeSubjectSpuDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        HomeSubjectSpu homeSubjectSpu = homeSubjectSpuService.add(homeSubjectSpuDto);

        return ResultUtil.successOfInsert(homeSubjectSpu);
    }

    /**
     *
     * 修改专题商品
     * @param homeSubjectSpuDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<HomeSubjectSpu> update(@RequestBody @Validated HomeSubjectSpuDto homeSubjectSpuDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        HomeSubjectSpu homeSubjectSpu = homeSubjectSpuService.update(homeSubjectSpuDto);
        if (homeSubjectSpu == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(homeSubjectSpu);
    }

    /**
     *
     * 删除专题商品
     * @param homeSubjectSpuDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<HomeSubjectSpu> delete(@RequestBody HomeSubjectSpuDto homeSubjectSpuDto, HttpServletRequest httpServletRequest) {
        HomeSubjectSpu homeSubjectSpu = homeSubjectSpuService.delete(homeSubjectSpuDto);
        if (homeSubjectSpu == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(homeSubjectSpu);
    }

    /**
     *
     * 分页查询专题商品
     * @param homeSubjectSpuDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<HomeSubjectSpu>> queryPage(@RequestBody HomeSubjectSpuDto homeSubjectSpuDto) {
        Page<HomeSubjectSpu> homeSubjectSpuPage = new Page<>(homeSubjectSpuDto.getPageIndex(), homeSubjectSpuDto.getPageSize());
        IPage<HomeSubjectSpu> page = homeSubjectSpuService.queryPage(homeSubjectSpuDto, homeSubjectSpuPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询专题商品
     * @param homeSubjectSpuDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<HomeSubjectSpu> queryById(@RequestBody HomeSubjectSpuDto homeSubjectSpuDto) {
        HomeSubjectSpu homeSubjectSpu = homeSubjectSpuService.getById(homeSubjectSpuDto.getId());
        if (homeSubjectSpu == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(homeSubjectSpu);
    }


    /**
     *
     * 查询所有专题商品
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<HomeSubjectSpu>> queryAll(@RequestBody HomeSubjectSpuDto homeSubjectSpuDto) {
        List<HomeSubjectSpu> list = homeSubjectSpuService.queryAll(homeSubjectSpuDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验专题商品名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody HomeSubjectSpuDto homeSubjectSpuDto) {

        if (Objects.isNull(homeSubjectSpuDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (homeSubjectSpuService.checkName(homeSubjectSpuDto, homeSubjectSpuDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "专题商品名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:homesubjectspu:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = homeSubjectSpuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:homesubjectspu:info")
    public R info(@PathVariable("id") Long id){
        HomeSubjectSpu homeSubjectSpu = homeSubjectSpuService.getById(id);

        return R.ok().put("homeSubjectSpu", homeSubjectSpu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:homesubjectspu:save")
    public R save(@RequestBody HomeSubjectSpu homeSubjectSpu){
        homeSubjectSpuService.save(homeSubjectSpu);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:homesubjectspu:update")
    public R update(@RequestBody HomeSubjectSpu homeSubjectSpu){
        homeSubjectSpuService.updateById(homeSubjectSpu);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:homesubjectspu:delete")
    public R delete(@RequestBody Long[] ids){
        homeSubjectSpuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

