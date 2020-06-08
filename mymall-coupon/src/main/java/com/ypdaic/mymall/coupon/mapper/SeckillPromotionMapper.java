package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.SeckillPromotion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.SeckillPromotionDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 秒杀活动 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface SeckillPromotionMapper extends BaseMapper<SeckillPromotion> {

    /**
     * 分页查询秒杀活动
     * @param seckillPromotionPage
     * @param seckillPromotionDto
     * @return
     */
    IPage<SeckillPromotion> queryPage(Page<SeckillPromotion> seckillPromotionPage, @Param("dto") SeckillPromotionDto seckillPromotionDto);

    /**
     * 导出查询数量
     * @param seckillPromotionDto
     * @return
     */
    Integer queryCount(@Param("dto") SeckillPromotionDto seckillPromotionDto);

    /**
     * 导出分页查询秒杀活动
     * @param seckillPromotionPage
     * @param seckillPromotionDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> seckillPromotionPage, @Param("dto") SeckillPromotionDto seckillPromotionDto);

    /**
     *
     * 查询所有秒杀活动
     * @return
     */
    List<SeckillPromotion> queryAll(@Param("dto") SeckillPromotionDto seckillPromotionDto);

}
