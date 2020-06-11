package com.ypdaic.mymall.member.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.member.service.IMemberStatisticsInfoService;
import com.ypdaic.mymall.member.vo.MemberStatisticsInfoDto;
import com.ypdaic.mymall.member.entity.MemberStatisticsInfo;

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
 * 会员统计信息 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-11
 */
@RestController
@RequestMapping("/member/member-statistics-info")
public class MemberStatisticsInfoController extends BaseController {

    @Autowired
    IMemberStatisticsInfoService memberStatisticsInfoService;

    /**
     *
     * 新增会员统计信息
     * @param memberStatisticsInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<MemberStatisticsInfo> add(@RequestBody @Validated MemberStatisticsInfoDto memberStatisticsInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        MemberStatisticsInfo memberStatisticsInfo = memberStatisticsInfoService.add(memberStatisticsInfoDto);

        return ResultUtil.successOfInsert(memberStatisticsInfo);
    }

    /**
     *
     * 修改会员统计信息
     * @param memberStatisticsInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<MemberStatisticsInfo> update(@RequestBody @Validated MemberStatisticsInfoDto memberStatisticsInfoDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        MemberStatisticsInfo memberStatisticsInfo = memberStatisticsInfoService.update(memberStatisticsInfoDto);
        if (memberStatisticsInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(memberStatisticsInfo);
    }

    /**
     *
     * 删除会员统计信息
     * @param memberStatisticsInfoDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<MemberStatisticsInfo> delete(@RequestBody MemberStatisticsInfoDto memberStatisticsInfoDto, HttpServletRequest httpServletRequest) {
        MemberStatisticsInfo memberStatisticsInfo = memberStatisticsInfoService.delete(memberStatisticsInfoDto);
        if (memberStatisticsInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(memberStatisticsInfo);
    }

    /**
     *
     * 分页查询会员统计信息
     * @param memberStatisticsInfoDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<MemberStatisticsInfo>> queryPage(@RequestBody MemberStatisticsInfoDto memberStatisticsInfoDto) {
        Page<MemberStatisticsInfo> memberStatisticsInfoPage = new Page<>(memberStatisticsInfoDto.getPageIndex(), memberStatisticsInfoDto.getPageSize());
        IPage<MemberStatisticsInfo> page = memberStatisticsInfoService.queryPage(memberStatisticsInfoDto, memberStatisticsInfoPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询会员统计信息
     * @param memberStatisticsInfoDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<MemberStatisticsInfo> queryById(@RequestBody MemberStatisticsInfoDto memberStatisticsInfoDto) {
        MemberStatisticsInfo memberStatisticsInfo = memberStatisticsInfoService.getById(memberStatisticsInfoDto.getId());
        if (memberStatisticsInfo == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(memberStatisticsInfo);
    }


    /**
     *
     * 查询所有会员统计信息
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<MemberStatisticsInfo>> queryAll(@RequestBody MemberStatisticsInfoDto memberStatisticsInfoDto) {
        List<MemberStatisticsInfo> list = memberStatisticsInfoService.queryAll(memberStatisticsInfoDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验会员统计信息名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody MemberStatisticsInfoDto memberStatisticsInfoDto) {

        if (Objects.isNull(memberStatisticsInfoDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (memberStatisticsInfoService.checkName(memberStatisticsInfoDto, memberStatisticsInfoDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "会员统计信息名称已存在！");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:memberstatisticsinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberStatisticsInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:memberstatisticsinfo:info")
    public R info(@PathVariable("id") Long id){
        MemberStatisticsInfo memberStatisticsInfo = memberStatisticsInfoService.getById(id);

        return R.ok().put("memberStatisticsInfo", memberStatisticsInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:memberstatisticsinfo:save")
    public R save(@RequestBody MemberStatisticsInfo memberStatisticsInfo){
        memberStatisticsInfoService.save(memberStatisticsInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:memberstatisticsinfo:update")
    public R update(@RequestBody MemberStatisticsInfo memberStatisticsInfo){
        memberStatisticsInfoService.updateById(memberStatisticsInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:memberstatisticsinfo:delete")
    public R delete(@RequestBody Long[] ids){
        memberStatisticsInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

