package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.SkuSaleAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.SkuItemSaleAttrVo;
import com.ypdaic.mymall.product.vo.SkuSaleAttrValueDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * sku销售属性&值 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface SkuSaleAttrValueMapper extends BaseMapper<SkuSaleAttrValue> {

    /**
     * 分页查询sku销售属性&值
     * @param skuSaleAttrValuePage
     * @param skuSaleAttrValueDto
     * @return
     */
    IPage<SkuSaleAttrValue> queryPage(Page<SkuSaleAttrValue> skuSaleAttrValuePage, @Param("dto") SkuSaleAttrValueDto skuSaleAttrValueDto);

    /**
     * 导出查询数量
     * @param skuSaleAttrValueDto
     * @return
     */
    Integer queryCount(@Param("dto") SkuSaleAttrValueDto skuSaleAttrValueDto);

    /**
     * 导出分页查询sku销售属性&值
     * @param skuSaleAttrValuePage
     * @param skuSaleAttrValueDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> skuSaleAttrValuePage, @Param("dto") SkuSaleAttrValueDto skuSaleAttrValueDto);

    /**
     *
     * 查询所有sku销售属性&值
     * @return
     */
    List<SkuSaleAttrValue> queryAll(@Param("dto") SkuSaleAttrValueDto skuSaleAttrValueDto);

    List<SkuItemSaleAttrVo> getSaleAttrsBySpuId(@Param("spuId") Long spuId);

    List<String> getSkuSaleAttrValues(Long skuId);
}
