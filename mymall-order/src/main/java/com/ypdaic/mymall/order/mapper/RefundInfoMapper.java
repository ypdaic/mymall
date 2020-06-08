package com.ypdaic.mymall.order.mapper;

import com.ypdaic.mymall.order.entity.RefundInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.order.vo.RefundInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 退款信息 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface RefundInfoMapper extends BaseMapper<RefundInfo> {

    /**
     * 分页查询退款信息
     * @param refundInfoPage
     * @param refundInfoDto
     * @return
     */
    IPage<RefundInfo> queryPage(Page<RefundInfo> refundInfoPage, @Param("dto") RefundInfoDto refundInfoDto);

    /**
     * 导出查询数量
     * @param refundInfoDto
     * @return
     */
    Integer queryCount(@Param("dto") RefundInfoDto refundInfoDto);

    /**
     * 导出分页查询退款信息
     * @param refundInfoPage
     * @param refundInfoDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> refundInfoPage, @Param("dto") RefundInfoDto refundInfoDto);

    /**
     *
     * 查询所有退款信息
     * @return
     */
    List<RefundInfo> queryAll(@Param("dto") RefundInfoDto refundInfoDto);

}
