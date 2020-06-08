package com.ypdaic.mymall.order.mapper;

import com.ypdaic.mymall.order.entity.OrderOperateHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.order.vo.OrderOperateHistoryDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 订单操作历史记录 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface OrderOperateHistoryMapper extends BaseMapper<OrderOperateHistory> {

    /**
     * 分页查询订单操作历史记录
     * @param orderOperateHistoryPage
     * @param orderOperateHistoryDto
     * @return
     */
    IPage<OrderOperateHistory> queryPage(Page<OrderOperateHistory> orderOperateHistoryPage, @Param("dto") OrderOperateHistoryDto orderOperateHistoryDto);

    /**
     * 导出查询数量
     * @param orderOperateHistoryDto
     * @return
     */
    Integer queryCount(@Param("dto") OrderOperateHistoryDto orderOperateHistoryDto);

    /**
     * 导出分页查询订单操作历史记录
     * @param orderOperateHistoryPage
     * @param orderOperateHistoryDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> orderOperateHistoryPage, @Param("dto") OrderOperateHistoryDto orderOperateHistoryDto);

    /**
     *
     * 查询所有订单操作历史记录
     * @return
     */
    List<OrderOperateHistory> queryAll(@Param("dto") OrderOperateHistoryDto orderOperateHistoryDto);

}
