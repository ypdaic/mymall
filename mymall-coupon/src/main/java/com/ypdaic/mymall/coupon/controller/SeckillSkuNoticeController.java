package com.ypdaic.mymall.coupon.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.ISeckillSkuNoticeService;
import com.ypdaic.mymall.coupon.vo.SeckillSkuNoticeDto;
import com.ypdaic.mymall.coupon.entity.SeckillSkuNotice;

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
 * 秒杀商品通知订阅 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/seckill-sku-notice")
public class SeckillSkuNoticeController extends BaseController {

    @Autowired
    ISeckillSkuNoticeService seckillSkuNoticeService;

    /**
     *
     * 新增秒杀商品通知订阅
     * @param seckillSkuNoticeDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SeckillSkuNotice> add(@RequestBody @Validated SeckillSkuNoticeDto seckillSkuNoticeDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SeckillSkuNotice seckillSkuNotice = seckillSkuNoticeService.add(seckillSkuNoticeDto);

        return ResultUtil.successOfInsert(seckillSkuNotice);
    }

    /**
     *
     * 修改秒杀商品通知订阅
     * @param seckillSkuNoticeDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SeckillSkuNotice> update(@RequestBody @Validated SeckillSkuNoticeDto seckillSkuNoticeDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SeckillSkuNotice seckillSkuNotice = seckillSkuNoticeService.update(seckillSkuNoticeDto);
        if (seckillSkuNotice == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(seckillSkuNotice);
    }

    /**
     *
     * 删除秒杀商品通知订阅
     * @param seckillSkuNoticeDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SeckillSkuNotice> delete(@RequestBody SeckillSkuNoticeDto seckillSkuNoticeDto, HttpServletRequest httpServletRequest) {
        SeckillSkuNotice seckillSkuNotice = seckillSkuNoticeService.delete(seckillSkuNoticeDto);
        if (seckillSkuNotice == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(seckillSkuNotice);
    }

    /**
     *
     * 分页查询秒杀商品通知订阅
     * @param seckillSkuNoticeDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SeckillSkuNotice>> queryPage(@RequestBody SeckillSkuNoticeDto seckillSkuNoticeDto) {
        Page<SeckillSkuNotice> seckillSkuNoticePage = new Page<>(seckillSkuNoticeDto.getPageIndex(), seckillSkuNoticeDto.getPageSize());
        IPage<SeckillSkuNotice> page = seckillSkuNoticeService.queryPage(seckillSkuNoticeDto, seckillSkuNoticePage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询秒杀商品通知订阅
     * @param seckillSkuNoticeDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SeckillSkuNotice> queryById(@RequestBody SeckillSkuNoticeDto seckillSkuNoticeDto) {
        SeckillSkuNotice seckillSkuNotice = seckillSkuNoticeService.getById(seckillSkuNoticeDto.getId());
        if (seckillSkuNotice == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(seckillSkuNotice);
    }


    /**
     *
     * 查询所有秒杀商品通知订阅
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SeckillSkuNotice>> queryAll(@RequestBody SeckillSkuNoticeDto seckillSkuNoticeDto) {
        List<SeckillSkuNotice> list = seckillSkuNoticeService.queryAll(seckillSkuNoticeDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验秒杀商品通知订阅名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SeckillSkuNoticeDto seckillSkuNoticeDto) {

        if (Objects.isNull(seckillSkuNoticeDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (seckillSkuNoticeService.checkName(seckillSkuNoticeDto, seckillSkuNoticeDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "秒杀商品通知订阅名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:seckillskunotice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillSkuNoticeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:seckillskunotice:info")
    public R info(@PathVariable("id") Long id){
        SeckillSkuNotice seckillSkuNotice = seckillSkuNoticeService.getById(id);

        return R.ok().put("seckillSkuNotice", seckillSkuNotice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:seckillskunotice:save")
    public R save(@RequestBody SeckillSkuNotice seckillSkuNotice){
        seckillSkuNoticeService.save(seckillSkuNotice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:seckillskunotice:update")
    public R update(@RequestBody SeckillSkuNotice seckillSkuNotice){
        seckillSkuNoticeService.updateById(seckillSkuNotice);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:seckillskunotice:delete")
    public R delete(@RequestBody Long[] ids){
        seckillSkuNoticeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

