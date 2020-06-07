package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IAttrService;
import com.ypdaic.mymall.product.vo.AttrDto;
import com.ypdaic.mymall.product.entity.Attr;

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
 * 商品属性 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/attr")
public class AttrController extends BaseController {

    @Autowired
    IAttrService attrService;

    /**
     *
     * 新增商品属性
     * @param attrDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<Attr> add(@RequestBody @Validated AttrDto attrDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        Attr attr = attrService.add(attrDto);

        return ResultUtil.successOfInsert(attr);
    }

    /**
     *
     * 修改商品属性
     * @param attrDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<Attr> update(@RequestBody @Validated AttrDto attrDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        Attr attr = attrService.update(attrDto);
        if (attr == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(attr);
    }

    /**
     *
     * 删除商品属性
     * @param attrDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<Attr> delete(@RequestBody AttrDto attrDto, HttpServletRequest httpServletRequest) {
        Attr attr = attrService.delete(attrDto);
        if (attr == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(attr);
    }

    /**
     *
     * 分页查询商品属性
     * @param attrDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<Attr>> queryPage(@RequestBody AttrDto attrDto) {
        Page<Attr> attrPage = new Page<>(attrDto.getPageIndex(), attrDto.getPageSize());
        IPage<Attr> page = attrService.queryPage(attrDto, attrPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询商品属性
     * @param attrDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<Attr> queryById(@RequestBody AttrDto attrDto) {
        Attr attr = attrService.getById(attrDto.getId());
        if (attr == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(attr);
    }


    /**
     *
     * 查询所有商品属性
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<Attr>> queryAll(@RequestBody AttrDto attrDto) {
        List<Attr> list = attrService.queryAll(attrDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验商品属性名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody AttrDto attrDto) {

        if (Objects.isNull(attrDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (attrService.checkName(attrDto, attrDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "商品属性名称已存在！");
    }

}

