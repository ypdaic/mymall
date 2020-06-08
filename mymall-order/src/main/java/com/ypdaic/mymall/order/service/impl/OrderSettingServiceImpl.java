package com.ypdaic.mymall.order.service.impl;

import com.ypdaic.mymall.order.entity.OrderSetting;
import com.ypdaic.mymall.order.mapper.OrderSettingMapper;
import com.ypdaic.mymall.order.service.IOrderSettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.order.vo.OrderSettingDto;
import com.ypdaic.mymall.order.enums.OrderSettingExcelHeadersEnum;
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

/**
 * <p>
 * 订单配置信息 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting> implements IOrderSettingService {


    /**
     * 新增订单配置信息
     * @param orderSettingDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderSetting add(OrderSettingDto orderSettingDto) {

        OrderSetting orderSetting = new OrderSetting();
        orderSetting.setFlashOrderOvertime(orderSettingDto.getFlashOrderOvertime());
        orderSetting.setNormalOrderOvertime(orderSettingDto.getNormalOrderOvertime());
        orderSetting.setConfirmOvertime(orderSettingDto.getConfirmOvertime());
        orderSetting.setFinishOvertime(orderSettingDto.getFinishOvertime());
        orderSetting.setCommentOvertime(orderSettingDto.getCommentOvertime());
        orderSetting.setMemberLevel(orderSettingDto.getMemberLevel());
        orderSetting.insert();
        return orderSetting;
    }

    /**
     * 更新订单配置信息
     * @param orderSettingDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderSetting update(OrderSettingDto orderSettingDto) {
        OrderSetting orderSetting = baseMapper.selectById(orderSettingDto.getId());
        if (orderSetting == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(orderSettingDto.getFlashOrderOvertime(), orderSetting::setFlashOrderOvertime);
        JavaUtils.INSTANCE.acceptIfNotNull(orderSettingDto.getNormalOrderOvertime(), orderSetting::setNormalOrderOvertime);
        JavaUtils.INSTANCE.acceptIfNotNull(orderSettingDto.getConfirmOvertime(), orderSetting::setConfirmOvertime);
        JavaUtils.INSTANCE.acceptIfNotNull(orderSettingDto.getFinishOvertime(), orderSetting::setFinishOvertime);
        JavaUtils.INSTANCE.acceptIfNotNull(orderSettingDto.getCommentOvertime(), orderSetting::setCommentOvertime);
        JavaUtils.INSTANCE.acceptIfNotNull(orderSettingDto.getMemberLevel(), orderSetting::setMemberLevel);
        orderSetting.updateById();
        return orderSetting;
    }

    /**
     * 删除订单配置信息
     * @param orderSettingDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderSetting delete(OrderSettingDto orderSettingDto) {
        OrderSetting orderSetting = baseMapper.selectById(orderSettingDto.getId());
        if (orderSetting == null) {
            return null;
        }
        orderSetting.deleteById();

        return orderSetting;
    }

    /**
     * 分页查询订单配置信息
     * @param orderSettingDto
     * @param orderSettingPage
     * @return
     */
    @Override
    public IPage<OrderSetting> queryPage(OrderSettingDto orderSettingDto, Page<OrderSetting> orderSettingPage) {

        return baseMapper.queryPage(orderSettingPage, orderSettingDto);
    }


    /**
     * 校验订单配置信息名称
     * @param orderSettingDto
     * @return
     */
    @Override
    public boolean checkName(OrderSettingDto orderSettingDto, boolean isAdd) {

        QueryWrapper<OrderSetting> orderSettingQueryWrapper = new QueryWrapper<OrderSetting>();
        orderSettingQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        orderSettingQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            orderSettingQueryWrapper.ne("id", orderSettingDto.getId());
        }

        Integer count = baseMapper.selectCount(orderSettingQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有订单配置信息
     * @return
     */
    public List<OrderSetting> queryAll(OrderSettingDto orderSettingDto) {
        return baseMapper.queryAll(orderSettingDto);
    }

}
