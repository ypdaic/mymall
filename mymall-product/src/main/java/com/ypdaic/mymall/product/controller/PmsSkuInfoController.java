package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IPmsSkuInfoService;
import com.ypdaic.mymall.product.vo.PmsSkuInfoDto;
import com.ypdaic.mymall.product.entity.PmsSkuInfo;

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
 * sku信息 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/product/pms-sku-info")
public class PmsSkuInfoController extends BaseController {

    @Autowired
    IPmsSkuInfoService pmsSkuInfoService;

    /**
     *
     * 新增sku信息
     * @param pmsSkuInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PmsSkuInfo> add(@RequestBody @Validated PmsSkuInfoDto pmsSkuInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PmsSkuInfo pmsSkuInfo = pmsSkuInfoService.add(pmsSkuInfoDto);

        return ResultUtil.successOfInsert(pmsSkuInfo);
    }

    /**
     *
     * 修改sku信息
     * @param pmsSkuInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PmsSkuInfo> update(@RequestBody @Validated PmsSkuInfoDto pmsSkuInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PmsSkuInfo pmsSkuInfo = pmsSkuInfoService.update(pmsSkuInfoDto);
        if (pmsSkuInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(pmsSkuInfo);
    }

    /**
     *
     * 删除sku信息
     * @param pmsSkuInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PmsSkuInfo> delete(@RequestBody PmsSkuInfoDto pmsSkuInfoDto, HttpServletRequest httpServletRequest) {
        PmsSkuInfo pmsSkuInfo = pmsSkuInfoService.delete(pmsSkuInfoDto);
        if (pmsSkuInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(pmsSkuInfo);
    }

    /**
     *
     * 分页查询sku信息
     * @param pmsSkuInfoDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PmsSkuInfo>> queryPage(@RequestBody PmsSkuInfoDto pmsSkuInfoDto) {
        Page<PmsSkuInfo> pmsSkuInfoPage = new Page<>(pmsSkuInfoDto.getPageIndex(), pmsSkuInfoDto.getPageSize());
        IPage<PmsSkuInfo> page = pmsSkuInfoService.queryPage(pmsSkuInfoDto, pmsSkuInfoPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询sku信息
     * @param pmsSkuInfoDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PmsSkuInfo> queryById(@RequestBody PmsSkuInfoDto pmsSkuInfoDto) {
        PmsSkuInfo pmsSkuInfo = pmsSkuInfoService.getById(pmsSkuInfoDto.getId());
        if (pmsSkuInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(pmsSkuInfo);
    }


    /**
     *
     * 查询所有sku信息
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PmsSkuInfo>> queryAll(@RequestBody PmsSkuInfoDto pmsSkuInfoDto) {
        List<PmsSkuInfo> list = pmsSkuInfoService.queryAll(pmsSkuInfoDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验sku信息名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PmsSkuInfoDto pmsSkuInfoDto) {

        if (Objects.isNull(pmsSkuInfoDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (pmsSkuInfoService.checkName(pmsSkuInfoDto, pmsSkuInfoDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "sku信息名称已存在！");
    }

}

