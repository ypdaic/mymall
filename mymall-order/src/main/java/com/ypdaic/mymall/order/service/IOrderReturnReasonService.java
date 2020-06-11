package com.ypdaic.mymall.order.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.order.entity.OrderReturnReason;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.order.vo.OrderReturnReasonDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 退货原因 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IOrderReturnReasonService extends IService<OrderReturnReason> {

    /**
     * 新增退货原因
     * @param orderReturnReasonDto
     * @return
     */
    OrderReturnReason add(OrderReturnReasonDto orderReturnReasonDto);

    /**
     * 更新退货原因
     * @param orderReturnReasonDto
     * @return
     */
    OrderReturnReason update(OrderReturnReasonDto orderReturnReasonDto);

    /**
     * 删除退货原因
     * @param orderReturnReasonDto
     * @return
     */
    OrderReturnReason delete(OrderReturnReasonDto orderReturnReasonDto);

    /**
     * 分页查询退货原因
     * @param orderReturnReasonDto
     * @param orderReturnReasonPage
     * @return
     */
    IPage<OrderReturnReason> queryPage(OrderReturnReasonDto orderReturnReasonDto, Page<OrderReturnReason> orderReturnReasonPage);


    /**
     * 校验退货原因名称
     * @param orderReturnReasonDto
     * @return
     */
    boolean checkName(OrderReturnReasonDto orderReturnReasonDto, boolean isAdd);

    /**
     *
     * 查询所有退货原因
     * @return
     */
    List<OrderReturnReason> queryAll(OrderReturnReasonDto orderReturnReasonDto);

    PageUtils queryPage(Map<String, Object> params);
}
