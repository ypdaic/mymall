package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.SeckillSkuRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.SeckillSkuRelationDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 秒杀活动商品关联 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface SeckillSkuRelationMapper extends BaseMapper<SeckillSkuRelation> {

    /**
     * 分页查询秒杀活动商品关联
     * @param seckillSkuRelationPage
     * @param seckillSkuRelationDto
     * @return
     */
    IPage<SeckillSkuRelation> queryPage(Page<SeckillSkuRelation> seckillSkuRelationPage, @Param("dto") SeckillSkuRelationDto seckillSkuRelationDto);

    /**
     * 导出查询数量
     * @param seckillSkuRelationDto
     * @return
     */
    Integer queryCount(@Param("dto") SeckillSkuRelationDto seckillSkuRelationDto);

    /**
     * 导出分页查询秒杀活动商品关联
     * @param seckillSkuRelationPage
     * @param seckillSkuRelationDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> seckillSkuRelationPage, @Param("dto") SeckillSkuRelationDto seckillSkuRelationDto);

    /**
     *
     * 查询所有秒杀活动商品关联
     * @return
     */
    List<SeckillSkuRelation> queryAll(@Param("dto") SeckillSkuRelationDto seckillSkuRelationDto);

}
