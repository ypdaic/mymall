package com.ypdaic.mymall.ware.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.ware.vo.FareVo;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.ware.service.IWareInfoService;
import com.ypdaic.mymall.ware.vo.WareInfoDto;
import com.ypdaic.mymall.ware.entity.WareInfo;

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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * 仓库信息 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/ware/wareinfo")
public class WareInfoController extends BaseController {

    @Autowired
    IWareInfoService wareInfoService;

    /**
     *
     * 新增仓库信息
     * @param wareInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<WareInfo> add(@RequestBody @Validated WareInfoDto wareInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        WareInfo wareInfo = wareInfoService.add(wareInfoDto);

        return ResultUtil.successOfInsert(wareInfo);
    }

    /**
     *
     * 修改仓库信息
     * @param wareInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<WareInfo> update(@RequestBody @Validated WareInfoDto wareInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        WareInfo wareInfo = wareInfoService.update(wareInfoDto);
        if (wareInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(wareInfo);
    }

    /**
     *
     * 删除仓库信息
     * @param wareInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<WareInfo> delete(@RequestBody WareInfoDto wareInfoDto, HttpServletRequest httpServletRequest) {
        WareInfo wareInfo = wareInfoService.delete(wareInfoDto);
        if (wareInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(wareInfo);
    }

    /**
     *
     * 分页查询仓库信息
     * @param wareInfoDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<WareInfo>> queryPage(@RequestBody WareInfoDto wareInfoDto) {
        Page<WareInfo> wareInfoPage = new Page<>(wareInfoDto.getPageIndex(), wareInfoDto.getPageSize());
        IPage<WareInfo> page = wareInfoService.queryPage(wareInfoDto, wareInfoPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询仓库信息
     * @param wareInfoDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<WareInfo> queryById(@RequestBody WareInfoDto wareInfoDto) {
        WareInfo wareInfo = wareInfoService.getById(wareInfoDto.getId());
        if (wareInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(wareInfo);
    }


    /**
     *
     * 查询所有仓库信息
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<WareInfo>> queryAll(@RequestBody WareInfoDto wareInfoDto) {
        List<WareInfo> list = wareInfoService.queryAll(wareInfoDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验仓库信息名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody WareInfoDto wareInfoDto) {

        if (Objects.isNull(wareInfoDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (wareInfoService.checkName(wareInfoDto, wareInfoDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "仓库信息名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ware:wareinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wareInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ware:wareinfo:info")
    public R info(@PathVariable("id") Long id){
        WareInfo wareInfo = wareInfoService.getById(id);

        return R.ok().put("wareInfo", wareInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ware:wareinfo:save")
    public R save(@RequestBody WareInfo wareInfo){
        wareInfoService.save(wareInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ware:wareinfo:update")
    public R update(@RequestBody WareInfo wareInfo){
        wareInfoService.updateById(wareInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ware:wareinfo:delete")
    public R delete(@RequestBody Long[] ids){
        wareInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @GetMapping("/fare")
    public R getFare(@RequestParam("addrId") Long id) {

        FareVo fare = wareInfoService.getFare(id);
        return R.ok().setData(fare);

    }

}

