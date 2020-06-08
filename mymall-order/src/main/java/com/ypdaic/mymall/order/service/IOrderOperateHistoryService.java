package com.ypdaic.mymall.order.service;

import com.ypdaic.mymall.order.entity.OrderOperateHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.order.vo.OrderOperateHistoryDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 订单操作历史记录 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IOrderOperateHistoryService extends IService<OrderOperateHistory> {

    /**
     * 新增订单操作历史记录
     * @param orderOperateHistoryDto
     * @return
     */
    OrderOperateHistory add(OrderOperateHistoryDto orderOperateHistoryDto);

    /**
     * 更新订单操作历史记录
     * @param orderOperateHistoryDto
     * @return
     */
    OrderOperateHistory update(OrderOperateHistoryDto orderOperateHistoryDto);

    /**
     * 删除订单操作历史记录
     * @param orderOperateHistoryDto
     * @return
     */
    OrderOperateHistory delete(OrderOperateHistoryDto orderOperateHistoryDto);

    /**
     * 分页查询订单操作历史记录
     * @param orderOperateHistoryDto
     * @param orderOperateHistoryPage
     * @return
     */
    IPage<OrderOperateHistory> queryPage(OrderOperateHistoryDto orderOperateHistoryDto, Page<OrderOperateHistory> orderOperateHistoryPage);


    /**
     * 校验订单操作历史记录名称
     * @param orderOperateHistoryDto
     * @return
     */
    boolean checkName(OrderOperateHistoryDto orderOperateHistoryDto, boolean isAdd);

    /**
     *
     * 查询所有订单操作历史记录
     * @return
     */
    List<OrderOperateHistory> queryAll(OrderOperateHistoryDto orderOperateHistoryDto);
}
