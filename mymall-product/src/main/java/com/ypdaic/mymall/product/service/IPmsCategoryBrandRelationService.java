package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsCategoryBrandRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsCategoryBrandRelationDto;
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
 * @since 2020-06-07
 */
public interface IPmsCategoryBrandRelationService extends IService<PmsCategoryBrandRelation> {

    /**
     * 新增品牌分类关联
     * @param pmsCategoryBrandRelationDto
     * @return
     */
    PmsCategoryBrandRelation add(PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto);

    /**
     * 更新品牌分类关联
     * @param pmsCategoryBrandRelationDto
     * @return
     */
    PmsCategoryBrandRelation update(PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto);

    /**
     * 删除品牌分类关联
     * @param pmsCategoryBrandRelationDto
     * @return
     */
    PmsCategoryBrandRelation delete(PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto);

    /**
     * 分页查询品牌分类关联
     * @param pmsCategoryBrandRelationDto
     * @param pmsCategoryBrandRelationPage
     * @return
     */
    IPage<PmsCategoryBrandRelation> queryPage(PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto, Page<PmsCategoryBrandRelation> pmsCategoryBrandRelationPage);


    /**
     * 校验品牌分类关联名称
     * @param pmsCategoryBrandRelationDto
     * @return
     */
    boolean checkName(PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto, boolean isAdd);

    /**
     *
     * 查询所有品牌分类关联
     * @return
     */
    List<PmsCategoryBrandRelation> queryAll(PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto);
}
