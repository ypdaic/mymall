package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.coupon.entity.SeckillPromotion;
import com.ypdaic.mymall.coupon.mapper.SeckillPromotionMapper;
import com.ypdaic.mymall.coupon.service.ISeckillPromotionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.SeckillPromotionDto;
import com.ypdaic.mymall.coupon.enums.SeckillPromotionExcelHeadersEnum;
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
import java.util.Date;
import java.util.Date;

/**
 * <p>
 * 秒杀活动 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class SeckillPromotionServiceImpl extends ServiceImpl<SeckillPromotionMapper, SeckillPromotion> implements ISeckillPromotionService {


    /**
     * 新增秒杀活动
     * @param seckillPromotionDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SeckillPromotion add(SeckillPromotionDto seckillPromotionDto) {

        SeckillPromotion seckillPromotion = new SeckillPromotion();
        seckillPromotion.setTitle(seckillPromotionDto.getTitle());
        Date date1= new Date();
        seckillPromotion.setStartTime(date1);
        Date date2= new Date();
        seckillPromotion.setEndTime(date2);
        seckillPromotion.setStatus(seckillPromotionDto.getStatus());
        Date date4= new Date();
        seckillPromotion.setCreateTime(date4);
        seckillPromotion.setUserId(seckillPromotionDto.getUserId());
        seckillPromotion.insert();
        return seckillPromotion;
    }

    /**
     * 更新秒杀活动
     * @param seckillPromotionDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SeckillPromotion update(SeckillPromotionDto seckillPromotionDto) {
        SeckillPromotion seckillPromotion = baseMapper.selectById(seckillPromotionDto.getId());
        if (seckillPromotion == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(seckillPromotionDto.getTitle(), seckillPromotion::setTitle);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillPromotionDto.getStartTime(), seckillPromotion::setStartTime);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillPromotionDto.getEndTime(), seckillPromotion::setEndTime);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillPromotionDto.getStatus(), seckillPromotion::setStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillPromotionDto.getUserId(), seckillPromotion::setUserId);
        seckillPromotion.updateById();
        return seckillPromotion;
    }

    /**
     * 删除秒杀活动
     * @param seckillPromotionDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SeckillPromotion delete(SeckillPromotionDto seckillPromotionDto) {
        SeckillPromotion seckillPromotion = baseMapper.selectById(seckillPromotionDto.getId());
        if (seckillPromotion == null) {
            return null;
        }
        seckillPromotion.deleteById();

        return seckillPromotion;
    }

    /**
     * 分页查询秒杀活动
     * @param seckillPromotionDto
     * @param seckillPromotionPage
     * @return
     */
    @Override
    public IPage<SeckillPromotion> queryPage(SeckillPromotionDto seckillPromotionDto, Page<SeckillPromotion> seckillPromotionPage) {

        return baseMapper.queryPage(seckillPromotionPage, seckillPromotionDto);
    }


    /**
     * 校验秒杀活动名称
     * @param seckillPromotionDto
     * @return
     */
    @Override
    public boolean checkName(SeckillPromotionDto seckillPromotionDto, boolean isAdd) {

        QueryWrapper<SeckillPromotion> seckillPromotionQueryWrapper = new QueryWrapper<SeckillPromotion>();
        seckillPromotionQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        seckillPromotionQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            seckillPromotionQueryWrapper.ne("id", seckillPromotionDto.getId());
        }

        Integer count = baseMapper.selectCount(seckillPromotionQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有秒杀活动
     * @return
     */
    public List<SeckillPromotion> queryAll(SeckillPromotionDto seckillPromotionDto) {
        return baseMapper.queryAll(seckillPromotionDto);
    }

}
