package com.ypdaic.mymall.member.mapper;

import com.ypdaic.mymall.member.entity.GrowthChangeHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.member.vo.GrowthChangeHistoryDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 成长值变化历史记录 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface GrowthChangeHistoryMapper extends BaseMapper<GrowthChangeHistory> {

    /**
     * 分页查询成长值变化历史记录
     * @param growthChangeHistoryPage
     * @param growthChangeHistoryDto
     * @return
     */
    IPage<GrowthChangeHistory> queryPage(Page<GrowthChangeHistory> growthChangeHistoryPage, @Param("dto") GrowthChangeHistoryDto growthChangeHistoryDto);

    /**
     * 导出查询数量
     * @param growthChangeHistoryDto
     * @return
     */
    Integer queryCount(@Param("dto") GrowthChangeHistoryDto growthChangeHistoryDto);

    /**
     * 导出分页查询成长值变化历史记录
     * @param growthChangeHistoryPage
     * @param growthChangeHistoryDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> growthChangeHistoryPage, @Param("dto") GrowthChangeHistoryDto growthChangeHistoryDto);

    /**
     *
     * 查询所有成长值变化历史记录
     * @return
     */
    List<GrowthChangeHistory> queryAll(@Param("dto") GrowthChangeHistoryDto growthChangeHistoryDto);

}
