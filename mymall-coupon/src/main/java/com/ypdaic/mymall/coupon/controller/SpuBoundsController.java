package com.ypdaic.mymall.coupon.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.ISpuBoundsService;
import com.ypdaic.mymall.coupon.vo.SpuBoundsDto;
import com.ypdaic.mymall.coupon.entity.SpuBounds;

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
 * 商品spu积分设置 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-10
 */
@RestController
@RequestMapping("/coupon/spubounds")
public class SpuBoundsController extends BaseController {

    @Autowired
    ISpuBoundsService spuBoundsService;

    /**
     *
     * 新增商品spu积分设置
     * @param spuBoundsDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SpuBounds> add(@RequestBody @Validated SpuBoundsDto spuBoundsDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SpuBounds spuBounds = spuBoundsService.add(spuBoundsDto);

        return ResultUtil.successOfInsert(spuBounds);
    }

    /**
     *
     * 修改商品spu积分设置
     * @param spuBoundsDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SpuBounds> update(@RequestBody @Validated SpuBoundsDto spuBoundsDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SpuBounds spuBounds = spuBoundsService.update(spuBoundsDto);
        if (spuBounds == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(spuBounds);
    }

    /**
     *
     * 删除商品spu积分设置
     * @param spuBoundsDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SpuBounds> delete(@RequestBody SpuBoundsDto spuBoundsDto, HttpServletRequest httpServletRequest) {
        SpuBounds spuBounds = spuBoundsService.delete(spuBoundsDto);
        if (spuBounds == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(spuBounds);
    }

    /**
     *
     * 分页查询商品spu积分设置
     * @param spuBoundsDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SpuBounds>> queryPage(@RequestBody SpuBoundsDto spuBoundsDto) {
        Page<SpuBounds> spuBoundsPage = new Page<>(spuBoundsDto.getPageIndex(), spuBoundsDto.getPageSize());
        IPage<SpuBounds> page = spuBoundsService.queryPage(spuBoundsDto, spuBoundsPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询商品spu积分设置
     * @param spuBoundsDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SpuBounds> queryById(@RequestBody SpuBoundsDto spuBoundsDto) {
        SpuBounds spuBounds = spuBoundsService.getById(spuBoundsDto.getId());
        if (spuBounds == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(spuBounds);
    }


    /**
     *
     * 查询所有商品spu积分设置
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SpuBounds>> queryAll(@RequestBody SpuBoundsDto spuBoundsDto) {
        List<SpuBounds> list = spuBoundsService.queryAll(spuBoundsDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验商品spu积分设置名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SpuBoundsDto spuBoundsDto) {

        if (Objects.isNull(spuBoundsDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (spuBoundsService.checkName(spuBoundsDto, spuBoundsDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "商品spu积分设置名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:spubounds:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuBoundsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:spubounds:info")
    public R info(@PathVariable("id") Long id){
        SpuBounds spuBounds = spuBoundsService.getById(id);

        return R.ok().put("spuBounds", spuBounds);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("coupon:spubounds:save")
    public R save(@RequestBody SpuBounds spuBounds){
        spuBoundsService.save(spuBounds);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:spubounds:update")
    public R update(@RequestBody SpuBounds spuBounds){
        spuBoundsService.updateById(spuBounds);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:spubounds:delete")
    public R delete(@RequestBody Long[] ids){
        spuBoundsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

