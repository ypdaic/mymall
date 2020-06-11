package com.ypdaic.mymall.order.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.order.service.IRefundInfoService;
import com.ypdaic.mymall.order.vo.RefundInfoDto;
import com.ypdaic.mymall.order.entity.RefundInfo;

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
 * 退款信息 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/order/refund-info")
public class RefundInfoController extends BaseController {

    @Autowired
    IRefundInfoService refundInfoService;

    /**
     *
     * 新增退款信息
     * @param refundInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<RefundInfo> add(@RequestBody @Validated RefundInfoDto refundInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        RefundInfo refundInfo = refundInfoService.add(refundInfoDto);

        return ResultUtil.successOfInsert(refundInfo);
    }

    /**
     *
     * 修改退款信息
     * @param refundInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<RefundInfo> update(@RequestBody @Validated RefundInfoDto refundInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        RefundInfo refundInfo = refundInfoService.update(refundInfoDto);
        if (refundInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(refundInfo);
    }

    /**
     *
     * 删除退款信息
     * @param refundInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<RefundInfo> delete(@RequestBody RefundInfoDto refundInfoDto, HttpServletRequest httpServletRequest) {
        RefundInfo refundInfo = refundInfoService.delete(refundInfoDto);
        if (refundInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(refundInfo);
    }

    /**
     *
     * 分页查询退款信息
     * @param refundInfoDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<RefundInfo>> queryPage(@RequestBody RefundInfoDto refundInfoDto) {
        Page<RefundInfo> refundInfoPage = new Page<>(refundInfoDto.getPageIndex(), refundInfoDto.getPageSize());
        IPage<RefundInfo> page = refundInfoService.queryPage(refundInfoDto, refundInfoPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询退款信息
     * @param refundInfoDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<RefundInfo> queryById(@RequestBody RefundInfoDto refundInfoDto) {
        RefundInfo refundInfo = refundInfoService.getById(refundInfoDto.getId());
        if (refundInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(refundInfo);
    }


    /**
     *
     * 查询所有退款信息
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<RefundInfo>> queryAll(@RequestBody RefundInfoDto refundInfoDto) {
        List<RefundInfo> list = refundInfoService.queryAll(refundInfoDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验退款信息名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody RefundInfoDto refundInfoDto) {

        if (Objects.isNull(refundInfoDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (refundInfoService.checkName(refundInfoDto, refundInfoDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "退款信息名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:refundinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = refundInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:refundinfo:info")
    public R info(@PathVariable("id") Long id){
        RefundInfo refundInfo = refundInfoService.getById(id);

        return R.ok().put("refundInfo", refundInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:refundinfo:save")
    public R save(@RequestBody RefundInfo refundInfo){
        refundInfoService.save(refundInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:refundinfo:update")
    public R update(@RequestBody RefundInfo refundInfo){
        refundInfoService.updateById(refundInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("order:refundinfo:delete")
    public R delete(@RequestBody Long[] ids){
        refundInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

