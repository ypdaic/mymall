package com.ypdaic.mymall.ware.controller;


import com.ypdaic.mymall.common.exception.BizCodeEnum;
import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.ware.vo.LockStockResult;
import com.ypdaic.mymall.ware.vo.SkuHasStockVo;
import com.ypdaic.mymall.ware.vo.WareSkuLockVo;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.ware.service.IWareSkuService;
import com.ypdaic.mymall.ware.vo.WareSkuDto;
import com.ypdaic.mymall.ware.entity.WareSku;

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
 * 商品库存 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-11
 */
@RestController
@RequestMapping("/ware/waresku")
public class WareSkuController extends BaseController {

    @Autowired
    IWareSkuService wareSkuService;

    /**
     *
     * 新增商品库存
     * @param wareSkuDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<WareSku> add(@RequestBody @Validated WareSkuDto wareSkuDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        WareSku wareSku = wareSkuService.add(wareSkuDto);

        return ResultUtil.successOfInsert(wareSku);
    }

    /**
     *
     * 修改商品库存
     * @param wareSkuDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<WareSku> update(@RequestBody @Validated WareSkuDto wareSkuDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        WareSku wareSku = wareSkuService.update(wareSkuDto);
        if (wareSku == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(wareSku);
    }

    /**
     *
     * 删除商品库存
     * @param wareSkuDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<WareSku> delete(@RequestBody WareSkuDto wareSkuDto, HttpServletRequest httpServletRequest) {
        WareSku wareSku = wareSkuService.delete(wareSkuDto);
        if (wareSku == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(wareSku);
    }

    /**
     *
     * 分页查询商品库存
     * @param wareSkuDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<WareSku>> queryPage(@RequestBody WareSkuDto wareSkuDto) {
        Page<WareSku> wareSkuPage = new Page<>(wareSkuDto.getPageIndex(), wareSkuDto.getPageSize());
        IPage<WareSku> page = wareSkuService.queryPage(wareSkuDto, wareSkuPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询商品库存
     * @param wareSkuDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<WareSku> queryById(@RequestBody WareSkuDto wareSkuDto) {
        WareSku wareSku = wareSkuService.getById(wareSkuDto.getId());
        if (wareSku == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(wareSku);
    }


    /**
     *
     * 查询所有商品库存
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<WareSku>> queryAll(@RequestBody WareSkuDto wareSkuDto) {
        List<WareSku> list = wareSkuService.queryAll(wareSkuDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验商品库存名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody WareSkuDto wareSkuDto) {

        if (Objects.isNull(wareSkuDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (wareSkuService.checkName(wareSkuDto, wareSkuDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "商品库存名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ware:waresku:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wareSkuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ware:waresku:info")
    public R info(@PathVariable("id") Long id){
        WareSku wareSku = wareSkuService.getById(id);

        return R.ok().put("wareSku", wareSku);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ware:waresku:save")
    public R save(@RequestBody WareSku wareSku){
        wareSkuService.save(wareSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ware:waresku:update")
    public R update(@RequestBody WareSku wareSku){
        wareSkuService.updateById(wareSku);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:waresku:delete")
    public R delete(@RequestBody Long[] ids){
        wareSkuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 根据skuId查询是否有库存
     * @param skuIds
     * @return
     */
    @PostMapping("/hasStock")
    public R getSkuHasStock(@RequestBody List<Long> skuIds){
        List<SkuHasStockVo> skuHasStockVos=wareSkuService.getSkuHasStock(skuIds);

        return R.ok().setData(skuHasStockVos);
    }

    @PostMapping("/lock/order")
    public R orderLockStock(@RequestBody WareSkuLockVo wareSkuLockVo) {
        try {
            Boolean aBoolean = wareSkuService.orderLockStock(wareSkuLockVo);
            return R.ok();
        } catch (Exception e) {
            return R.error(BizCodeEnum.NO_STOCK_EXCEPTION.getCode(), BizCodeEnum.NO_STOCK_EXCEPTION.getMsg());
        }

    }

}

