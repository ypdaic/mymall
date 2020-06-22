package com.ypdaic.mymall.order.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.order.vo.OrderConfirmVo;
import com.ypdaic.mymall.order.vo.OrderDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IOrderService extends IService<Order> {

    /**
     * 新增订单
     * @param orderDto
     * @return
     */
    Order add(OrderDto orderDto);

    /**
     * 更新订单
     * @param orderDto
     * @return
     */
    Order update(OrderDto orderDto);

    /**
     * 删除订单
     * @param orderDto
     * @return
     */
    Order delete(OrderDto orderDto);

    /**
     * 分页查询订单
     * @param orderDto
     * @param orderPage
     * @return
     */
    IPage<Order> queryPage(OrderDto orderDto, Page<Order> orderPage);


    /**
     * 校验订单名称
     * @param orderDto
     * @return
     */
    boolean checkName(OrderDto orderDto, boolean isAdd);

    /**
     *
     * 查询所有订单
     * @return
     */
    List<Order> queryAll(OrderDto orderDto);

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 订单确认页返回的数据
     * @return
     */
    OrderConfirmVo confirmOrder();
}
