package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsSkuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsSkuInfoDto;
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
 * @since 2020-06-07
 */
public interface PmsSkuInfoMapper extends BaseMapper<PmsSkuInfo> {

    /**
     * 分页查询sku信息
     * @param pmsSkuInfoPage
     * @param pmsSkuInfoDto
     * @return
     */
    IPage<PmsSkuInfo> queryPage(Page<PmsSkuInfo> pmsSkuInfoPage, @Param("dto") PmsSkuInfoDto pmsSkuInfoDto);

    /**
     * 导出查询数量
     * @param pmsSkuInfoDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsSkuInfoDto pmsSkuInfoDto);

    /**
     * 导出分页查询sku信息
     * @param pmsSkuInfoPage
     * @param pmsSkuInfoDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsSkuInfoPage, @Param("dto") PmsSkuInfoDto pmsSkuInfoDto);

    /**
     *
     * 查询所有sku信息
     * @return
     */
    List<PmsSkuInfo> queryAll(@Param("dto") PmsSkuInfoDto pmsSkuInfoDto);

}
