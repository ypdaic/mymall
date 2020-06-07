package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.ISpuCommentService;
import com.ypdaic.mymall.product.vo.SpuCommentDto;
import com.ypdaic.mymall.product.entity.SpuComment;

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
 * 商品评价 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/spu-comment")
public class SpuCommentController extends BaseController {

    @Autowired
    ISpuCommentService spuCommentService;

    /**
     *
     * 新增商品评价
     * @param spuCommentDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<SpuComment> add(@RequestBody @Validated SpuCommentDto spuCommentDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        SpuComment spuComment = spuCommentService.add(spuCommentDto);

        return ResultUtil.successOfInsert(spuComment);
    }

    /**
     *
     * 修改商品评价
     * @param spuCommentDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<SpuComment> update(@RequestBody @Validated SpuCommentDto spuCommentDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        SpuComment spuComment = spuCommentService.update(spuCommentDto);
        if (spuComment == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(spuComment);
    }

    /**
     *
     * 删除商品评价
     * @param spuCommentDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<SpuComment> delete(@RequestBody SpuCommentDto spuCommentDto, HttpServletRequest httpServletRequest) {
        SpuComment spuComment = spuCommentService.delete(spuCommentDto);
        if (spuComment == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(spuComment);
    }

    /**
     *
     * 分页查询商品评价
     * @param spuCommentDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<SpuComment>> queryPage(@RequestBody SpuCommentDto spuCommentDto) {
        Page<SpuComment> spuCommentPage = new Page<>(spuCommentDto.getPageIndex(), spuCommentDto.getPageSize());
        IPage<SpuComment> page = spuCommentService.queryPage(spuCommentDto, spuCommentPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询商品评价
     * @param spuCommentDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<SpuComment> queryById(@RequestBody SpuCommentDto spuCommentDto) {
        SpuComment spuComment = spuCommentService.getById(spuCommentDto.getId());
        if (spuComment == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(spuComment);
    }


    /**
     *
     * 查询所有商品评价
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<SpuComment>> queryAll(@RequestBody SpuCommentDto spuCommentDto) {
        List<SpuComment> list = spuCommentService.queryAll(spuCommentDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验商品评价名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody SpuCommentDto spuCommentDto) {

        if (Objects.isNull(spuCommentDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (spuCommentService.checkName(spuCommentDto, spuCommentDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "商品评价名称已存在！");
    }

}

