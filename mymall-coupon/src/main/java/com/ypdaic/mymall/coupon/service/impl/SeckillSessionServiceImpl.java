package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.coupon.entity.SeckillSession;
import com.ypdaic.mymall.coupon.entity.SeckillSkuRelation;
import com.ypdaic.mymall.coupon.mapper.SeckillSessionMapper;
import com.ypdaic.mymall.coupon.service.ISeckillSessionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.service.ISeckillSkuRelationService;
import com.ypdaic.mymall.coupon.vo.SeckillSessionDto;
import com.ypdaic.mymall.coupon.enums.SeckillSessionExcelHeadersEnum;
import com.ypdaic.mymall.coupon.vo.SeckillSkuRelationDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypdaic.mymall.common.util.ExcelUtil;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.util.JavaUtils;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * <p>
 * 秒杀活动场次 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionMapper, SeckillSession> implements ISeckillSessionService {

    @Autowired
    ISeckillSkuRelationService seckillSkuRelationService;

    /**
     * 新增秒杀活动场次
     * @param seckillSessionDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SeckillSession add(SeckillSessionDto seckillSessionDto) {

        SeckillSession seckillSession = new SeckillSession();
        seckillSession.setName(seckillSessionDto.getName());
        Date date1= new Date();
        seckillSession.setStartTime(date1);
        Date date2= new Date();
        seckillSession.setEndTime(date2);
        seckillSession.setStatus(seckillSessionDto.getStatus());
        Date date4= new Date();
        seckillSession.setCreateTime(date4);
        seckillSession.insert();
        return seckillSession;
    }

    /**
     * 更新秒杀活动场次
     * @param seckillSessionDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SeckillSession update(SeckillSessionDto seckillSessionDto) {
        SeckillSession seckillSession = baseMapper.selectById(seckillSessionDto.getId());
        if (seckillSession == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(seckillSessionDto.getName(), seckillSession::setName);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSessionDto.getStartTime(), seckillSession::setStartTime);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSessionDto.getEndTime(), seckillSession::setEndTime);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSessionDto.getStatus(), seckillSession::setStatus);
        seckillSession.updateById();
        return seckillSession;
    }

    /**
     * 删除秒杀活动场次
     * @param seckillSessionDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SeckillSession delete(SeckillSessionDto seckillSessionDto) {
        SeckillSession seckillSession = baseMapper.selectById(seckillSessionDto.getId());
        if (seckillSession == null) {
            return null;
        }
        seckillSession.deleteById();

        return seckillSession;
    }

    /**
     * 分页查询秒杀活动场次
     * @param seckillSessionDto
     * @param seckillSessionPage
     * @return
     */
    @Override
    public IPage<SeckillSession> queryPage(SeckillSessionDto seckillSessionDto, Page<SeckillSession> seckillSessionPage) {

        return baseMapper.queryPage(seckillSessionPage, seckillSessionDto);
    }


    /**
     * 校验秒杀活动场次名称
     * @param seckillSessionDto
     * @return
     */
    @Override
    public boolean checkName(SeckillSessionDto seckillSessionDto, boolean isAdd) {

        QueryWrapper<SeckillSession> seckillSessionQueryWrapper = new QueryWrapper<SeckillSession>();
        seckillSessionQueryWrapper.eq("name", seckillSessionDto.getName());
        seckillSessionQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        seckillSessionQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            seckillSessionQueryWrapper.ne("id", seckillSessionDto.getId());
        }

        Integer count = baseMapper.selectCount(seckillSessionQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有秒杀活动场次
     * @return
     */
    public List<SeckillSession> queryAll(SeckillSessionDto seckillSessionDto) {
        return baseMapper.queryAll(seckillSessionDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillSession> page = this.page(
                new Query<SeckillSession>().getPage(params),
                new QueryWrapper<SeckillSession>()
        );

        return new PageUtils(page);
    }

    /**
     * 查询3天内的秒杀
     * @return
     */
    @Override
    public List<SeckillSession> getLates3DaySession() {
        LocalDate now = LocalDate.now();
        LocalDate plus = now.plusDays(2);

        LocalTime min = LocalTime.MIN;
        LocalTime max = LocalTime.MAX;

        LocalDateTime of = LocalDateTime.of(now, min);

        LocalDateTime of1 = LocalDateTime.of(plus, max);
        ZoneId zoneId = ZoneId.systemDefault();

        ZonedDateTime zdt = of.atZone(zoneId);
        ZonedDateTime zonedDateTime = of1.atZone(zoneId);

        SeckillSessionDto seckillSessionDto = new SeckillSessionDto();
        seckillSessionDto.setStartTime(Date.from(zdt.toInstant()));
        seckillSessionDto.setEndTime(Date.from(zonedDateTime.toInstant()));
        List<SeckillSession> seckillSessions = queryAll(seckillSessionDto);

        if (CollectionUtils.isNotEmpty(seckillSessions)) {
            List<SeckillSession> collect = seckillSessions.stream().map(seckillSession -> {
                Long id = seckillSession.getId();
                SeckillSkuRelationDto seckillSkuRelationDto = new SeckillSkuRelationDto();
                seckillSkuRelationDto.setPromotionSessionId(id);
                List<SeckillSkuRelation> seckillSkuRelations = seckillSkuRelationService.queryAll(seckillSkuRelationDto);
                seckillSession.setList(seckillSkuRelations);
                return seckillSession;
            }).collect(Collectors.toList());

            return collect;
        }

        return null;
    }

}
