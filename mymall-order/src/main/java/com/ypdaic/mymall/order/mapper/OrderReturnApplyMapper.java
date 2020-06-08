package com.ypdaic.mymall.order.mapper;

import com.ypdaic.mymall.order.entity.OrderReturnApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.order.vo.OrderReturnApplyDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 订单退货申请 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface OrderReturnApplyMapper extends BaseMapper<OrderReturnApply> {

    /**
     * 分页查询订单退货申请
     * @param orderReturnApplyPage
     * @param orderReturnApplyDto
     * @return
     */
    IPage<OrderReturnApply> queryPage(Page<OrderReturnApply> orderReturnApplyPage, @Param("dto") OrderReturnApplyDto orderReturnApplyDto);

    /**
     * 导出查询数量
     * @param orderReturnApplyDto
     * @return
     */
    Integer queryCount(@Param("dto") OrderReturnApplyDto orderReturnApplyDto);

    /**
     * 导出分页查询订单退货申请
     * @param orderReturnApplyPage
     * @param orderReturnApplyDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> orderReturnApplyPage, @Param("dto") OrderReturnApplyDto orderReturnApplyDto);

    /**
     *
     * 查询所有订单退货申请
     * @return
     */
    List<OrderReturnApply> queryAll(@Param("dto") OrderReturnApplyDto orderReturnApplyDto);

}
