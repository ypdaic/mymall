package com.ypdaic.mymall.member.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.member.service.IMemberLoginLogService;
import com.ypdaic.mymall.member.vo.MemberLoginLogDto;
import com.ypdaic.mymall.member.entity.MemberLoginLog;

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
 * 会员登录记录 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/member/member-login-log")
public class MemberLoginLogController extends BaseController {

    @Autowired
    IMemberLoginLogService memberLoginLogService;

    /**
     *
     * 新增会员登录记录
     * @param memberLoginLogDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<MemberLoginLog> add(@RequestBody @Validated MemberLoginLogDto memberLoginLogDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        MemberLoginLog memberLoginLog = memberLoginLogService.add(memberLoginLogDto);

        return ResultUtil.successOfInsert(memberLoginLog);
    }

    /**
     *
     * 修改会员登录记录
     * @param memberLoginLogDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<MemberLoginLog> update(@RequestBody @Validated MemberLoginLogDto memberLoginLogDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        MemberLoginLog memberLoginLog = memberLoginLogService.update(memberLoginLogDto);
        if (memberLoginLog == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(memberLoginLog);
    }

    /**
     *
     * 删除会员登录记录
     * @param memberLoginLogDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<MemberLoginLog> delete(@RequestBody MemberLoginLogDto memberLoginLogDto, HttpServletRequest httpServletRequest) {
        MemberLoginLog memberLoginLog = memberLoginLogService.delete(memberLoginLogDto);
        if (memberLoginLog == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(memberLoginLog);
    }

    /**
     *
     * 分页查询会员登录记录
     * @param memberLoginLogDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<MemberLoginLog>> queryPage(@RequestBody MemberLoginLogDto memberLoginLogDto) {
        Page<MemberLoginLog> memberLoginLogPage = new Page<>(memberLoginLogDto.getPageIndex(), memberLoginLogDto.getPageSize());
        IPage<MemberLoginLog> page = memberLoginLogService.queryPage(memberLoginLogDto, memberLoginLogPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询会员登录记录
     * @param memberLoginLogDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<MemberLoginLog> queryById(@RequestBody MemberLoginLogDto memberLoginLogDto) {
        MemberLoginLog memberLoginLog = memberLoginLogService.getById(memberLoginLogDto.getId());
        if (memberLoginLog == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(memberLoginLog);
    }


    /**
     *
     * 查询所有会员登录记录
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<MemberLoginLog>> queryAll(@RequestBody MemberLoginLogDto memberLoginLogDto) {
        List<MemberLoginLog> list = memberLoginLogService.queryAll(memberLoginLogDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验会员登录记录名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody MemberLoginLogDto memberLoginLogDto) {

        if (Objects.isNull(memberLoginLogDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (memberLoginLogService.checkName(memberLoginLogDto, memberLoginLogDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "会员登录记录名称已存在！");
    }

}

