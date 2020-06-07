package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.Category;
import com.ypdaic.mymall.product.mapper.CategoryMapper;
import com.ypdaic.mymall.product.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.CategoryDto;
import com.ypdaic.mymall.product.enums.CategoryExcelHeadersEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypdaic.mymall.common.util.ExcelUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.util.JavaUtils;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {


    /**
     * 新增商品三级分类
     * @param categoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Category add(CategoryDto categoryDto) {

        Category category = new Category();
        category.setCatId(categoryDto.getCatId());
        category.setName(categoryDto.getName());
        category.setParentCid(categoryDto.getParentCid());
        category.setCatLevel(categoryDto.getCatLevel());
        category.setShowStatus(categoryDto.getShowStatus());
        category.setSort(categoryDto.getSort());
        category.setIcon(categoryDto.getIcon());
        category.setProductUnit(categoryDto.getProductUnit());
        category.setProductCount(categoryDto.getProductCount());
        category.insert();
        return category;
    }

    /**
     * 更新商品三级分类
     * @param categoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Category update(CategoryDto categoryDto) {
        Category category = baseMapper.selectById(categoryDto.getId());
        if (category == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getCatId(), category::setCatId);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getName(), category::setName);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getParentCid(), category::setParentCid);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getCatLevel(), category::setCatLevel);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getShowStatus(), category::setShowStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getSort(), category::setSort);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getIcon(), category::setIcon);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getProductUnit(), category::setProductUnit);
        JavaUtils.INSTANCE.acceptIfNotNull(categoryDto.getProductCount(), category::setProductCount);
        category.updateById();
        return category;
    }

    /**
     * 删除商品三级分类
     * @param categoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Category delete(CategoryDto categoryDto) {
        Category category = baseMapper.selectById(categoryDto.getId());
        if (category == null) {
            return null;
        }
        category.deleteById();

        return category;
    }

    /**
     * 分页查询商品三级分类
     * @param categoryDto
     * @param categoryPage
     * @return
     */
    @Override
    public IPage<Category> queryPage(CategoryDto categoryDto, Page<Category> categoryPage) {

        return baseMapper.queryPage(categoryPage, categoryDto);
    }


    /**
     * 校验商品三级分类名称
     * @param categoryDto
     * @return
     */
    @Override
    public boolean checkName(CategoryDto categoryDto, boolean isAdd) {

        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<Category>();
        categoryQueryWrapper.eq("name", categoryDto.getName());
        categoryQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        categoryQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            categoryQueryWrapper.ne("id", categoryDto.getId());
        }

        Integer count = baseMapper.selectCount(categoryQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有商品三级分类
     * @return
     */
    public List<Category> queryAll(CategoryDto categoryDto) {
        return baseMapper.queryAll(categoryDto);
    }

}
