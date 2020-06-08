package com.ypdaic.mymall.member.mapper;

import com.ypdaic.mymall.member.entity.IntegrationChangeHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.member.vo.IntegrationChangeHistoryDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 积分变化历史记录 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IntegrationChangeHistoryMapper extends BaseMapper<IntegrationChangeHistory> {

    /**
     * 分页查询积分变化历史记录
     * @param integrationChangeHistoryPage
     * @param integrationChangeHistoryDto
     * @return
     */
    IPage<IntegrationChangeHistory> queryPage(Page<IntegrationChangeHistory> integrationChangeHistoryPage, @Param("dto") IntegrationChangeHistoryDto integrationChangeHistoryDto);

    /**
     * 导出查询数量
     * @param integrationChangeHistoryDto
     * @return
     */
    Integer queryCount(@Param("dto") IntegrationChangeHistoryDto integrationChangeHistoryDto);

    /**
     * 导出分页查询积分变化历史记录
     * @param integrationChangeHistoryPage
     * @param integrationChangeHistoryDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> integrationChangeHistoryPage, @Param("dto") IntegrationChangeHistoryDto integrationChangeHistoryDto);

    /**
     *
     * 查询所有积分变化历史记录
     * @return
     */
    List<IntegrationChangeHistory> queryAll(@Param("dto") IntegrationChangeHistoryDto integrationChangeHistoryDto);

}
