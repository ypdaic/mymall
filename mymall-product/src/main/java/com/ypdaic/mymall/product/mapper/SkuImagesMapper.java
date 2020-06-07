package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.SkuImages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.SkuImagesDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * sku图片 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface SkuImagesMapper extends BaseMapper<SkuImages> {

    /**
     * 分页查询sku图片
     * @param skuImagesPage
     * @param skuImagesDto
     * @return
     */
    IPage<SkuImages> queryPage(Page<SkuImages> skuImagesPage, @Param("dto") SkuImagesDto skuImagesDto);

    /**
     * 导出查询数量
     * @param skuImagesDto
     * @return
     */
    Integer queryCount(@Param("dto") SkuImagesDto skuImagesDto);

    /**
     * 导出分页查询sku图片
     * @param skuImagesPage
     * @param skuImagesDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> skuImagesPage, @Param("dto") SkuImagesDto skuImagesDto);

    /**
     *
     * 查询所有sku图片
     * @return
     */
    List<SkuImages> queryAll(@Param("dto") SkuImagesDto skuImagesDto);

}
