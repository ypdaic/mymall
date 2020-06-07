package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.SkuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.SkuInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * sku信息 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {

    /**
     * 分页查询sku信息
     * @param skuInfoPage
     * @param skuInfoDto
     * @return
     */
    IPage<SkuInfo> queryPage(Page<SkuInfo> skuInfoPage, @Param("dto") SkuInfoDto skuInfoDto);

    /**
     * 导出查询数量
     * @param skuInfoDto
     * @return
     */
    Integer queryCount(@Param("dto") SkuInfoDto skuInfoDto);

    /**
     * 导出分页查询sku信息
     * @param skuInfoPage
     * @param skuInfoDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> skuInfoPage, @Param("dto") SkuInfoDto skuInfoDto);

    /**
     *
     * 查询所有sku信息
     * @return
     */
    List<SkuInfo> queryAll(@Param("dto") SkuInfoDto skuInfoDto);

}
