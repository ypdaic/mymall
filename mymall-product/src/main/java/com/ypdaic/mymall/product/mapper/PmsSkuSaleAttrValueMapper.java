package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsSkuSaleAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsSkuSaleAttrValueDto;
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
 * @since 2020-06-07
 */
public interface PmsSkuSaleAttrValueMapper extends BaseMapper<PmsSkuSaleAttrValue> {

    /**
     * 分页查询sku销售属性&值
     * @param pmsSkuSaleAttrValuePage
     * @param pmsSkuSaleAttrValueDto
     * @return
     */
    IPage<PmsSkuSaleAttrValue> queryPage(Page<PmsSkuSaleAttrValue> pmsSkuSaleAttrValuePage, @Param("dto") PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto);

    /**
     * 导出查询数量
     * @param pmsSkuSaleAttrValueDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto);

    /**
     * 导出分页查询sku销售属性&值
     * @param pmsSkuSaleAttrValuePage
     * @param pmsSkuSaleAttrValueDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsSkuSaleAttrValuePage, @Param("dto") PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto);

    /**
     *
     * 查询所有sku销售属性&值
     * @return
     */
    List<PmsSkuSaleAttrValue> queryAll(@Param("dto") PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto);

}
