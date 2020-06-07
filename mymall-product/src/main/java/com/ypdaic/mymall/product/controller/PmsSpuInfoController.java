package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IPmsSpuInfoService;
import com.ypdaic.mymall.product.vo.PmsSpuInfoDto;
import com.ypdaic.mymall.product.entity.PmsSpuInfo;

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
 * spu信息 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/product/pms-spu-info")
public class PmsSpuInfoController extends BaseController {

    @Autowired
    IPmsSpuInfoService pmsSpuInfoService;

    /**
     *
     * 新增spu信息
     * @param pmsSpuInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PmsSpuInfo> add(@RequestBody @Validated PmsSpuInfoDto pmsSpuInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PmsSpuInfo pmsSpuInfo = pmsSpuInfoService.add(pmsSpuInfoDto);

        return ResultUtil.successOfInsert(pmsSpuInfo);
    }

    /**
     *
     * 修改spu信息
     * @param pmsSpuInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PmsSpuInfo> update(@RequestBody @Validated PmsSpuInfoDto pmsSpuInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PmsSpuInfo pmsSpuInfo = pmsSpuInfoService.update(pmsSpuInfoDto);
        if (pmsSpuInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(pmsSpuInfo);
    }

    /**
     *
     * 删除spu信息
     * @param pmsSpuInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PmsSpuInfo> delete(@RequestBody PmsSpuInfoDto pmsSpuInfoDto, HttpServletRequest httpServletRequest) {
        PmsSpuInfo pmsSpuInfo = pmsSpuInfoService.delete(pmsSpuInfoDto);
        if (pmsSpuInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(pmsSpuInfo);
    }

    /**
     *
     * 分页查询spu信息
     * @param pmsSpuInfoDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PmsSpuInfo>> queryPage(@RequestBody PmsSpuInfoDto pmsSpuInfoDto) {
        Page<PmsSpuInfo> pmsSpuInfoPage = new Page<>(pmsSpuInfoDto.getPageIndex(), pmsSpuInfoDto.getPageSize());
        IPage<PmsSpuInfo> page = pmsSpuInfoService.queryPage(pmsSpuInfoDto, pmsSpuInfoPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询spu信息
     * @param pmsSpuInfoDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PmsSpuInfo> queryById(@RequestBody PmsSpuInfoDto pmsSpuInfoDto) {
        PmsSpuInfo pmsSpuInfo = pmsSpuInfoService.getById(pmsSpuInfoDto.getId());
        if (pmsSpuInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(pmsSpuInfo);
    }


    /**
     *
     * 查询所有spu信息
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PmsSpuInfo>> queryAll(@RequestBody PmsSpuInfoDto pmsSpuInfoDto) {
        List<PmsSpuInfo> list = pmsSpuInfoService.queryAll(pmsSpuInfoDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验spu信息名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PmsSpuInfoDto pmsSpuInfoDto) {

        if (Objects.isNull(pmsSpuInfoDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (pmsSpuInfoService.checkName(pmsSpuInfoDto, pmsSpuInfoDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "spu信息名称已存在！");
    }

}

