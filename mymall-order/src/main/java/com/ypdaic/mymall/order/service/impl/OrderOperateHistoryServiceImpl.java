package com.ypdaic.mymall.order.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.order.entity.OrderItem;
import com.ypdaic.mymall.order.entity.OrderOperateHistory;
import com.ypdaic.mymall.order.mapper.OrderOperateHistoryMapper;
import com.ypdaic.mymall.order.service.IOrderOperateHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.order.vo.OrderOperateHistoryDto;
import com.ypdaic.mymall.order.enums.OrderOperateHistoryExcelHeadersEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypdaic.mymall.common.util.ExcelUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.util.JavaUtils;
import java.util.Date;

/**
 * <p>
 * 订单操作历史记录 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryMapper, OrderOperateHistory> implements IOrderOperateHistoryService {


    /**
     * 新增订单操作历史记录
     * @param orderOperateHistoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderOperateHistory add(OrderOperateHistoryDto orderOperateHistoryDto) {

        OrderOperateHistory orderOperateHistory = new OrderOperateHistory();
        orderOperateHistory.setOrderId(orderOperateHistoryDto.getOrderId());
        orderOperateHistory.setOperateMan(orderOperateHistoryDto.getOperateMan());
        Date date2= new Date();
        orderOperateHistory.setCreateTime(date2);
        orderOperateHistory.setOrderStatus(orderOperateHistoryDto.getOrderStatus());
        orderOperateHistory.setNote(orderOperateHistoryDto.getNote());
        orderOperateHistory.insert();
        return orderOperateHistory;
    }

    /**
     * 更新订单操作历史记录
     * @param orderOperateHistoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderOperateHistory update(OrderOperateHistoryDto orderOperateHistoryDto) {
        OrderOperateHistory orderOperateHistory = baseMapper.selectById(orderOperateHistoryDto.getId());
        if (orderOperateHistory == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(orderOperateHistoryDto.getOrderId(), orderOperateHistory::setOrderId);
        JavaUtils.INSTANCE.acceptIfNotNull(orderOperateHistoryDto.getOperateMan(), orderOperateHistory::setOperateMan);
        JavaUtils.INSTANCE.acceptIfNotNull(orderOperateHistoryDto.getOrderStatus(), orderOperateHistory::setOrderStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(orderOperateHistoryDto.getNote(), orderOperateHistory::setNote);
        orderOperateHistory.updateById();
        return orderOperateHistory;
    }

    /**
     * 删除订单操作历史记录
     * @param orderOperateHistoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderOperateHistory delete(OrderOperateHistoryDto orderOperateHistoryDto) {
        OrderOperateHistory orderOperateHistory = baseMapper.selectById(orderOperateHistoryDto.getId());
        if (orderOperateHistory == null) {
            return null;
        }
        orderOperateHistory.deleteById();

        return orderOperateHistory;
    }

    /**
     * 分页查询订单操作历史记录
     * @param orderOperateHistoryDto
     * @param orderOperateHistoryPage
     * @return
     */
    @Override
    public IPage<OrderOperateHistory> queryPage(OrderOperateHistoryDto orderOperateHistoryDto, Page<OrderOperateHistory> orderOperateHistoryPage) {

        return baseMapper.queryPage(orderOperateHistoryPage, orderOperateHistoryDto);
    }


    /**
     * 校验订单操作历史记录名称
     * @param orderOperateHistoryDto
     * @return
     */
    @Override
    public boolean checkName(OrderOperateHistoryDto orderOperateHistoryDto, boolean isAdd) {

        QueryWrapper<OrderOperateHistory> orderOperateHistoryQueryWrapper = new QueryWrapper<OrderOperateHistory>();
        orderOperateHistoryQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        orderOperateHistoryQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            orderOperateHistoryQueryWrapper.ne("id", orderOperateHistoryDto.getId());
        }

        Integer count = baseMapper.selectCount(orderOperateHistoryQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有订单操作历史记录
     * @return
     */
    public List<OrderOperateHistory> queryAll(OrderOperateHistoryDto orderOperateHistoryDto) {
        return baseMapper.queryAll(orderOperateHistoryDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderOperateHistory> page = this.page(
                new Query<OrderOperateHistory>().getPage(params),
                new QueryWrapper<OrderOperateHistory>()
        );

        return new PageUtils(page);
    }

}
