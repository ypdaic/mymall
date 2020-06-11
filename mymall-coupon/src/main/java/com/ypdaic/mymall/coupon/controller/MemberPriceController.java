package com.ypdaic.mymall.coupon.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.IMemberPriceService;
import com.ypdaic.mymall.coupon.vo.MemberPriceDto;
import com.ypdaic.mymall.coupon.entity.MemberPrice;

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
 * 商品会员价格 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/memberprice")
public class MemberPriceController extends BaseController {

    @Autowired
    IMemberPriceService memberPriceService;

    /**
     *
     * 新增商品会员价格
     * @param memberPriceDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<MemberPrice> add(@RequestBody @Validated MemberPriceDto memberPriceDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        MemberPrice memberPrice = memberPriceService.add(memberPriceDto);

        return ResultUtil.successOfInsert(memberPrice);
    }

    /**
     *
     * 修改商品会员价格
     * @param memberPriceDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<MemberPrice> update(@RequestBody @Validated MemberPriceDto memberPriceDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        MemberPrice memberPrice = memberPriceService.update(memberPriceDto);
        if (memberPrice == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(memberPrice);
    }

    /**
     *
     * 删除商品会员价格
     * @param memberPriceDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<MemberPrice> delete(@RequestBody MemberPriceDto memberPriceDto, HttpServletRequest httpServletRequest) {
        MemberPrice memberPrice = memberPriceService.delete(memberPriceDto);
        if (memberPrice == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(memberPrice);
    }

    /**
     *
     * 分页查询商品会员价格
     * @param memberPriceDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<MemberPrice>> queryPage(@RequestBody MemberPriceDto memberPriceDto) {
        Page<MemberPrice> memberPricePage = new Page<>(memberPriceDto.getPageIndex(), memberPriceDto.getPageSize());
        IPage<MemberPrice> page = memberPriceService.queryPage(memberPriceDto, memberPricePage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询商品会员价格
     * @param memberPriceDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<MemberPrice> queryById(@RequestBody MemberPriceDto memberPriceDto) {
        MemberPrice memberPrice = memberPriceService.getById(memberPriceDto.getId());
        if (memberPrice == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(memberPrice);
    }


    /**
     *
     * 查询所有商品会员价格
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<MemberPrice>> queryAll(@RequestBody MemberPriceDto memberPriceDto) {
        List<MemberPrice> list = memberPriceService.queryAll(memberPriceDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验商品会员价格名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody MemberPriceDto memberPriceDto) {

        if (Objects.isNull(memberPriceDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (memberPriceService.checkName(memberPriceDto, memberPriceDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "商品会员价格名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:memberprice:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberPriceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:memberprice:info")
    public R info(@PathVariable("id") Long id){
        MemberPrice memberPrice = memberPriceService.getById(id);

        return R.ok().put("memberPrice", memberPrice);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:memberprice:save")
    public R save(@RequestBody MemberPrice memberPrice){
        memberPriceService.save(memberPrice);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:memberprice:update")
    public R update(@RequestBody MemberPrice memberPrice){
        memberPriceService.updateById(memberPrice);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:memberprice:delete")
    public R delete(@RequestBody Long[] ids){
        memberPriceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

