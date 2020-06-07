package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsCategoryDto;
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
 * @since 2020-06-07
 */
public interface PmsCategoryMapper extends BaseMapper<PmsCategory> {

    /**
     * 分页查询商品三级分类
     * @param pmsCategoryPage
     * @param pmsCategoryDto
     * @return
     */
    IPage<PmsCategory> queryPage(Page<PmsCategory> pmsCategoryPage, @Param("dto") PmsCategoryDto pmsCategoryDto);

    /**
     * 导出查询数量
     * @param pmsCategoryDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsCategoryDto pmsCategoryDto);

    /**
     * 导出分页查询商品三级分类
     * @param pmsCategoryPage
     * @param pmsCategoryDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsCategoryPage, @Param("dto") PmsCategoryDto pmsCategoryDto);

    /**
     *
     * 查询所有商品三级分类
     * @return
     */
    List<PmsCategory> queryAll(@Param("dto") PmsCategoryDto pmsCategoryDto);

}
