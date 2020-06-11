package com.ypdaic.mymall.member.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.member.service.IMemberLevelService;
import com.ypdaic.mymall.member.vo.MemberLevelDto;
import com.ypdaic.mymall.member.entity.MemberLevel;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * 会员等级 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/member/memberlevel")
public class MemberLevelController extends BaseController {

    @Autowired
    IMemberLevelService memberLevelService;

    /**
     *
     * 新增会员等级
     * @param memberLevelDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<MemberLevel> add(@RequestBody @Validated MemberLevelDto memberLevelDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        MemberLevel memberLevel = memberLevelService.add(memberLevelDto);

        return ResultUtil.successOfInsert(memberLevel);
    }

    /**
     *
     * 修改会员等级
     * @param memberLevelDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<MemberLevel> update(@RequestBody @Validated MemberLevelDto memberLevelDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        MemberLevel memberLevel = memberLevelService.update(memberLevelDto);
        if (memberLevel == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(memberLevel);
    }

    /**
     *
     * 删除会员等级
     * @param memberLevelDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<MemberLevel> delete(@RequestBody MemberLevelDto memberLevelDto, HttpServletRequest httpServletRequest) {
        MemberLevel memberLevel = memberLevelService.delete(memberLevelDto);
        if (memberLevel == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(memberLevel);
    }

    /**
     *
     * 分页查询会员等级
     * @param memberLevelDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<MemberLevel>> queryPage(@RequestBody MemberLevelDto memberLevelDto) {
        Page<MemberLevel> memberLevelPage = new Page<>(memberLevelDto.getPageIndex(), memberLevelDto.getPageSize());
        IPage<MemberLevel> page = memberLevelService.queryPage(memberLevelDto, memberLevelPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询会员等级
     * @param memberLevelDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<MemberLevel> queryById(@RequestBody MemberLevelDto memberLevelDto) {
        MemberLevel memberLevel = memberLevelService.getById(memberLevelDto.getId());
        if (memberLevel == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(memberLevel);
    }


    /**
     *
     * 查询所有会员等级
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<MemberLevel>> queryAll(@RequestBody MemberLevelDto memberLevelDto) {
        List<MemberLevel> list = memberLevelService.queryAll(memberLevelDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验会员等级名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody MemberLevelDto memberLevelDto) {

        if (Objects.isNull(memberLevelDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (memberLevelService.checkName(memberLevelDto, memberLevelDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "会员等级名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:memberlevel:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberLevelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:memberlevel:info")
    public R info(@PathVariable("id") Long id){
        MemberLevel memberLevel = memberLevelService.getById(id);

        return R.ok().put("memberLevel", memberLevel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:memberlevel:save")
    public R save(@RequestBody MemberLevel memberLevel){
        memberLevelService.save(memberLevel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:memberlevel:update")
    public R update(@RequestBody MemberLevel memberLevel){
        memberLevelService.updateById(memberLevel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:memberlevel:delete")
    public R delete(@RequestBody Long[] ids){
        memberLevelService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }



}

