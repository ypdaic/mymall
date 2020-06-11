package com.ypdaic.mymall.order.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.order.entity.OrderReturnApply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.order.vo.OrderReturnApplyDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 订单退货申请 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IOrderReturnApplyService extends IService<OrderReturnApply> {

    /**
     * 新增订单退货申请
     * @param orderReturnApplyDto
     * @return
     */
    OrderReturnApply add(OrderReturnApplyDto orderReturnApplyDto);

    /**
     * 更新订单退货申请
     * @param orderReturnApplyDto
     * @return
     */
    OrderReturnApply update(OrderReturnApplyDto orderReturnApplyDto);

    /**
     * 删除订单退货申请
     * @param orderReturnApplyDto
     * @return
     */
    OrderReturnApply delete(OrderReturnApplyDto orderReturnApplyDto);

    /**
     * 分页查询订单退货申请
     * @param orderReturnApplyDto
     * @param orderReturnApplyPage
     * @return
     */
    IPage<OrderReturnApply> queryPage(OrderReturnApplyDto orderReturnApplyDto, Page<OrderReturnApply> orderReturnApplyPage);


    /**
     * 校验订单退货申请名称
     * @param orderReturnApplyDto
     * @return
     */
    boolean checkName(OrderReturnApplyDto orderReturnApplyDto, boolean isAdd);

    /**
     *
     * 查询所有订单退货申请
     * @return
     */
    List<OrderReturnApply> queryAll(OrderReturnApplyDto orderReturnApplyDto);
    PageUtils queryPage(Map<String, Object> params);
}
