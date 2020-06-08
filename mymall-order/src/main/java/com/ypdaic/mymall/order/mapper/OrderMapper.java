package com.ypdaic.mymall.order.mapper;

import com.ypdaic.mymall.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.order.vo.OrderDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 分页查询订单
     * @param orderPage
     * @param orderDto
     * @return
     */
    IPage<Order> queryPage(Page<Order> orderPage, @Param("dto") OrderDto orderDto);

    /**
     * 导出查询数量
     * @param orderDto
     * @return
     */
    Integer queryCount(@Param("dto") OrderDto orderDto);

    /**
     * 导出分页查询订单
     * @param orderPage
     * @param orderDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> orderPage, @Param("dto") OrderDto orderDto);

    /**
     *
     * 查询所有订单
     * @return
     */
    List<Order> queryAll(@Param("dto") OrderDto orderDto);

}
