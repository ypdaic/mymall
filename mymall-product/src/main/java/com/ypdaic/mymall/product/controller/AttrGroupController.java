package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IAttrGroupService;
import com.ypdaic.mymall.product.vo.AttrGroupDto;
import com.ypdaic.mymall.product.entity.AttrGroup;

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
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/attr-group")
public class AttrGroupController extends BaseController {

    @Autowired
    IAttrGroupService attrGroupService;

    /**
     *
     * 新增属性分组
     * @param attrGroupDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<AttrGroup> add(@RequestBody @Validated AttrGroupDto attrGroupDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        AttrGroup attrGroup = attrGroupService.add(attrGroupDto);

        return ResultUtil.successOfInsert(attrGroup);
    }

    /**
     *
     * 修改属性分组
     * @param attrGroupDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<AttrGroup> update(@RequestBody @Validated AttrGroupDto attrGroupDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        AttrGroup attrGroup = attrGroupService.update(attrGroupDto);
        if (attrGroup == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(attrGroup);
    }

    /**
     *
     * 删除属性分组
     * @param attrGroupDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<AttrGroup> delete(@RequestBody AttrGroupDto attrGroupDto, HttpServletRequest httpServletRequest) {
        AttrGroup attrGroup = attrGroupService.delete(attrGroupDto);
        if (attrGroup == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(attrGroup);
    }

    /**
     *
     * 分页查询属性分组
     * @param attrGroupDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<AttrGroup>> queryPage(@RequestBody AttrGroupDto attrGroupDto) {
        Page<AttrGroup> attrGroupPage = new Page<>(attrGroupDto.getPageIndex(), attrGroupDto.getPageSize());
        IPage<AttrGroup> page = attrGroupService.queryPage(attrGroupDto, attrGroupPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询属性分组
     * @param attrGroupDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<AttrGroup> queryById(@RequestBody AttrGroupDto attrGroupDto) {
        AttrGroup attrGroup = attrGroupService.getById(attrGroupDto.getId());
        if (attrGroup == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(attrGroup);
    }


    /**
     *
     * 查询所有属性分组
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<AttrGroup>> queryAll(@RequestBody AttrGroupDto attrGroupDto) {
        List<AttrGroup> list = attrGroupService.queryAll(attrGroupDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验属性分组名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody AttrGroupDto attrGroupDto) {

        if (Objects.isNull(attrGroupDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (attrGroupService.checkName(attrGroupDto, attrGroupDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "属性分组名称已存在！");
    }

}

