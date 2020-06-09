package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.product.entity.AttrAttrgroupRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.AttrAttrgroupRelationDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 属性&属性分组关联 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IAttrAttrgroupRelationService extends IService<AttrAttrgroupRelation> {

    /**
     * 新增属性&属性分组关联
     * @param attrAttrgroupRelationDto
     * @return
     */
    AttrAttrgroupRelation add(AttrAttrgroupRelationDto attrAttrgroupRelationDto);

    /**
     * 更新属性&属性分组关联
     * @param attrAttrgroupRelationDto
     * @return
     */
    AttrAttrgroupRelation update(AttrAttrgroupRelationDto attrAttrgroupRelationDto);

    /**
     * 删除属性&属性分组关联
     * @param attrAttrgroupRelationDto
     * @return
     */
    AttrAttrgroupRelation delete(AttrAttrgroupRelationDto attrAttrgroupRelationDto);

    /**
     * 分页查询属性&属性分组关联
     * @param attrAttrgroupRelationDto
     * @param attrAttrgroupRelationPage
     * @return
     */
    IPage<AttrAttrgroupRelation> queryPage(AttrAttrgroupRelationDto attrAttrgroupRelationDto, Page<AttrAttrgroupRelation> attrAttrgroupRelationPage);


    /**
     * 校验属性&属性分组关联名称
     * @param attrAttrgroupRelationDto
     * @return
     */
    boolean checkName(AttrAttrgroupRelationDto attrAttrgroupRelationDto, boolean isAdd);

    /**
     *
     * 查询所有属性&属性分组关联
     * @return
     */
    List<AttrAttrgroupRelation> queryAll(AttrAttrgroupRelationDto attrAttrgroupRelationDto);

    PageUtils queryPage(Map<String, Object> params);

    void saveBatch(List<AttrAttrgroupRelationDto> vos);
}
