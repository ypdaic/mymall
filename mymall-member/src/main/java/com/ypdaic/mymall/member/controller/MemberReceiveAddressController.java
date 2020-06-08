package com.ypdaic.mymall.member.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.member.service.IMemberReceiveAddressService;
import com.ypdaic.mymall.member.vo.MemberReceiveAddressDto;
import com.ypdaic.mymall.member.entity.MemberReceiveAddress;

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
 * 会员收货地址 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/member/member-receive-address")
public class MemberReceiveAddressController extends BaseController {

    @Autowired
    IMemberReceiveAddressService memberReceiveAddressService;

    /**
     *
     * 新增会员收货地址
     * @param memberReceiveAddressDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<MemberReceiveAddress> add(@RequestBody @Validated MemberReceiveAddressDto memberReceiveAddressDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        MemberReceiveAddress memberReceiveAddress = memberReceiveAddressService.add(memberReceiveAddressDto);

        return ResultUtil.successOfInsert(memberReceiveAddress);
    }

    /**
     *
     * 修改会员收货地址
     * @param memberReceiveAddressDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<MemberReceiveAddress> update(@RequestBody @Validated MemberReceiveAddressDto memberReceiveAddressDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        MemberReceiveAddress memberReceiveAddress = memberReceiveAddressService.update(memberReceiveAddressDto);
        if (memberReceiveAddress == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(memberReceiveAddress);
    }

    /**
     *
     * 删除会员收货地址
     * @param memberReceiveAddressDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<MemberReceiveAddress> delete(@RequestBody MemberReceiveAddressDto memberReceiveAddressDto, HttpServletRequest httpServletRequest) {
        MemberReceiveAddress memberReceiveAddress = memberReceiveAddressService.delete(memberReceiveAddressDto);
        if (memberReceiveAddress == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(memberReceiveAddress);
    }

    /**
     *
     * 分页查询会员收货地址
     * @param memberReceiveAddressDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<MemberReceiveAddress>> queryPage(@RequestBody MemberReceiveAddressDto memberReceiveAddressDto) {
        Page<MemberReceiveAddress> memberReceiveAddressPage = new Page<>(memberReceiveAddressDto.getPageIndex(), memberReceiveAddressDto.getPageSize());
        IPage<MemberReceiveAddress> page = memberReceiveAddressService.queryPage(memberReceiveAddressDto, memberReceiveAddressPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询会员收货地址
     * @param memberReceiveAddressDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<MemberReceiveAddress> queryById(@RequestBody MemberReceiveAddressDto memberReceiveAddressDto) {
        MemberReceiveAddress memberReceiveAddress = memberReceiveAddressService.getById(memberReceiveAddressDto.getId());
        if (memberReceiveAddress == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(memberReceiveAddress);
    }


    /**
     *
     * 查询所有会员收货地址
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<MemberReceiveAddress>> queryAll(@RequestBody MemberReceiveAddressDto memberReceiveAddressDto) {
        List<MemberReceiveAddress> list = memberReceiveAddressService.queryAll(memberReceiveAddressDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验会员收货地址名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody MemberReceiveAddressDto memberReceiveAddressDto) {

        if (Objects.isNull(memberReceiveAddressDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (memberReceiveAddressService.checkName(memberReceiveAddressDto, memberReceiveAddressDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "会员收货地址名称已存在！");
    }

}

