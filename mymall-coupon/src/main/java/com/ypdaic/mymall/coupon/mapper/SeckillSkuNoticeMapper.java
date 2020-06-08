package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.SeckillSkuNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.SeckillSkuNoticeDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 秒杀商品通知订阅 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface SeckillSkuNoticeMapper extends BaseMapper<SeckillSkuNotice> {

    /**
     * 分页查询秒杀商品通知订阅
     * @param seckillSkuNoticePage
     * @param seckillSkuNoticeDto
     * @return
     */
    IPage<SeckillSkuNotice> queryPage(Page<SeckillSkuNotice> seckillSkuNoticePage, @Param("dto") SeckillSkuNoticeDto seckillSkuNoticeDto);

    /**
     * 导出查询数量
     * @param seckillSkuNoticeDto
     * @return
     */
    Integer queryCount(@Param("dto") SeckillSkuNoticeDto seckillSkuNoticeDto);

    /**
     * 导出分页查询秒杀商品通知订阅
     * @param seckillSkuNoticePage
     * @param seckillSkuNoticeDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> seckillSkuNoticePage, @Param("dto") SeckillSkuNoticeDto seckillSkuNoticeDto);

    /**
     *
     * 查询所有秒杀商品通知订阅
     * @return
     */
    List<SeckillSkuNotice> queryAll(@Param("dto") SeckillSkuNoticeDto seckillSkuNoticeDto);

}
