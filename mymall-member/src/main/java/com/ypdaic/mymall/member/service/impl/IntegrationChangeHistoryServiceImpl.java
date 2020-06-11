package com.ypdaic.mymall.member.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.member.entity.GrowthChangeHistory;
import com.ypdaic.mymall.member.entity.IntegrationChangeHistory;
import com.ypdaic.mymall.member.mapper.IntegrationChangeHistoryMapper;
import com.ypdaic.mymall.member.service.IIntegrationChangeHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.member.vo.IntegrationChangeHistoryDto;
import com.ypdaic.mymall.member.enums.IntegrationChangeHistoryExcelHeadersEnum;
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
import java.util.Date;

/**
 * <p>
 * 积分变化历史记录 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class IntegrationChangeHistoryServiceImpl extends ServiceImpl<IntegrationChangeHistoryMapper, IntegrationChangeHistory> implements IIntegrationChangeHistoryService {


    /**
     * 新增积分变化历史记录
     * @param integrationChangeHistoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public IntegrationChangeHistory add(IntegrationChangeHistoryDto integrationChangeHistoryDto) {

        IntegrationChangeHistory integrationChangeHistory = new IntegrationChangeHistory();
        integrationChangeHistory.setMemberId(integrationChangeHistoryDto.getMemberId());
        Date date1= new Date();
        integrationChangeHistory.setCreateTime(date1);
        integrationChangeHistory.setChangeCount(integrationChangeHistoryDto.getChangeCount());
        integrationChangeHistory.setNote(integrationChangeHistoryDto.getNote());
        integrationChangeHistory.setSourceTyoe(integrationChangeHistoryDto.getSourceTyoe());
        integrationChangeHistory.insert();
        return integrationChangeHistory;
    }

    /**
     * 更新积分变化历史记录
     * @param integrationChangeHistoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public IntegrationChangeHistory update(IntegrationChangeHistoryDto integrationChangeHistoryDto) {
        IntegrationChangeHistory integrationChangeHistory = baseMapper.selectById(integrationChangeHistoryDto.getId());
        if (integrationChangeHistory == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(integrationChangeHistoryDto.getMemberId(), integrationChangeHistory::setMemberId);
        JavaUtils.INSTANCE.acceptIfNotNull(integrationChangeHistoryDto.getChangeCount(), integrationChangeHistory::setChangeCount);
        JavaUtils.INSTANCE.acceptIfNotNull(integrationChangeHistoryDto.getNote(), integrationChangeHistory::setNote);
        JavaUtils.INSTANCE.acceptIfNotNull(integrationChangeHistoryDto.getSourceTyoe(), integrationChangeHistory::setSourceTyoe);
        integrationChangeHistory.updateById();
        return integrationChangeHistory;
    }

    /**
     * 删除积分变化历史记录
     * @param integrationChangeHistoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public IntegrationChangeHistory delete(IntegrationChangeHistoryDto integrationChangeHistoryDto) {
        IntegrationChangeHistory integrationChangeHistory = baseMapper.selectById(integrationChangeHistoryDto.getId());
        if (integrationChangeHistory == null) {
            return null;
        }
        integrationChangeHistory.deleteById();

        return integrationChangeHistory;
    }

    /**
     * 分页查询积分变化历史记录
     * @param integrationChangeHistoryDto
     * @param integrationChangeHistoryPage
     * @return
     */
    @Override
    public IPage<IntegrationChangeHistory> queryPage(IntegrationChangeHistoryDto integrationChangeHistoryDto, Page<IntegrationChangeHistory> integrationChangeHistoryPage) {

        return baseMapper.queryPage(integrationChangeHistoryPage, integrationChangeHistoryDto);
    }


    /**
     * 校验积分变化历史记录名称
     * @param integrationChangeHistoryDto
     * @return
     */
    @Override
    public boolean checkName(IntegrationChangeHistoryDto integrationChangeHistoryDto, boolean isAdd) {

        QueryWrapper<IntegrationChangeHistory> integrationChangeHistoryQueryWrapper = new QueryWrapper<IntegrationChangeHistory>();
        integrationChangeHistoryQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        integrationChangeHistoryQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            integrationChangeHistoryQueryWrapper.ne("id", integrationChangeHistoryDto.getId());
        }

        Integer count = baseMapper.selectCount(integrationChangeHistoryQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有积分变化历史记录
     * @return
     */
    public List<IntegrationChangeHistory> queryAll(IntegrationChangeHistoryDto integrationChangeHistoryDto) {
        return baseMapper.queryAll(integrationChangeHistoryDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<IntegrationChangeHistory> page = this.page(
                new Query<IntegrationChangeHistory>().getPage(params),
                new QueryWrapper<IntegrationChangeHistory>()
        );

        return new PageUtils(page);
    }

}
