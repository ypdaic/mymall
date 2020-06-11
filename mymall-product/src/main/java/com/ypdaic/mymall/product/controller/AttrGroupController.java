package com.ypdaic.mymall.product.controller;


import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.product.entity.Attr;
import com.ypdaic.mymall.product.service.IAttrAttrgroupRelationService;
import com.ypdaic.mymall.product.service.IAttrService;
import com.ypdaic.mymall.product.service.ICategoryService;
import com.ypdaic.mymall.product.vo.AttrAttrgroupRelationDto;
import com.ypdaic.mymall.product.vo.AttrGroupWithAttrsDto;
import org.springframework.web.bind.annotation.*;

import com.ypdaic.mymall.common.base.BaseController;

import com.ypdaic.mymall.product.service.IAttrGroupService;
import com.ypdaic.mymall.product.vo.AttrGroupDto;
import com.ypdaic.mymall.product.entity.AttrGroup;

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
 * 属性分组 前端控制器
 *
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@RestController
@RequestMapping("/product/attrgroup")
public class AttrGroupController extends BaseController {

    @Autowired
    IAttrGroupService attrGroupService;
    @Autowired
    ICategoryService categoryService;

    @Autowired
    IAttrService attrService;

    @Autowired
    IAttrAttrgroupRelationService relationService;


    /**
     *
     * 新增属性分组
     * @param attrGroupDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/add")
    public Result<AttrGroup> add(@RequestBody @Validated AttrGroupDto attrGroupDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }

        AttrGroup attrGroup = attrGroupService.add(attrGroupDto);

        return ResultUtil.successOfInsert(attrGroup);
    }

    /**
     *
     * 修改属性分组
     * @param attrGroupDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/update")
    public Result<AttrGroup> update(@RequestBody @Validated AttrGroupDto attrGroupDto, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return paramError(bindingResult);
        }
        AttrGroup attrGroup = attrGroupService.update(attrGroupDto);
        if (attrGroup == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfUpdate(attrGroup);
    }

    /**
     *
     * 删除属性分组
     * @param attrGroupDto
     * @param httpServletRequest 
     * @return
     */
    @PostMapping("/delete")

    public Result<AttrGroup> delete(@RequestBody AttrGroupDto attrGroupDto, HttpServletRequest httpServletRequest) {
        AttrGroup attrGroup = attrGroupService.delete(attrGroupDto);
        if (attrGroup == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.successOfDelete(attrGroup);
    }

    /**
     *
     * 分页查询属性分组
     * @param attrGroupDto
     * @return
     */
    @PostMapping("/queryPage")
    public Result<IPage<AttrGroup>> queryPage(@RequestBody AttrGroupDto attrGroupDto) {
        Page<AttrGroup> attrGroupPage = new Page<>(attrGroupDto.getPageIndex(), attrGroupDto.getPageSize());
        IPage<AttrGroup> page = attrGroupService.queryPage(attrGroupDto, attrGroupPage);
        return ResultUtil.success(page);
    }

    /**
     *
     * 根据id查询属性分组
     * @param attrGroupDto
     * @return
     */
    @PostMapping("/queryById")
    public Result<AttrGroup> queryById(@RequestBody AttrGroupDto attrGroupDto) {
        AttrGroup attrGroup = attrGroupService.getById(attrGroupDto.getId());
        if (attrGroup == null) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        return ResultUtil.success(attrGroup);
    }


    /**
     *
     * 查询所有属性分组
     * @return
     */
    @PostMapping("/queryAll")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<List<AttrGroup>> queryAll(@RequestBody AttrGroupDto attrGroupDto) {
        List<AttrGroup> list = attrGroupService.queryAll(attrGroupDto);
        return ResultUtil.success(list);
    }

    /**
     * 校验属性分组名称
     *
     * @return
     */
    @PostMapping("/checkName")
    @NeedAuth(NeedAuthEnum.TOKEN)
    public Result<String> checkName(@RequestBody AttrGroupDto attrGroupDto) {

        if (Objects.isNull(attrGroupDto.getIsAdd())) {
            return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE);
        }
        if (attrGroupService.checkName(attrGroupDto, attrGroupDto.getIsAdd())) {
            return ResultUtil.success();
        }
        return ResultUtil.failure(40001, "属性分组名称已存在！");
    }

    ///product/attrgroup/attr/relation
    @PostMapping("/attr/relation")
    public R addRelation(@RequestBody List<AttrAttrgroupRelationDto> vos){

        relationService.saveBatch(vos);
        return R.ok();
    }

    ///product/attrgroup/{catelogId}/withattr
    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catelogId")Long catelogId){

        //1、查出当前分类下的所有属性分组，
        //2、查出每个属性分组的所有属性
        List<AttrGroupWithAttrsDto> vos =  attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data",vos);
    }

    /**
     * 查询属性分组关联的规格参数
     * @param attrgroupId
     * @return
     */
    ///product/attrgroup/{attrgroupId}/attr/relation
    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId){
        List<Attr> entities =  attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data",entities);
    }

    /**
     * 查询属性分组还没有关联的规格参数
     * @param attrgroupId
     * @param params
     * @return
     */
    ///product/attrgroup/{attrgroupId}/noattr/relation
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable("attrgroupId") Long attrgroupId,
                            @RequestParam Map<String, Object> params){
        PageUtils page = attrService.getNoRelationAttr(params,attrgroupId);
        return R.ok().put("page",page);
    }

    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody  AttrAttrgroupRelationDto[] vos){
        attrService.deleteRelation(vos);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable("catelogId") Long catelogId){
//        PageUtils page = attrGroupService.queryPage(params);

        PageUtils page = attrGroupService.queryPage(params,catelogId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
        AttrGroup attrGroup = attrGroupService.getById(attrGroupId);

        Long catelogId = attrGroup.getCatelogId();
        // 查询已经绑定的分类
        Long[] path = categoryService.findCatelogPath(catelogId);

        attrGroup.setCatelogPath(path);

        return R.ok().put("attrGroup", attrGroup);
    }



    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroup attrGroup){
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroup attrGroup){
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}

