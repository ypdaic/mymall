package com.ypdaic.mymall.member.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.member.service.IMemberCollectSpuService;
import com.ypdaic.mymall.member.vo.MemberCollectSpuDto;
import com.ypdaic.mymall.member.entity.MemberCollectSpu;

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
 * 会员收藏的商品 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/member/member-collect-spu")
public class MemberCollectSpuController extends BaseController {

    @Autowired
    IMemberCollectSpuService memberCollectSpuService;

    /**
     *
     * 新增会员收藏的商品
     * @param memberCollectSpuDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<MemberCollectSpu> add(@RequestBody @Validated MemberCollectSpuDto memberCollectSpuDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        MemberCollectSpu memberCollectSpu = memberCollectSpuService.add(memberCollectSpuDto);

        return ResultUtil.successOfInsert(memberCollectSpu);
    }

    /**
     *
     * 修改会员收藏的商品
     * @param memberCollectSpuDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<MemberCollectSpu> update(@RequestBody @Validated MemberCollectSpuDto memberCollectSpuDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        MemberCollectSpu memberCollectSpu = memberCollectSpuService.update(memberCollectSpuDto);
        if (memberCollectSpu == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(memberCollectSpu);
    }

    /**
     *
     * 删除会员收藏的商品
     * @param memberCollectSpuDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<MemberCollectSpu> delete(@RequestBody MemberCollectSpuDto memberCollectSpuDto, HttpServletRequest httpServletRequest) {
        MemberCollectSpu memberCollectSpu = memberCollectSpuService.delete(memberCollectSpuDto);
        if (memberCollectSpu == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(memberCollectSpu);
    }

    /**
     *
     * 分页查询会员收藏的商品
     * @param memberCollectSpuDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<MemberCollectSpu>> queryPage(@RequestBody MemberCollectSpuDto memberCollectSpuDto) {
        Page<MemberCollectSpu> memberCollectSpuPage = new Page<>(memberCollectSpuDto.getPageIndex(), memberCollectSpuDto.getPageSize());
        IPage<MemberCollectSpu> page = memberCollectSpuService.queryPage(memberCollectSpuDto, memberCollectSpuPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询会员收藏的商品
     * @param memberCollectSpuDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<MemberCollectSpu> queryById(@RequestBody MemberCollectSpuDto memberCollectSpuDto) {
        MemberCollectSpu memberCollectSpu = memberCollectSpuService.getById(memberCollectSpuDto.getId());
        if (memberCollectSpu == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(memberCollectSpu);
    }


    /**
     *
     * 查询所有会员收藏的商品
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<MemberCollectSpu>> queryAll(@RequestBody MemberCollectSpuDto memberCollectSpuDto) {
        List<MemberCollectSpu> list = memberCollectSpuService.queryAll(memberCollectSpuDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验会员收藏的商品名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody MemberCollectSpuDto memberCollectSpuDto) {

        if (Objects.isNull(memberCollectSpuDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (memberCollectSpuService.checkName(memberCollectSpuDto, memberCollectSpuDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "会员收藏的商品名称已存在！");
    }

}

