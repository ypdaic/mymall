package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsCategoryDto;
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
 * @since 2020-06-07
 */
public interface IPmsCategoryService extends IService<PmsCategory> {

    /**
     * 新增商品三级分类
     * @param pmsCategoryDto
     * @return
     */
    PmsCategory add(PmsCategoryDto pmsCategoryDto);

    /**
     * 更新商品三级分类
     * @param pmsCategoryDto
     * @return
     */
    PmsCategory update(PmsCategoryDto pmsCategoryDto);

    /**
     * 删除商品三级分类
     * @param pmsCategoryDto
     * @return
     */
    PmsCategory delete(PmsCategoryDto pmsCategoryDto);

    /**
     * 分页查询商品三级分类
     * @param pmsCategoryDto
     * @param pmsCategoryPage
     * @return
     */
    IPage<PmsCategory> queryPage(PmsCategoryDto pmsCategoryDto, Page<PmsCategory> pmsCategoryPage);


    /**
     * 校验商品三级分类名称
     * @param pmsCategoryDto
     * @return
     */
    boolean checkName(PmsCategoryDto pmsCategoryDto, boolean isAdd);

    /**
     *
     * 查询所有商品三级分类
     * @return
     */
    List<PmsCategory> queryAll(PmsCategoryDto pmsCategoryDto);
}
