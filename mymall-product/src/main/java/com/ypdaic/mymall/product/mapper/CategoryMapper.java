package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.CategoryDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 商品三级分类 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 分页查询商品三级分类
     * @param categoryPage
     * @param categoryDto
     * @return
     */
    IPage<Category> queryPage(Page<Category> categoryPage, @Param("dto") CategoryDto categoryDto);

    /**
     * 导出查询数量
     * @param categoryDto
     * @return
     */
    Integer queryCount(@Param("dto") CategoryDto categoryDto);

    /**
     * 导出分页查询商品三级分类
     * @param categoryPage
     * @param categoryDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> categoryPage, @Param("dto") CategoryDto categoryDto);

    /**
     *
     * 查询所有商品三级分类
     * @return
     */
    List<Category> queryAll(@Param("dto") CategoryDto categoryDto);

}
