package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsAttrAttrgroupRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsAttrAttrgroupRelationDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 属性&属性分组关联 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface IPmsAttrAttrgroupRelationService extends IService<PmsAttrAttrgroupRelation> {

    /**
     * 新增属性&属性分组关联
     * @param pmsAttrAttrgroupRelationDto
     * @return
     */
    PmsAttrAttrgroupRelation add(PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto);

    /**
     * 更新属性&属性分组关联
     * @param pmsAttrAttrgroupRelationDto
     * @return
     */
    PmsAttrAttrgroupRelation update(PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto);

    /**
     * 删除属性&属性分组关联
     * @param pmsAttrAttrgroupRelationDto
     * @return
     */
    PmsAttrAttrgroupRelation delete(PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto);

    /**
     * 分页查询属性&属性分组关联
     * @param pmsAttrAttrgroupRelationDto
     * @param pmsAttrAttrgroupRelationPage
     * @return
     */
    IPage<PmsAttrAttrgroupRelation> queryPage(PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto, Page<PmsAttrAttrgroupRelation> pmsAttrAttrgroupRelationPage);


    /**
     * 校验属性&属性分组关联名称
     * @param pmsAttrAttrgroupRelationDto
     * @return
     */
    boolean checkName(PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto, boolean isAdd);

    /**
     *
     * 查询所有属性&属性分组关联
     * @return
     */
    List<PmsAttrAttrgroupRelation> queryAll(PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto);
}
