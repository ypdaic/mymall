package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.common.to.SkuReductionTo;
import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.coupon.entity.SkuFullReduction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.SkuFullReductionDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 商品满减信息 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface SkuFullReductionMapper extends BaseMapper<SkuFullReduction> {

    /**
     * 分页查询商品满减信息
     * @param skuFullReductionPage
     * @param skuFullReductionDto
     * @return
     */
    IPage<SkuFullReduction> queryPage(Page<SkuFullReduction> skuFullReductionPage, @Param("dto") SkuFullReductionDto skuFullReductionDto);

    /**
     * 导出查询数量
     * @param skuFullReductionDto
     * @return
     */
    Integer queryCount(@Param("dto") SkuFullReductionDto skuFullReductionDto);

    /**
     * 导出分页查询商品满减信息
     * @param skuFullReductionPage
     * @param skuFullReductionDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> skuFullReductionPage, @Param("dto") SkuFullReductionDto skuFullReductionDto);

    /**
     *
     * 查询所有商品满减信息
     * @return
     */
    List<SkuFullReduction> queryAll(@Param("dto") SkuFullReductionDto skuFullReductionDto);

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuReduction(SkuReductionTo reductionTo);


}
