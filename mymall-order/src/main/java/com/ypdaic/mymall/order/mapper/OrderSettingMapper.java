package com.ypdaic.mymall.order.mapper;

import com.ypdaic.mymall.order.entity.OrderSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.order.vo.OrderSettingDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 订单配置信息 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface OrderSettingMapper extends BaseMapper<OrderSetting> {

    /**
     * 分页查询订单配置信息
     * @param orderSettingPage
     * @param orderSettingDto
     * @return
     */
    IPage<OrderSetting> queryPage(Page<OrderSetting> orderSettingPage, @Param("dto") OrderSettingDto orderSettingDto);

    /**
     * 导出查询数量
     * @param orderSettingDto
     * @return
     */
    Integer queryCount(@Param("dto") OrderSettingDto orderSettingDto);

    /**
     * 导出分页查询订单配置信息
     * @param orderSettingPage
     * @param orderSettingDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> orderSettingPage, @Param("dto") OrderSettingDto orderSettingDto);

    /**
     *
     * 查询所有订单配置信息
     * @return
     */
    List<OrderSetting> queryAll(@Param("dto") OrderSettingDto orderSettingDto);

}
