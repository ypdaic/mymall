package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.AttrAttrgroupRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.AttrAttrgroupRelationDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

/**
 * <p>
 * 属性&属性分组关联 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Repository
public interface AttrAttrgroupRelationMapper extends BaseMapper<AttrAttrgroupRelation> {

    /**
     * 分页查询属性&属性分组关联
     * @param attrAttrgroupRelationPage
     * @param attrAttrgroupRelationDto
     * @return
     */
    IPage<AttrAttrgroupRelation> queryPage(Page<AttrAttrgroupRelation> attrAttrgroupRelationPage, @Param("dto") AttrAttrgroupRelationDto attrAttrgroupRelationDto);

    /**
     * 导出查询数量
     * @param attrAttrgroupRelationDto
     * @return
     */
    Integer queryCount(@Param("dto") AttrAttrgroupRelationDto attrAttrgroupRelationDto);

    /**
     * 导出分页查询属性&属性分组关联
     * @param attrAttrgroupRelationPage
     * @param attrAttrgroupRelationDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> attrAttrgroupRelationPage, @Param("dto") AttrAttrgroupRelationDto attrAttrgroupRelationDto);

    /**
     *
     * 查询所有属性&属性分组关联
     * @return
     */
    List<AttrAttrgroupRelation> queryAll(@Param("dto") AttrAttrgroupRelationDto attrAttrgroupRelationDto);

    void deleteBatchRelation(@Param("entities") List<AttrAttrgroupRelation> entities);


}
