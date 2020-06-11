package com.ypdaic.mymall.order.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.order.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.order.vo.OrderItemDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 订单项信息 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IOrderItemService extends IService<OrderItem> {

    /**
     * 新增订单项信息
     * @param orderItemDto
     * @return
     */
    OrderItem add(OrderItemDto orderItemDto);

    /**
     * 更新订单项信息
     * @param orderItemDto
     * @return
     */
    OrderItem update(OrderItemDto orderItemDto);

    /**
     * 删除订单项信息
     * @param orderItemDto
     * @return
     */
    OrderItem delete(OrderItemDto orderItemDto);

    /**
     * 分页查询订单项信息
     * @param orderItemDto
     * @param orderItemPage
     * @return
     */
    IPage<OrderItem> queryPage(OrderItemDto orderItemDto, Page<OrderItem> orderItemPage);


    /**
     * 校验订单项信息名称
     * @param orderItemDto
     * @return
     */
    boolean checkName(OrderItemDto orderItemDto, boolean isAdd);

    /**
     *
     * 查询所有订单项信息
     * @return
     */
    List<OrderItem> queryAll(OrderItemDto orderItemDto);

    PageUtils queryPage(Map<String, Object> params);
}
