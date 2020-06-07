package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.ICommentReplayService;
import com.ypdaic.mymall.product.vo.CommentReplayDto;
import com.ypdaic.mymall.product.entity.CommentReplay;

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
 * 商品评价回复关系 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/comment-replay")
public class CommentReplayController extends BaseController {

    @Autowired
    ICommentReplayService commentReplayService;

    /**
     *
     * 新增商品评价回复关系
     * @param commentReplayDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<CommentReplay> add(@RequestBody @Validated CommentReplayDto commentReplayDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        CommentReplay commentReplay = commentReplayService.add(commentReplayDto);

        return ResultUtil.successOfInsert(commentReplay);
    }

    /**
     *
     * 修改商品评价回复关系
     * @param commentReplayDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<CommentReplay> update(@RequestBody @Validated CommentReplayDto commentReplayDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        CommentReplay commentReplay = commentReplayService.update(commentReplayDto);
        if (commentReplay == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(commentReplay);
    }

    /**
     *
     * 删除商品评价回复关系
     * @param commentReplayDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<CommentReplay> delete(@RequestBody CommentReplayDto commentReplayDto, HttpServletRequest httpServletRequest) {
        CommentReplay commentReplay = commentReplayService.delete(commentReplayDto);
        if (commentReplay == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(commentReplay);
    }

    /**
     *
     * 分页查询商品评价回复关系
     * @param commentReplayDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<CommentReplay>> queryPage(@RequestBody CommentReplayDto commentReplayDto) {
        Page<CommentReplay> commentReplayPage = new Page<>(commentReplayDto.getPageIndex(), commentReplayDto.getPageSize());
        IPage<CommentReplay> page = commentReplayService.queryPage(commentReplayDto, commentReplayPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询商品评价回复关系
     * @param commentReplayDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<CommentReplay> queryById(@RequestBody CommentReplayDto commentReplayDto) {
        CommentReplay commentReplay = commentReplayService.getById(commentReplayDto.getId());
        if (commentReplay == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(commentReplay);
    }


    /**
     *
     * 查询所有商品评价回复关系
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<CommentReplay>> queryAll(@RequestBody CommentReplayDto commentReplayDto) {
        List<CommentReplay> list = commentReplayService.queryAll(commentReplayDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验商品评价回复关系名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody CommentReplayDto commentReplayDto) {

        if (Objects.isNull(commentReplayDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (commentReplayService.checkName(commentReplayDto, commentReplayDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "商品评价回复关系名称已存在！");
    }

}

