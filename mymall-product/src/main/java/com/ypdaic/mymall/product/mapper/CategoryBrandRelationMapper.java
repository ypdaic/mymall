package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.CategoryBrandRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.CategoryBrandRelationDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

/**
 * <p>
 * 品牌分类关联 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Repository
public interface CategoryBrandRelationMapper extends BaseMapper<CategoryBrandRelation> {

    /**
     * 分页查询品牌分类关联
     * @param categoryBrandRelationPage
     * @param categoryBrandRelationDto
     * @return
     */
    IPage<CategoryBrandRelation> queryPage(Page<CategoryBrandRelation> categoryBrandRelationPage, @Param("dto") CategoryBrandRelationDto categoryBrandRelationDto);

    /**
     * 导出查询数量
     * @param categoryBrandRelationDto
     * @return
     */
    Integer queryCount(@Param("dto") CategoryBrandRelationDto categoryBrandRelationDto);

    /**
     * 导出分页查询品牌分类关联
     * @param categoryBrandRelationPage
     * @param categoryBrandRelationDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> categoryBrandRelationPage, @Param("dto") CategoryBrandRelationDto categoryBrandRelationDto);

    /**
     *
     * 查询所有品牌分类关联
     * @return
     */
    List<CategoryBrandRelation> queryAll(@Param("dto") CategoryBrandRelationDto categoryBrandRelationDto);

    void updateCategory(@Param("catId") Long catId, @Param("name") String name);
}
