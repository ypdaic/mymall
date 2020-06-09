package com.ypdaic.mymall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ypdaic.mymall.product.entity.Brand;
import com.ypdaic.mymall.product.entity.Category;
import com.ypdaic.mymall.product.entity.CategoryBrandRelation;
import com.ypdaic.mymall.product.mapper.BrandMapper;
import com.ypdaic.mymall.product.mapper.CategoryBrandRelationMapper;
import com.ypdaic.mymall.product.mapper.CategoryMapper;
import com.ypdaic.mymall.product.service.IBrandService;
import com.ypdaic.mymall.product.service.ICategoryBrandRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.CategoryBrandRelationDto;
import com.ypdaic.mymall.product.enums.CategoryBrandRelationExcelHeadersEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypdaic.mymall.common.util.ExcelUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.util.JavaUtils;

/**
 * <p>
 * 品牌分类关联 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelation> implements ICategoryBrandRelationService {

    @Autowired
    BrandMapper brandDao;

    @Autowired
    CategoryMapper categoryDao;

    @Autowired
    CategoryBrandRelationMapper relationDao;

    @Autowired
    IBrandService brandService;

    /**
     * 新增品牌分类关联
     * @param categoryBrandRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CategoryBrandRelation add(CategoryBrandRelationDto categoryBrandRelationDto) {

        CategoryBrandRelation categoryBrandRelation = new CategoryBrandRelation();
        categoryBrandRelation.setBrandId(categoryBrandRelationDto.getBrandId());
        categoryBrandRelation.setCatelogId(categoryBrandRelationDto.getCatelogId());
        categoryBrandRelation.setBrandName(categoryBrandRelationDto.getBrandName());
        categoryBrandRelation.setCatelogName(categoryBrandRelationDto.getCatelogName());
        categoryBrandRelation.insert();
        return categoryBrandRelation;
    }

    /**
     * 更新品牌分类关联
     * @param categoryBrandRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CategoryBrandRelation update(CategoryBrandRelationDto categoryBrandRelationDto) {
        CategoryBrandRelation categoryBrandRelation = baseMapper.selectById(categoryBrandRelationDto.getId());
        if (categoryBrandRelation == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(categoryBrandRelationDto.getBrandId(), categoryBrandRelation::setBrandId);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryBrandRelationDto.getCatelogId(), categoryBrandRelation::setCatelogId);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryBrandRelationDto.getBrandName(), categoryBrandRelation::setBrandName);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryBrandRelationDto.getCatelogName(), categoryBrandRelation::setCatelogName);
        categoryBrandRelation.updateById();
        return categoryBrandRelation;
    }

    /**
     * 删除品牌分类关联
     * @param categoryBrandRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CategoryBrandRelation delete(CategoryBrandRelationDto categoryBrandRelationDto) {
        CategoryBrandRelation categoryBrandRelation = baseMapper.selectById(categoryBrandRelationDto.getId());
        if (categoryBrandRelation == null) {
            return null;
        }
        categoryBrandRelation.deleteById();

        return categoryBrandRelation;
    }

    /**
     * 分页查询品牌分类关联
     * @param categoryBrandRelationDto
     * @param categoryBrandRelationPage
     * @return
     */
    @Override
    public IPage<CategoryBrandRelation> queryPage(CategoryBrandRelationDto categoryBrandRelationDto, Page<CategoryBrandRelation> categoryBrandRelationPage) {

        return baseMapper.queryPage(categoryBrandRelationPage, categoryBrandRelationDto);
    }


    /**
     * 校验品牌分类关联名称
     * @param categoryBrandRelationDto
     * @return
     */
    @Override
    public boolean checkName(CategoryBrandRelationDto categoryBrandRelationDto, boolean isAdd) {

        QueryWrapper<CategoryBrandRelation> categoryBrandRelationQueryWrapper = new QueryWrapper<CategoryBrandRelation>();
        categoryBrandRelationQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        categoryBrandRelationQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            categoryBrandRelationQueryWrapper.ne("id", categoryBrandRelationDto.getId());
        }

        Integer count = baseMapper.selectCount(categoryBrandRelationQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有品牌分类关联
     * @return
     */
    public List<CategoryBrandRelation> queryAll(CategoryBrandRelationDto categoryBrandRelationDto) {
        return baseMapper.queryAll(categoryBrandRelationDto);
    }

    @Override
    public void updateCategory(Long catId, String name) {
        this.baseMapper.updateCategory(catId,name);
    }

    @Override
    public void saveDetail(CategoryBrandRelation categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        //1、查询详细名字
        Brand brandEntity = brandDao.selectById(brandId);
        Category categoryEntity = categoryDao.selectById(catelogId);

        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());

        this.save(categoryBrandRelation);

    }

    @Override
    public void updateBrand(Long brandId, String name) {
        CategoryBrandRelation relationEntity = new CategoryBrandRelation();
        relationEntity.setBrandId(brandId);
        relationEntity.setBrandName(name);
        this.update(relationEntity,new UpdateWrapper<CategoryBrandRelation>().eq("brand_id",brandId));
    }

    @Override
    public List<Brand> getBrandsByCatId(Long catId) {

        List<CategoryBrandRelation> catelogId = relationDao.selectList(new QueryWrapper<CategoryBrandRelation>().eq("catelog_id", catId));
        List<Brand> collect = catelogId.stream().map(item -> {
            Long brandId = item.getBrandId();
            Brand byId = brandService.getById(brandId);
            return byId;
        }).collect(Collectors.toList());
        return collect;
    }

}
