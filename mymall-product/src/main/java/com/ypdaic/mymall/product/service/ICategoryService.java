package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.CategoryDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 新增商品三级分类
     * @param categoryDto
     * @return
     */
    Category add(CategoryDto categoryDto);

    /**
     * 更新商品三级分类
     * @param categoryDto
     * @return
     */
    Category update(CategoryDto categoryDto);

    /**
     * 删除商品三级分类
     * @param categoryDto
     * @return
     */
    Category delete(CategoryDto categoryDto);

    /**
     * 分页查询商品三级分类
     * @param categoryDto
     * @param categoryPage
     * @return
     */
    IPage<Category> queryPage(CategoryDto categoryDto, Page<Category> categoryPage);


    /**
     * 校验商品三级分类名称
     * @param categoryDto
     * @return
     */
    boolean checkName(CategoryDto categoryDto, boolean isAdd);

    /**
     *
     * 查询所有商品三级分类
     * @return
     */
    List<Category> queryAll(CategoryDto categoryDto);

    /**
     * 查询所有分类
     * @return
     */
    List<Category> listWithTree();

    void updateCascade(Category category);

    /**
     * 找到catelogId的完整路径；
     * [父/子/孙]
     * @param catelogId
     * @return
     */
    Long[] findCatelogPath(Long catelogId);
}
