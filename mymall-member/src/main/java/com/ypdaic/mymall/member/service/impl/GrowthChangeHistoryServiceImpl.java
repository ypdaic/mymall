package com.ypdaic.mymall.member.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.member.entity.GrowthChangeHistory;
import com.ypdaic.mymall.member.mapper.GrowthChangeHistoryMapper;
import com.ypdaic.mymall.member.service.IGrowthChangeHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.member.vo.GrowthChangeHistoryDto;
import com.ypdaic.mymall.member.enums.GrowthChangeHistoryExcelHeadersEnum;
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
 * 成长值变化历史记录 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class GrowthChangeHistoryServiceImpl extends ServiceImpl<GrowthChangeHistoryMapper, GrowthChangeHistory> implements IGrowthChangeHistoryService {


    /**
     * 新增成长值变化历史记录
     * @param growthChangeHistoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public GrowthChangeHistory add(GrowthChangeHistoryDto growthChangeHistoryDto) {

        GrowthChangeHistory growthChangeHistory = new GrowthChangeHistory();
        growthChangeHistory.setMemberId(growthChangeHistoryDto.getMemberId());
        Date date1= new Date();
        growthChangeHistory.setCreateTime(date1);
        growthChangeHistory.setChangeCount(growthChangeHistoryDto.getChangeCount());
        growthChangeHistory.setNote(growthChangeHistoryDto.getNote());
        growthChangeHistory.setSourceType(growthChangeHistoryDto.getSourceType());
        growthChangeHistory.insert();
        return growthChangeHistory;
    }

    /**
     * 更新成长值变化历史记录
     * @param growthChangeHistoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public GrowthChangeHistory update(GrowthChangeHistoryDto growthChangeHistoryDto) {
        GrowthChangeHistory growthChangeHistory = baseMapper.selectById(growthChangeHistoryDto.getId());
        if (growthChangeHistory == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(growthChangeHistoryDto.getMemberId(), growthChangeHistory::setMemberId);
        JavaUtils.INSTANCE.acceptIfNotNull(growthChangeHistoryDto.getChangeCount(), growthChangeHistory::setChangeCount);
        JavaUtils.INSTANCE.acceptIfNotNull(growthChangeHistoryDto.getNote(), growthChangeHistory::setNote);
        JavaUtils.INSTANCE.acceptIfNotNull(growthChangeHistoryDto.getSourceType(), growthChangeHistory::setSourceType);
        growthChangeHistory.updateById();
        return growthChangeHistory;
    }

    /**
     * 删除成长值变化历史记录
     * @param growthChangeHistoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public GrowthChangeHistory delete(GrowthChangeHistoryDto growthChangeHistoryDto) {
        GrowthChangeHistory growthChangeHistory = baseMapper.selectById(growthChangeHistoryDto.getId());
        if (growthChangeHistory == null) {
            return null;
        }
        growthChangeHistory.deleteById();

        return growthChangeHistory;
    }

    /**
     * 分页查询成长值变化历史记录
     * @param growthChangeHistoryDto
     * @param growthChangeHistoryPage
     * @return
     */
    @Override
    public IPage<GrowthChangeHistory> queryPage(GrowthChangeHistoryDto growthChangeHistoryDto, Page<GrowthChangeHistory> growthChangeHistoryPage) {

        return baseMapper.queryPage(growthChangeHistoryPage, growthChangeHistoryDto);
    }


    /**
     * 校验成长值变化历史记录名称
     * @param growthChangeHistoryDto
     * @return
     */
    @Override
    public boolean checkName(GrowthChangeHistoryDto growthChangeHistoryDto, boolean isAdd) {

        QueryWrapper<GrowthChangeHistory> growthChangeHistoryQueryWrapper = new QueryWrapper<GrowthChangeHistory>();
        growthChangeHistoryQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        growthChangeHistoryQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            growthChangeHistoryQueryWrapper.ne("id", growthChangeHistoryDto.getId());
        }

        Integer count = baseMapper.selectCount(growthChangeHistoryQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有成长值变化历史记录
     * @return
     */
    public List<GrowthChangeHistory> queryAll(GrowthChangeHistoryDto growthChangeHistoryDto) {
        return baseMapper.queryAll(growthChangeHistoryDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrowthChangeHistory> page = this.page(
                new Query<GrowthChangeHistory>().getPage(params),
                new QueryWrapper<GrowthChangeHistory>()
        );

        return new PageUtils(page);
    }

}
