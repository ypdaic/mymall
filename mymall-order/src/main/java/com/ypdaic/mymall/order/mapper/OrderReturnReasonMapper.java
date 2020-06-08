package com.ypdaic.mymall.order.mapper;

import com.ypdaic.mymall.order.entity.OrderReturnReason;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.order.vo.OrderReturnReasonDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 退货原因 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface OrderReturnReasonMapper extends BaseMapper<OrderReturnReason> {

    /**
     * 分页查询退货原因
     * @param orderReturnReasonPage
     * @param orderReturnReasonDto
     * @return
     */
    IPage<OrderReturnReason> queryPage(Page<OrderReturnReason> orderReturnReasonPage, @Param("dto") OrderReturnReasonDto orderReturnReasonDto);

    /**
     * 导出查询数量
     * @param orderReturnReasonDto
     * @return
     */
    Integer queryCount(@Param("dto") OrderReturnReasonDto orderReturnReasonDto);

    /**
     * 导出分页查询退货原因
     * @param orderReturnReasonPage
     * @param orderReturnReasonDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> orderReturnReasonPage, @Param("dto") OrderReturnReasonDto orderReturnReasonDto);

    /**
     *
     * 查询所有退货原因
     * @return
     */
    List<OrderReturnReason> queryAll(@Param("dto") OrderReturnReasonDto orderReturnReasonDto);

}
