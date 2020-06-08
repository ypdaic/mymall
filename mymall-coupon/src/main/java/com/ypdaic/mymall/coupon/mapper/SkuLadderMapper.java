package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.SkuLadder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.SkuLadderDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 商品阶梯价格 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface SkuLadderMapper extends BaseMapper<SkuLadder> {

    /**
     * 分页查询商品阶梯价格
     * @param skuLadderPage
     * @param skuLadderDto
     * @return
     */
    IPage<SkuLadder> queryPage(Page<SkuLadder> skuLadderPage, @Param("dto") SkuLadderDto skuLadderDto);

    /**
     * 导出查询数量
     * @param skuLadderDto
     * @return
     */
    Integer queryCount(@Param("dto") SkuLadderDto skuLadderDto);

    /**
     * 导出分页查询商品阶梯价格
     * @param skuLadderPage
     * @param skuLadderDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> skuLadderPage, @Param("dto") SkuLadderDto skuLadderDto);

    /**
     *
     * 查询所有商品阶梯价格
     * @return
     */
    List<SkuLadder> queryAll(@Param("dto") SkuLadderDto skuLadderDto);

}
