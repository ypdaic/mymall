package com.ypdaic.mymall.ware.mapper;

import com.ypdaic.mymall.ware.entity.WareSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.ware.vo.WareSkuDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

/**
 * <p>
 * 商品库存 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-11
 */
@Repository
public interface WareSkuMapper extends BaseMapper<WareSku> {

    /**
     * 分页查询商品库存
     * @param wareSkuPage
     * @param wareSkuDto
     * @return
     */
    IPage<WareSku> queryPage(Page<WareSku> wareSkuPage, @Param("dto") WareSkuDto wareSkuDto);

    /**
     * 导出查询数量
     * @param wareSkuDto
     * @return
     */
    Integer queryCount(@Param("dto") WareSkuDto wareSkuDto);

    /**
     * 导出分页查询商品库存
     * @param wareSkuPage
     * @param wareSkuDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> wareSkuPage, @Param("dto") WareSkuDto wareSkuDto);

    /**
     *
     * 查询所有商品库存
     * @return
     */
    List<WareSku> queryAll(@Param("dto") WareSkuDto wareSkuDto);

    void addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);

}
