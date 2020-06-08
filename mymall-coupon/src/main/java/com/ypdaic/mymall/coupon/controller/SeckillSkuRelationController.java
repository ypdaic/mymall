package com.ypdaic.mymall.coupon.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.ISeckillSkuRelationService;
import com.ypdaic.mymall.coupon.vo.SeckillSkuRelationDto;
import com.ypdaic.mymall.coupon.entity.SeckillSkuRelation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import java.util.List;
import java.util.Objects;

/**
 *
 * 秒杀活动商品关联 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/seckill-sku-relation")
public class SeckillSkuRelationController extends BaseController {

    @Autowired
    ISeckillSkuRelationService seckillSkuRelationService;

    /**
     *
     * 新增秒杀活动商品关联
     * @param seckillSkuRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SeckillSkuRelation> add(@RequestBody @Validated SeckillSkuRelationDto seckillSkuRelationDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SeckillSkuRelation seckillSkuRelation = seckillSkuRelationService.add(seckillSkuRelationDto);

        return ResultUtil.successOfInsert(seckillSkuRelation);
    }

    /**
     *
     * 修改秒杀活动商品关联
     * @param seckillSkuRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SeckillSkuRelation> update(@RequestBody @Validated SeckillSkuRelationDto seckillSkuRelationDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SeckillSkuRelation seckillSkuRelation = seckillSkuRelationService.update(seckillSkuRelationDto);
        if (seckillSkuRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(seckillSkuRelation);
    }

    /**
     *
     * 删除秒杀活动商品关联
     * @param seckillSkuRelationDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SeckillSkuRelation> delete(@RequestBody SeckillSkuRelationDto seckillSkuRelationDto, HttpServletRequest httpServletRequest) {
        SeckillSkuRelation seckillSkuRelation = seckillSkuRelationService.delete(seckillSkuRelationDto);
        if (seckillSkuRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(seckillSkuRelation);
    }

    /**
     *
     * 分页查询秒杀活动商品关联
     * @param seckillSkuRelationDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SeckillSkuRelation>> queryPage(@RequestBody SeckillSkuRelationDto seckillSkuRelationDto) {
        Page<SeckillSkuRelation> seckillSkuRelationPage = new Page<>(seckillSkuRelationDto.getPageIndex(), seckillSkuRelationDto.getPageSize());
        IPage<SeckillSkuRelation> page = seckillSkuRelationService.queryPage(seckillSkuRelationDto, seckillSkuRelationPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询秒杀活动商品关联
     * @param seckillSkuRelationDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SeckillSkuRelation> queryById(@RequestBody SeckillSkuRelationDto seckillSkuRelationDto) {
        SeckillSkuRelation seckillSkuRelation = seckillSkuRelationService.getById(seckillSkuRelationDto.getId());
        if (seckillSkuRelation == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(seckillSkuRelation);
    }


    /**
     *
     * 查询所有秒杀活动商品关联
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SeckillSkuRelation>> queryAll(@RequestBody SeckillSkuRelationDto seckillSkuRelationDto) {
        List<SeckillSkuRelation> list = seckillSkuRelationService.queryAll(seckillSkuRelationDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验秒杀活动商品关联名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SeckillSkuRelationDto seckillSkuRelationDto) {

        if (Objects.isNull(seckillSkuRelationDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (seckillSkuRelationService.checkName(seckillSkuRelationDto, seckillSkuRelationDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "秒杀活动商品关联名称已存在！");
    }

}

