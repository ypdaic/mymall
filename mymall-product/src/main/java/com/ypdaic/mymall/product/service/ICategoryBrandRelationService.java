package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.CategoryBrandRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.CategoryBrandRelationDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 品牌分类关联 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ICategoryBrandRelationService extends IService<CategoryBrandRelation> {

    /**
     * 新增品牌分类关联
     * @param categoryBrandRelationDto
     * @return
     */
    CategoryBrandRelation add(CategoryBrandRelationDto categoryBrandRelationDto);

    /**
     * 更新品牌分类关联
     * @param categoryBrandRelationDto
     * @return
     */
    CategoryBrandRelation update(CategoryBrandRelationDto categoryBrandRelationDto);

    /**
     * 删除品牌分类关联
     * @param categoryBrandRelationDto
     * @return
     */
    CategoryBrandRelation delete(CategoryBrandRelationDto categoryBrandRelationDto);

    /**
     * 分页查询品牌分类关联
     * @param categoryBrandRelationDto
     * @param categoryBrandRelationPage
     * @return
     */
    IPage<CategoryBrandRelation> queryPage(CategoryBrandRelationDto categoryBrandRelationDto, Page<CategoryBrandRelation> categoryBrandRelationPage);


    /**
     * 校验品牌分类关联名称
     * @param categoryBrandRelationDto
     * @return
     */
    boolean checkName(CategoryBrandRelationDto categoryBrandRelationDto, boolean isAdd);

    /**
     *
     * 查询所有品牌分类关联
     * @return
     */
    List<CategoryBrandRelation> queryAll(CategoryBrandRelationDto categoryBrandRelationDto);
}
