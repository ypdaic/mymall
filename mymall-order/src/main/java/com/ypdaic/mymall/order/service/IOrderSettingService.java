package com.ypdaic.mymall.order.service;

import com.ypdaic.mymall.order.entity.OrderSetting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.order.vo.OrderSettingDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 订单配置信息 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IOrderSettingService extends IService<OrderSetting> {

    /**
     * 新增订单配置信息
     * @param orderSettingDto
     * @return
     */
    OrderSetting add(OrderSettingDto orderSettingDto);

    /**
     * 更新订单配置信息
     * @param orderSettingDto
     * @return
     */
    OrderSetting update(OrderSettingDto orderSettingDto);

    /**
     * 删除订单配置信息
     * @param orderSettingDto
     * @return
     */
    OrderSetting delete(OrderSettingDto orderSettingDto);

    /**
     * 分页查询订单配置信息
     * @param orderSettingDto
     * @param orderSettingPage
     * @return
     */
    IPage<OrderSetting> queryPage(OrderSettingDto orderSettingDto, Page<OrderSetting> orderSettingPage);


    /**
     * 校验订单配置信息名称
     * @param orderSettingDto
     * @return
     */
    boolean checkName(OrderSettingDto orderSettingDto, boolean isAdd);

    /**
     *
     * 查询所有订单配置信息
     * @return
     */
    List<OrderSetting> queryAll(OrderSettingDto orderSettingDto);
}
