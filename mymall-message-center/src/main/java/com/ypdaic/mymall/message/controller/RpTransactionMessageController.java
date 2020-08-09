package com.ypdaic.mymall.message.controller;


import com.ypdaic.mymall.common.util.R;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.message.service.IRpTransactionMessageService;
import com.ypdaic.mymall.message.vo.RpTransactionMessageDto;
import com.ypdaic.mymall.message.entity.RpTransactionMessage;

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
import java.util.List;
import java.util.Objects;

/**
 *
 *  前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-08-08
 */
@RestController
@RequestMapping("/message/rp-transaction-message")
public class RpTransactionMessageController extends BaseController {

    @Autowired
    IRpTransactionMessageService rpTransactionMessageService;

    /**
     *
     * 新增
     * @param rpTransactionMessageDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<RpTransactionMessage> add(@RequestBody @Validated RpTransactionMessageDto rpTransactionMessageDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        RpTransactionMessage rpTransactionMessage = rpTransactionMessageService.add(rpTransactionMessageDto);

        return ResultUtil.successOfInsert(rpTransactionMessage);
    }

    /**
     *
     * 修改
     * @param rpTransactionMessageDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<RpTransactionMessage> update(@RequestBody @Validated RpTransactionMessageDto rpTransactionMessageDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        RpTransactionMessage rpTransactionMessage = rpTransactionMessageService.update(rpTransactionMessageDto);
        if (rpTransactionMessage == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(rpTransactionMessage);
    }

    /**
     *
     * 删除
     * @param rpTransactionMessageDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<RpTransactionMessage> delete(@RequestBody RpTransactionMessageDto rpTransactionMessageDto, HttpServletRequest httpServletRequest) {
        RpTransactionMessage rpTransactionMessage = rpTransactionMessageService.delete(rpTransactionMessageDto);
        if (rpTransactionMessage == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(rpTransactionMessage);
    }

    /**
     *
     * 分页查询
     * @param rpTransactionMessageDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<RpTransactionMessage>> queryPage(@RequestBody RpTransactionMessageDto rpTransactionMessageDto) {
        Page<RpTransactionMessage> rpTransactionMessagePage = new Page<>(rpTransactionMessageDto.getPageIndex(), rpTransactionMessageDto.getPageSize());
        IPage<RpTransactionMessage> page = rpTransactionMessageService.queryPage(rpTransactionMessageDto, rpTransactionMessagePage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询
     * @param rpTransactionMessageDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<RpTransactionMessage> queryById(@RequestBody RpTransactionMessageDto rpTransactionMessageDto) {
        RpTransactionMessage rpTransactionMessage = rpTransactionMessageService.getById(rpTransactionMessageDto.getId());
        if (rpTransactionMessage == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(rpTransactionMessage);
    }


    /**
     *
     * 查询所有
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<RpTransactionMessage>> queryAll(@RequestBody RpTransactionMessageDto rpTransactionMessageDto) {
        List<RpTransactionMessage> list = rpTransactionMessageService.queryAll(rpTransactionMessageDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody RpTransactionMessageDto rpTransactionMessageDto) {

        if (Objects.isNull(rpTransactionMessageDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (rpTransactionMessageService.checkName(rpTransactionMessageDto, rpTransactionMessageDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "名称已存在！");
    }

    /**
     * 校验名称
     *
     * @return
     */
    @PostMapping("/saveMessageWaitingConfirm")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public R saveMessageWaitingConfirm(@RequestBody RpTransactionMessage rpTransactionMessage) {

        int i = rpTransactionMessageService.saveMessageWaitingConfirm(rpTransactionMessage);
        return R.ok().setData(i);
    }

    /**
     * 校验名称
     *
     * @return
     */
    @PostMapping("/confirmAndSendMessage")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public R confirmAndSendMessage(@RequestParam("messageId") String messageId) {

        rpTransactionMessageService.confirmAndSendMessage(messageId);
        return R.ok();
    }





}

