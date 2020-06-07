package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IPmsAttrGroupService;
import com.ypdaic.mymall.product.vo.PmsAttrGroupDto;
import com.ypdaic.mymall.product.entity.PmsAttrGroup;

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
 * 属性分组 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/product/pms-attr-group")
public class PmsAttrGroupController extends BaseController {

    @Autowired
    IPmsAttrGroupService pmsAttrGroupService;

    /**
     *
     * 新增属性分组
     * @param pmsAttrGroupDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PmsAttrGroup> add(@RequestBody @Validated PmsAttrGroupDto pmsAttrGroupDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PmsAttrGroup pmsAttrGroup = pmsAttrGroupService.add(pmsAttrGroupDto);

        return ResultUtil.successOfInsert(pmsAttrGroup);
    }

    /**
     *
     * 修改属性分组
     * @param pmsAttrGroupDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PmsAttrGroup> update(@RequestBody @Validated PmsAttrGroupDto pmsAttrGroupDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PmsAttrGroup pmsAttrGroup = pmsAttrGroupService.update(pmsAttrGroupDto);
        if (pmsAttrGroup == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(pmsAttrGroup);
    }

    /**
     *
     * 删除属性分组
     * @param pmsAttrGroupDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PmsAttrGroup> delete(@RequestBody PmsAttrGroupDto pmsAttrGroupDto, HttpServletRequest httpServletRequest) {
        PmsAttrGroup pmsAttrGroup = pmsAttrGroupService.delete(pmsAttrGroupDto);
        if (pmsAttrGroup == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(pmsAttrGroup);
    }

    /**
     *
     * 分页查询属性分组
     * @param pmsAttrGroupDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PmsAttrGroup>> queryPage(@RequestBody PmsAttrGroupDto pmsAttrGroupDto) {
        Page<PmsAttrGroup> pmsAttrGroupPage = new Page<>(pmsAttrGroupDto.getPageIndex(), pmsAttrGroupDto.getPageSize());
        IPage<PmsAttrGroup> page = pmsAttrGroupService.queryPage(pmsAttrGroupDto, pmsAttrGroupPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询属性分组
     * @param pmsAttrGroupDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PmsAttrGroup> queryById(@RequestBody PmsAttrGroupDto pmsAttrGroupDto) {
        PmsAttrGroup pmsAttrGroup = pmsAttrGroupService.getById(pmsAttrGroupDto.getId());
        if (pmsAttrGroup == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(pmsAttrGroup);
    }


    /**
     *
     * 查询所有属性分组
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PmsAttrGroup>> queryAll(@RequestBody PmsAttrGroupDto pmsAttrGroupDto) {
        List<PmsAttrGroup> list = pmsAttrGroupService.queryAll(pmsAttrGroupDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验属性分组名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PmsAttrGroupDto pmsAttrGroupDto) {

        if (Objects.isNull(pmsAttrGroupDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (pmsAttrGroupService.checkName(pmsAttrGroupDto, pmsAttrGroupDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "属性分组名称已存在！");
    }

}

