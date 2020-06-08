package com.ypdaic.mymall.coupon.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.coupon.service.IHomeSubjectService;
import com.ypdaic.mymall.coupon.vo.HomeSubjectDto;
import com.ypdaic.mymall.coupon.entity.HomeSubject;

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
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/coupon/home-subject")
public class HomeSubjectController extends BaseController {

    @Autowired
    IHomeSubjectService homeSubjectService;

    /**
     *
     * 新增首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<HomeSubject> add(@RequestBody @Validated HomeSubjectDto homeSubjectDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        HomeSubject homeSubject = homeSubjectService.add(homeSubjectDto);

        return ResultUtil.successOfInsert(homeSubject);
    }

    /**
     *
     * 修改首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<HomeSubject> update(@RequestBody @Validated HomeSubjectDto homeSubjectDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        HomeSubject homeSubject = homeSubjectService.update(homeSubjectDto);
        if (homeSubject == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(homeSubject);
    }

    /**
     *
     * 删除首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<HomeSubject> delete(@RequestBody HomeSubjectDto homeSubjectDto, HttpServletRequest httpServletRequest) {
        HomeSubject homeSubject = homeSubjectService.delete(homeSubjectDto);
        if (homeSubject == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(homeSubject);
    }

    /**
     *
     * 分页查询首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<HomeSubject>> queryPage(@RequestBody HomeSubjectDto homeSubjectDto) {
        Page<HomeSubject> homeSubjectPage = new Page<>(homeSubjectDto.getPageIndex(), homeSubjectDto.getPageSize());
        IPage<HomeSubject> page = homeSubjectService.queryPage(homeSubjectDto, homeSubjectPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<HomeSubject> queryById(@RequestBody HomeSubjectDto homeSubjectDto) {
        HomeSubject homeSubject = homeSubjectService.getById(homeSubjectDto.getId());
        if (homeSubject == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(homeSubject);
    }


    /**
     *
     * 查询所有首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<HomeSubject>> queryAll(@RequestBody HomeSubjectDto homeSubjectDto) {
        List<HomeSubject> list = homeSubjectService.queryAll(homeSubjectDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody HomeSubjectDto homeSubjectDto) {

        if (Objects.isNull(homeSubjectDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (homeSubjectService.checkName(homeSubjectDto, homeSubjectDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】名称已存在！");
    }

}

