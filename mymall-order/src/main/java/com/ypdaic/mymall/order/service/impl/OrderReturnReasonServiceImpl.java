package com.ypdaic.mymall.order.service.impl;

import com.ypdaic.mymall.order.entity.OrderReturnReason;
import com.ypdaic.mymall.order.mapper.OrderReturnReasonMapper;
import com.ypdaic.mymall.order.service.IOrderReturnReasonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.order.vo.OrderReturnReasonDto;
import com.ypdaic.mymall.order.enums.OrderReturnReasonExcelHeadersEnum;
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
 * 退货原因 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonMapper, OrderReturnReason> implements IOrderReturnReasonService {


    /**
     * 新增退货原因
     * @param orderReturnReasonDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderReturnReason add(OrderReturnReasonDto orderReturnReasonDto) {

        OrderReturnReason orderReturnReason = new OrderReturnReason();
        orderReturnReason.setName(orderReturnReasonDto.getName());
        orderReturnReason.setSort(orderReturnReasonDto.getSort());
        orderReturnReason.setStatus(orderReturnReasonDto.getStatus());
        Date date3= new Date();
        orderReturnReason.setCreateTime(date3);
        orderReturnReason.insert();
        return orderReturnReason;
    }

    /**
     * 更新退货原因
     * @param orderReturnReasonDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderReturnReason update(OrderReturnReasonDto orderReturnReasonDto) {
        OrderReturnReason orderReturnReason = baseMapper.selectById(orderReturnReasonDto.getId());
        if (orderReturnReason == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnReasonDto.getName(), orderReturnReason::setName);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnReasonDto.getSort(), orderReturnReason::setSort);
        JavaUtils.INSTANCE.acceptIfNotNull(orderReturnReasonDto.getStatus(), orderReturnReason::setStatus);
        orderReturnReason.updateById();
        return orderReturnReason;
    }

    /**
     * 删除退货原因
     * @param orderReturnReasonDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderReturnReason delete(OrderReturnReasonDto orderReturnReasonDto) {
        OrderReturnReason orderReturnReason = baseMapper.selectById(orderReturnReasonDto.getId());
        if (orderReturnReason == null) {
            return null;
        }
        orderReturnReason.deleteById();

        return orderReturnReason;
    }

    /**
     * 分页查询退货原因
     * @param orderReturnReasonDto
     * @param orderReturnReasonPage
     * @return
     */
    @Override
    public IPage<OrderReturnReason> queryPage(OrderReturnReasonDto orderReturnReasonDto, Page<OrderReturnReason> orderReturnReasonPage) {

        return baseMapper.queryPage(orderReturnReasonPage, orderReturnReasonDto);
    }


    /**
     * 校验退货原因名称
     * @param orderReturnReasonDto
     * @return
     */
    @Override
    public boolean checkName(OrderReturnReasonDto orderReturnReasonDto, boolean isAdd) {

        QueryWrapper<OrderReturnReason> orderReturnReasonQueryWrapper = new QueryWrapper<OrderReturnReason>();
        orderReturnReasonQueryWrapper.eq("name", orderReturnReasonDto.getName());
        orderReturnReasonQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        orderReturnReasonQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            orderReturnReasonQueryWrapper.ne("id", orderReturnReasonDto.getId());
        }

        Integer count = baseMapper.selectCount(orderReturnReasonQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有退货原因
     * @return
     */
    public List<OrderReturnReason> queryAll(OrderReturnReasonDto orderReturnReasonDto) {
        return baseMapper.queryAll(orderReturnReasonDto);
    }

}
