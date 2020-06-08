package com.ypdaic.mymall.member.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.member.service.IMemberService;
import com.ypdaic.mymall.member.vo.MemberDto;
import com.ypdaic.mymall.member.entity.Member;

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
 * 会员 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/member/member")
public class MemberController extends BaseController {

    @Autowired
    IMemberService memberService;

    /**
     *
     * 新增会员
     * @param memberDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<Member> add(@RequestBody @Validated MemberDto memberDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        Member member = memberService.add(memberDto);

        return ResultUtil.successOfInsert(member);
    }

    /**
     *
     * 修改会员
     * @param memberDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<Member> update(@RequestBody @Validated MemberDto memberDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        Member member = memberService.update(memberDto);
        if (member == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(member);
    }

    /**
     *
     * 删除会员
     * @param memberDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<Member> delete(@RequestBody MemberDto memberDto, HttpServletRequest httpServletRequest) {
        Member member = memberService.delete(memberDto);
        if (member == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(member);
    }

    /**
     *
     * 分页查询会员
     * @param memberDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<Member>> queryPage(@RequestBody MemberDto memberDto) {
        Page<Member> memberPage = new Page<>(memberDto.getPageIndex(), memberDto.getPageSize());
        IPage<Member> page = memberService.queryPage(memberDto, memberPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询会员
     * @param memberDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<Member> queryById(@RequestBody MemberDto memberDto) {
        Member member = memberService.getById(memberDto.getId());
        if (member == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(member);
    }


    /**
     *
     * 查询所有会员
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<Member>> queryAll(@RequestBody MemberDto memberDto) {
        List<Member> list = memberService.queryAll(memberDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验会员名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody MemberDto memberDto) {

        if (Objects.isNull(memberDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (memberService.checkName(memberDto, memberDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "会员名称已存在！");
    }

}

