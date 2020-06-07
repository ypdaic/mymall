package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsSkuImages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsSkuImagesDto;
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
 * @since 2020-06-07
 */
public interface PmsSkuImagesMapper extends BaseMapper<PmsSkuImages> {

    /**
     * 分页查询sku图片
     * @param pmsSkuImagesPage
     * @param pmsSkuImagesDto
     * @return
     */
    IPage<PmsSkuImages> queryPage(Page<PmsSkuImages> pmsSkuImagesPage, @Param("dto") PmsSkuImagesDto pmsSkuImagesDto);

    /**
     * 导出查询数量
     * @param pmsSkuImagesDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsSkuImagesDto pmsSkuImagesDto);

    /**
     * 导出分页查询sku图片
     * @param pmsSkuImagesPage
     * @param pmsSkuImagesDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsSkuImagesPage, @Param("dto") PmsSkuImagesDto pmsSkuImagesDto);

    /**
     *
     * 查询所有sku图片
     * @return
     */
    List<PmsSkuImages> queryAll(@Param("dto") PmsSkuImagesDto pmsSkuImagesDto);

}
