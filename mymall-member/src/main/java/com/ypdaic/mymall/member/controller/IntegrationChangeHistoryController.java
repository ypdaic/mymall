package com.ypdaic.mymall.member.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.member.service.IIntegrationChangeHistoryService;
import com.ypdaic.mymall.member.vo.IntegrationChangeHistoryDto;
import com.ypdaic.mymall.member.entity.IntegrationChangeHistory;

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
 * 积分变化历史记录 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/member/integration-change-history")
public class IntegrationChangeHistoryController extends BaseController {

    @Autowired
    IIntegrationChangeHistoryService integrationChangeHistoryService;

    /**
     *
     * 新增积分变化历史记录
     * @param integrationChangeHistoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<IntegrationChangeHistory> add(@RequestBody @Validated IntegrationChangeHistoryDto integrationChangeHistoryDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        IntegrationChangeHistory integrationChangeHistory = integrationChangeHistoryService.add(integrationChangeHistoryDto);

        return ResultUtil.successOfInsert(integrationChangeHistory);
    }

    /**
     *
     * 修改积分变化历史记录
     * @param integrationChangeHistoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<IntegrationChangeHistory> update(@RequestBody @Validated IntegrationChangeHistoryDto integrationChangeHistoryDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        IntegrationChangeHistory integrationChangeHistory = integrationChangeHistoryService.update(integrationChangeHistoryDto);
        if (integrationChangeHistory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(integrationChangeHistory);
    }

    /**
     *
     * 删除积分变化历史记录
     * @param integrationChangeHistoryDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<IntegrationChangeHistory> delete(@RequestBody IntegrationChangeHistoryDto integrationChangeHistoryDto, HttpServletRequest httpServletRequest) {
        IntegrationChangeHistory integrationChangeHistory = integrationChangeHistoryService.delete(integrationChangeHistoryDto);
        if (integrationChangeHistory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(integrationChangeHistory);
    }

    /**
     *
     * 分页查询积分变化历史记录
     * @param integrationChangeHistoryDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<IntegrationChangeHistory>> queryPage(@RequestBody IntegrationChangeHistoryDto integrationChangeHistoryDto) {
        Page<IntegrationChangeHistory> integrationChangeHistoryPage = new Page<>(integrationChangeHistoryDto.getPageIndex(), integrationChangeHistoryDto.getPageSize());
        IPage<IntegrationChangeHistory> page = integrationChangeHistoryService.queryPage(integrationChangeHistoryDto, integrationChangeHistoryPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询积分变化历史记录
     * @param integrationChangeHistoryDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<IntegrationChangeHistory> queryById(@RequestBody IntegrationChangeHistoryDto integrationChangeHistoryDto) {
        IntegrationChangeHistory integrationChangeHistory = integrationChangeHistoryService.getById(integrationChangeHistoryDto.getId());
        if (integrationChangeHistory == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(integrationChangeHistory);
    }


    /**
     *
     * 查询所有积分变化历史记录
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<IntegrationChangeHistory>> queryAll(@RequestBody IntegrationChangeHistoryDto integrationChangeHistoryDto) {
        List<IntegrationChangeHistory> list = integrationChangeHistoryService.queryAll(integrationChangeHistoryDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验积分变化历史记录名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody IntegrationChangeHistoryDto integrationChangeHistoryDto) {

        if (Objects.isNull(integrationChangeHistoryDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (integrationChangeHistoryService.checkName(integrationChangeHistoryDto, integrationChangeHistoryDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "积分变化历史记录名称已存在！");
    }

}

