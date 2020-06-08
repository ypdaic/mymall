package com.ypdaic.mymall.member.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.member.service.IMemberCollectSubjectService;
import com.ypdaic.mymall.member.vo.MemberCollectSubjectDto;
import com.ypdaic.mymall.member.entity.MemberCollectSubject;

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
 * 会员收藏的专题活动 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/member/member-collect-subject")
public class MemberCollectSubjectController extends BaseController {

    @Autowired
    IMemberCollectSubjectService memberCollectSubjectService;

    /**
     *
     * 新增会员收藏的专题活动
     * @param memberCollectSubjectDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<MemberCollectSubject> add(@RequestBody @Validated MemberCollectSubjectDto memberCollectSubjectDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        MemberCollectSubject memberCollectSubject = memberCollectSubjectService.add(memberCollectSubjectDto);

        return ResultUtil.successOfInsert(memberCollectSubject);
    }

    /**
     *
     * 修改会员收藏的专题活动
     * @param memberCollectSubjectDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<MemberCollectSubject> update(@RequestBody @Validated MemberCollectSubjectDto memberCollectSubjectDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        MemberCollectSubject memberCollectSubject = memberCollectSubjectService.update(memberCollectSubjectDto);
        if (memberCollectSubject == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(memberCollectSubject);
    }

    /**
     *
     * 删除会员收藏的专题活动
     * @param memberCollectSubjectDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<MemberCollectSubject> delete(@RequestBody MemberCollectSubjectDto memberCollectSubjectDto, HttpServletRequest httpServletRequest) {
        MemberCollectSubject memberCollectSubject = memberCollectSubjectService.delete(memberCollectSubjectDto);
        if (memberCollectSubject == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(memberCollectSubject);
    }

    /**
     *
     * 分页查询会员收藏的专题活动
     * @param memberCollectSubjectDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<MemberCollectSubject>> queryPage(@RequestBody MemberCollectSubjectDto memberCollectSubjectDto) {
        Page<MemberCollectSubject> memberCollectSubjectPage = new Page<>(memberCollectSubjectDto.getPageIndex(), memberCollectSubjectDto.getPageSize());
        IPage<MemberCollectSubject> page = memberCollectSubjectService.queryPage(memberCollectSubjectDto, memberCollectSubjectPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询会员收藏的专题活动
     * @param memberCollectSubjectDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<MemberCollectSubject> queryById(@RequestBody MemberCollectSubjectDto memberCollectSubjectDto) {
        MemberCollectSubject memberCollectSubject = memberCollectSubjectService.getById(memberCollectSubjectDto.getId());
        if (memberCollectSubject == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(memberCollectSubject);
    }


    /**
     *
     * 查询所有会员收藏的专题活动
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<MemberCollectSubject>> queryAll(@RequestBody MemberCollectSubjectDto memberCollectSubjectDto) {
        List<MemberCollectSubject> list = memberCollectSubjectService.queryAll(memberCollectSubjectDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验会员收藏的专题活动名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody MemberCollectSubjectDto memberCollectSubjectDto) {

        if (Objects.isNull(memberCollectSubjectDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (memberCollectSubjectService.checkName(memberCollectSubjectDto, memberCollectSubjectDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "会员收藏的专题活动名称已存在！");
    }

}

