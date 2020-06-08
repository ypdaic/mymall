package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.SeckillSession;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.SeckillSessionDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 秒杀活动场次 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface SeckillSessionMapper extends BaseMapper<SeckillSession> {

    /**
     * 分页查询秒杀活动场次
     * @param seckillSessionPage
     * @param seckillSessionDto
     * @return
     */
    IPage<SeckillSession> queryPage(Page<SeckillSession> seckillSessionPage, @Param("dto") SeckillSessionDto seckillSessionDto);

    /**
     * 导出查询数量
     * @param seckillSessionDto
     * @return
     */
    Integer queryCount(@Param("dto") SeckillSessionDto seckillSessionDto);

    /**
     * 导出分页查询秒杀活动场次
     * @param seckillSessionPage
     * @param seckillSessionDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> seckillSessionPage, @Param("dto") SeckillSessionDto seckillSessionDto);

    /**
     *
     * 查询所有秒杀活动场次
     * @return
     */
    List<SeckillSession> queryAll(@Param("dto") SeckillSessionDto seckillSessionDto);

}
