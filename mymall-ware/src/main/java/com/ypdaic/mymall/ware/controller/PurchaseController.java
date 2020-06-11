package com.ypdaic.mymall.ware.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.ware.vo.MergeVo;
import com.ypdaic.mymall.ware.vo.PurchaseDoneVo;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.ware.service.IPurchaseService;
import com.ypdaic.mymall.ware.vo.PurchaseDto;
import com.ypdaic.mymall.ware.entity.Purchase;

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

import java.util.*;

/**
 *
 * 采购信息 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/ware/purchase")
public class PurchaseController extends BaseController {

    @Autowired
    IPurchaseService purchaseService;

    /**
     *
     * 新增采购信息
     * @param purchaseDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<Purchase> add(@RequestBody @Validated PurchaseDto purchaseDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        Purchase purchase = purchaseService.add(purchaseDto);

        return ResultUtil.successOfInsert(purchase);
    }

    /**
     *
     * 修改采购信息
     * @param purchaseDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<Purchase> update(@RequestBody @Validated PurchaseDto purchaseDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        Purchase purchase = purchaseService.update(purchaseDto);
        if (purchase == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(purchase);
    }

    /**
     *
     * 删除采购信息
     * @param purchaseDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<Purchase> delete(@RequestBody PurchaseDto purchaseDto, HttpServletRequest httpServletRequest) {
        Purchase purchase = purchaseService.delete(purchaseDto);
        if (purchase == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(purchase);
    }

    /**
     *
     * 分页查询采购信息
     * @param purchaseDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<Purchase>> queryPage(@RequestBody PurchaseDto purchaseDto) {
        Page<Purchase> purchasePage = new Page<>(purchaseDto.getPageIndex(), purchaseDto.getPageSize());
        IPage<Purchase> page = purchaseService.queryPage(purchaseDto, purchasePage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询采购信息
     * @param purchaseDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<Purchase> queryById(@RequestBody PurchaseDto purchaseDto) {
        Purchase purchase = purchaseService.getById(purchaseDto.getId());
        if (purchase == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(purchase);
    }


    /**
     *
     * 查询所有采购信息
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<Purchase>> queryAll(@RequestBody PurchaseDto purchaseDto) {
        List<Purchase> list = purchaseService.queryAll(purchaseDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验采购信息名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PurchaseDto purchaseDto) {

        if (Objects.isNull(purchaseDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (purchaseService.checkName(purchaseDto, purchaseDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "采购信息名称已存在！");
    }

    ///ware/purchase/done
    @PostMapping("/done")
    public R finish(@RequestBody PurchaseDoneVo doneVo){

        purchaseService.done(doneVo);

        return R.ok();
    }

    /**
     * 领取采购单，这里领取在其他系统上
     * @return
     */
    @PostMapping("/received")
    public R received(@RequestBody List<Long> ids){

        purchaseService.received(ids);

        return R.ok();
    }

    ///ware/purchase/unreceive/list
    ///ware/purchase/merge
    @PostMapping("/merge")
    public R merge(@RequestBody MergeVo mergeVo){

        purchaseService.mergePurchase(mergeVo);
        return R.ok();
    }

    @RequestMapping("/unreceive/list")
    //@RequiresPermissions("ware:purchase:list")
    public R unreceivelist(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPageUnreceivePurchase(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ware:purchase:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ware:purchase:info")
    public R info(@PathVariable("id") Long id){
        Purchase purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ware:purchase:save")
    public R save(@RequestBody Purchase purchase){
        purchase.setUpdateTime(new Date());
        purchase.setCreateTime(new Date());
        purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ware:purchase:update")
    public R update(@RequestBody Purchase purchase){
        purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:purchase:delete")
    public R delete(@RequestBody Long[] ids){
        purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

