package com.ypdaic.mymall.product.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IPmsCommentReplayService;
import com.ypdaic.mymall.product.vo.PmsCommentReplayDto;
import com.ypdaic.mymall.product.entity.PmsCommentReplay;

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
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/product/pms-comment-replay")
public class PmsCommentReplayController extends BaseController {

    @Autowired
    IPmsCommentReplayService pmsCommentReplayService;

    /**
     *
     * 新增商品评价回复关系
     * @param pmsCommentReplayDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<PmsCommentReplay> add(@RequestBody @Validated PmsCommentReplayDto pmsCommentReplayDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        PmsCommentReplay pmsCommentReplay = pmsCommentReplayService.add(pmsCommentReplayDto);

        return ResultUtil.successOfInsert(pmsCommentReplay);
    }

    /**
     *
     * 修改商品评价回复关系
     * @param pmsCommentReplayDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<PmsCommentReplay> update(@RequestBody @Validated PmsCommentReplayDto pmsCommentReplayDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        PmsCommentReplay pmsCommentReplay = pmsCommentReplayService.update(pmsCommentReplayDto);
        if (pmsCommentReplay == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(pmsCommentReplay);
    }

    /**
     *
     * 删除商品评价回复关系
     * @param pmsCommentReplayDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<PmsCommentReplay> delete(@RequestBody PmsCommentReplayDto pmsCommentReplayDto, HttpServletRequest httpServletRequest) {
        PmsCommentReplay pmsCommentReplay = pmsCommentReplayService.delete(pmsCommentReplayDto);
        if (pmsCommentReplay == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(pmsCommentReplay);
    }

    /**
     *
     * 分页查询商品评价回复关系
     * @param pmsCommentReplayDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<PmsCommentReplay>> queryPage(@RequestBody PmsCommentReplayDto pmsCommentReplayDto) {
        Page<PmsCommentReplay> pmsCommentReplayPage = new Page<>(pmsCommentReplayDto.getPageIndex(), pmsCommentReplayDto.getPageSize());
        IPage<PmsCommentReplay> page = pmsCommentReplayService.queryPage(pmsCommentReplayDto, pmsCommentReplayPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询商品评价回复关系
     * @param pmsCommentReplayDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<PmsCommentReplay> queryById(@RequestBody PmsCommentReplayDto pmsCommentReplayDto) {
        PmsCommentReplay pmsCommentReplay = pmsCommentReplayService.getById(pmsCommentReplayDto.getId());
        if (pmsCommentReplay == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(pmsCommentReplay);
    }


    /**
     *
     * 查询所有商品评价回复关系
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<PmsCommentReplay>> queryAll(@RequestBody PmsCommentReplayDto pmsCommentReplayDto) {
        List<PmsCommentReplay> list = pmsCommentReplayService.queryAll(pmsCommentReplayDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验商品评价回复关系名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody PmsCommentReplayDto pmsCommentReplayDto) {

        if (Objects.isNull(pmsCommentReplayDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (pmsCommentReplayService.checkName(pmsCommentReplayDto, pmsCommentReplayDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "商品评价回复关系名称已存在！");
    }

}

