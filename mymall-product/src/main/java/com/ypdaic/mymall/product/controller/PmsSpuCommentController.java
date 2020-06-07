package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IPmsSpuCommentService;
import com.ypdaic.mymall.product.vo.PmsSpuCommentDto;
import com.ypdaic.mymall.product.entity.PmsSpuComment;

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
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/product/pms-spu-comment")
public class PmsSpuCommentController extends BaseController {

    @Autowired
    IPmsSpuCommentService pmsSpuCommentService;

    /**
     *
     * 新增商品评价
     * @param pmsSpuCommentDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PmsSpuComment> add(@RequestBody @Validated PmsSpuCommentDto pmsSpuCommentDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PmsSpuComment pmsSpuComment = pmsSpuCommentService.add(pmsSpuCommentDto);

        return ResultUtil.successOfInsert(pmsSpuComment);
    }

    /**
     *
     * 修改商品评价
     * @param pmsSpuCommentDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PmsSpuComment> update(@RequestBody @Validated PmsSpuCommentDto pmsSpuCommentDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PmsSpuComment pmsSpuComment = pmsSpuCommentService.update(pmsSpuCommentDto);
        if (pmsSpuComment == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(pmsSpuComment);
    }

    /**
     *
     * 删除商品评价
     * @param pmsSpuCommentDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PmsSpuComment> delete(@RequestBody PmsSpuCommentDto pmsSpuCommentDto, HttpServletRequest httpServletRequest) {
        PmsSpuComment pmsSpuComment = pmsSpuCommentService.delete(pmsSpuCommentDto);
        if (pmsSpuComment == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(pmsSpuComment);
    }

    /**
     *
     * 分页查询商品评价
     * @param pmsSpuCommentDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PmsSpuComment>> queryPage(@RequestBody PmsSpuCommentDto pmsSpuCommentDto) {
        Page<PmsSpuComment> pmsSpuCommentPage = new Page<>(pmsSpuCommentDto.getPageIndex(), pmsSpuCommentDto.getPageSize());
        IPage<PmsSpuComment> page = pmsSpuCommentService.queryPage(pmsSpuCommentDto, pmsSpuCommentPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询商品评价
     * @param pmsSpuCommentDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PmsSpuComment> queryById(@RequestBody PmsSpuCommentDto pmsSpuCommentDto) {
        PmsSpuComment pmsSpuComment = pmsSpuCommentService.getById(pmsSpuCommentDto.getId());
        if (pmsSpuComment == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(pmsSpuComment);
    }


    /**
     *
     * 查询所有商品评价
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PmsSpuComment>> queryAll(@RequestBody PmsSpuCommentDto pmsSpuCommentDto) {
        List<PmsSpuComment> list = pmsSpuCommentService.queryAll(pmsSpuCommentDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验商品评价名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PmsSpuCommentDto pmsSpuCommentDto) {

        if (Objects.isNull(pmsSpuCommentDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (pmsSpuCommentService.checkName(pmsSpuCommentDto, pmsSpuCommentDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "商品评价名称已存在！");
    }

}

