package com.ypdaic.mymall.order.mapper;

import com.ypdaic.mymall.order.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.order.vo.OrderItemDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 订单项信息 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    /**
     * 分页查询订单项信息
     * @param orderItemPage
     * @param orderItemDto
     * @return
     */
    IPage<OrderItem> queryPage(Page<OrderItem> orderItemPage, @Param("dto") OrderItemDto orderItemDto);

    /**
     * 导出查询数量
     * @param orderItemDto
     * @return
     */
    Integer queryCount(@Param("dto") OrderItemDto orderItemDto);

    /**
     * 导出分页查询订单项信息
     * @param orderItemPage
     * @param orderItemDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> orderItemPage, @Param("dto") OrderItemDto orderItemDto);

    /**
     *
     * 查询所有订单项信息
     * @return
     */
    List<OrderItem> queryAll(@Param("dto") OrderItemDto orderItemDto);

}
