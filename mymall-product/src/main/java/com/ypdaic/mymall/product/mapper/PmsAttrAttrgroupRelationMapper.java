package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsAttrAttrgroupRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsAttrAttrgroupRelationDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 属性&属性分组关联 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface PmsAttrAttrgroupRelationMapper extends BaseMapper<PmsAttrAttrgroupRelation> {

    /**
     * 分页查询属性&属性分组关联
     * @param pmsAttrAttrgroupRelationPage
     * @param pmsAttrAttrgroupRelationDto
     * @return
     */
    IPage<PmsAttrAttrgroupRelation> queryPage(Page<PmsAttrAttrgroupRelation> pmsAttrAttrgroupRelationPage, @Param("dto") PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto);

    /**
     * 导出查询数量
     * @param pmsAttrAttrgroupRelationDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto);

    /**
     * 导出分页查询属性&属性分组关联
     * @param pmsAttrAttrgroupRelationPage
     * @param pmsAttrAttrgroupRelationDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsAttrAttrgroupRelationPage, @Param("dto") PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto);

    /**
     *
     * 查询所有属性&属性分组关联
     * @return
     */
    List<PmsAttrAttrgroupRelation> queryAll(@Param("dto") PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto);

}
