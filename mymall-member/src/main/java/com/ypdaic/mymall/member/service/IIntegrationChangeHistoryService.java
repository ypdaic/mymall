package com.ypdaic.mymall.member.service;

import com.ypdaic.mymall.member.entity.IntegrationChangeHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.member.vo.IntegrationChangeHistoryDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 积分变化历史记录 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IIntegrationChangeHistoryService extends IService<IntegrationChangeHistory> {

    /**
     * 新增积分变化历史记录
     * @param integrationChangeHistoryDto
     * @return
     */
    IntegrationChangeHistory add(IntegrationChangeHistoryDto integrationChangeHistoryDto);

    /**
     * 更新积分变化历史记录
     * @param integrationChangeHistoryDto
     * @return
     */
    IntegrationChangeHistory update(IntegrationChangeHistoryDto integrationChangeHistoryDto);

    /**
     * 删除积分变化历史记录
     * @param integrationChangeHistoryDto
     * @return
     */
    IntegrationChangeHistory delete(IntegrationChangeHistoryDto integrationChangeHistoryDto);

    /**
     * 分页查询积分变化历史记录
     * @param integrationChangeHistoryDto
     * @param integrationChangeHistoryPage
     * @return
     */
    IPage<IntegrationChangeHistory> queryPage(IntegrationChangeHistoryDto integrationChangeHistoryDto, Page<IntegrationChangeHistory> integrationChangeHistoryPage);


    /**
     * 校验积分变化历史记录名称
     * @param integrationChangeHistoryDto
     * @return
     */
    boolean checkName(IntegrationChangeHistoryDto integrationChangeHistoryDto, boolean isAdd);

    /**
     *
     * 查询所有积分变化历史记录
     * @return
     */
    List<IntegrationChangeHistory> queryAll(IntegrationChangeHistoryDto integrationChangeHistoryDto);
}
