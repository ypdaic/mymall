package com.ypdaic.mymall.ware.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.ware.service.IMqMessageService;
import com.ypdaic.mymall.ware.vo.MqMessageDto;
import com.ypdaic.mymall.ware.entity.MqMessage;

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
 * 消息备份 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-08-08
 */
@RestController
@RequestMapping("/ware/mq-message")
public class MqMessageController extends BaseController {

    @Autowired
    IMqMessageService mqMessageService;

    /**
     *
     * 新增消息备份
     * @param mqMessageDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<MqMessage> add(@RequestBody @Validated MqMessageDto mqMessageDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        MqMessage mqMessage = mqMessageService.add(mqMessageDto);

        return ResultUtil.successOfInsert(mqMessage);
    }

    /**
     *
     * 修改消息备份
     * @param mqMessageDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<MqMessage> update(@RequestBody @Validated MqMessageDto mqMessageDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        MqMessage mqMessage = mqMessageService.update(mqMessageDto);
        if (mqMessage == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(mqMessage);
    }

    /**
     *
     * 删除消息备份
     * @param mqMessageDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<MqMessage> delete(@RequestBody MqMessageDto mqMessageDto, HttpServletRequest httpServletRequest) {
        MqMessage mqMessage = mqMessageService.delete(mqMessageDto);
        if (mqMessage == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(mqMessage);
    }

    /**
     *
     * 分页查询消息备份
     * @param mqMessageDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<MqMessage>> queryPage(@RequestBody MqMessageDto mqMessageDto) {
        Page<MqMessage> mqMessagePage = new Page<>(mqMessageDto.getPageIndex(), mqMessageDto.getPageSize());
        IPage<MqMessage> page = mqMessageService.queryPage(mqMessageDto, mqMessagePage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询消息备份
     * @param mqMessageDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<MqMessage> queryById(@RequestBody MqMessageDto mqMessageDto) {
        MqMessage mqMessage = mqMessageService.getById(mqMessageDto.getId());
        if (mqMessage == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(mqMessage);
    }


    /**
     *
     * 查询所有消息备份
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<MqMessage>> queryAll(@RequestBody MqMessageDto mqMessageDto) {
        List<MqMessage> list = mqMessageService.queryAll(mqMessageDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验消息备份名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody MqMessageDto mqMessageDto) {

        if (Objects.isNull(mqMessageDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (mqMessageService.checkName(mqMessageDto, mqMessageDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "消息备份名称已存在！");
    }

}

