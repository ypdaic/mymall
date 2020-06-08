package com.ypdaic.mymall.member.service;

import com.ypdaic.mymall.member.entity.GrowthChangeHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.member.vo.GrowthChangeHistoryDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 成长值变化历史记录 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IGrowthChangeHistoryService extends IService<GrowthChangeHistory> {

    /**
     * 新增成长值变化历史记录
     * @param growthChangeHistoryDto
     * @return
     */
    GrowthChangeHistory add(GrowthChangeHistoryDto growthChangeHistoryDto);

    /**
     * 更新成长值变化历史记录
     * @param growthChangeHistoryDto
     * @return
     */
    GrowthChangeHistory update(GrowthChangeHistoryDto growthChangeHistoryDto);

    /**
     * 删除成长值变化历史记录
     * @param growthChangeHistoryDto
     * @return
     */
    GrowthChangeHistory delete(GrowthChangeHistoryDto growthChangeHistoryDto);

    /**
     * 分页查询成长值变化历史记录
     * @param growthChangeHistoryDto
     * @param growthChangeHistoryPage
     * @return
     */
    IPage<GrowthChangeHistory> queryPage(GrowthChangeHistoryDto growthChangeHistoryDto, Page<GrowthChangeHistory> growthChangeHistoryPage);


    /**
     * 校验成长值变化历史记录名称
     * @param growthChangeHistoryDto
     * @return
     */
    boolean checkName(GrowthChangeHistoryDto growthChangeHistoryDto, boolean isAdd);

    /**
     *
     * 查询所有成长值变化历史记录
     * @return
     */
    List<GrowthChangeHistory> queryAll(GrowthChangeHistoryDto growthChangeHistoryDto);
}
