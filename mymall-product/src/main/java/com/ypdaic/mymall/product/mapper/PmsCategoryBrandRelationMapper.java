package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsCategoryBrandRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsCategoryBrandRelationDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 品牌分类关联 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface PmsCategoryBrandRelationMapper extends BaseMapper<PmsCategoryBrandRelation> {

    /**
     * 分页查询品牌分类关联
     * @param pmsCategoryBrandRelationPage
     * @param pmsCategoryBrandRelationDto
     * @return
     */
    IPage<PmsCategoryBrandRelation> queryPage(Page<PmsCategoryBrandRelation> pmsCategoryBrandRelationPage, @Param("dto") PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto);

    /**
     * 导出查询数量
     * @param pmsCategoryBrandRelationDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto);

    /**
     * 导出分页查询品牌分类关联
     * @param pmsCategoryBrandRelationPage
     * @param pmsCategoryBrandRelationDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsCategoryBrandRelationPage, @Param("dto") PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto);

    /**
     *
     * 查询所有品牌分类关联
     * @return
     */
    List<PmsCategoryBrandRelation> queryAll(@Param("dto") PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto);

}
