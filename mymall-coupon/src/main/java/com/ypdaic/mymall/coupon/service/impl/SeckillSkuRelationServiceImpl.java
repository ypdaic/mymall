package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.coupon.entity.SeckillSkuRelation;
import com.ypdaic.mymall.coupon.mapper.SeckillSkuRelationMapper;
import com.ypdaic.mymall.coupon.service.ISeckillSkuRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.SeckillSkuRelationDto;
import com.ypdaic.mymall.coupon.enums.SeckillSkuRelationExcelHeadersEnum;
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

/**
 * <p>
 * 秒杀活动商品关联 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class SeckillSkuRelationServiceImpl extends ServiceImpl<SeckillSkuRelationMapper, SeckillSkuRelation> implements ISeckillSkuRelationService {


    /**
     * 新增秒杀活动商品关联
     * @param seckillSkuRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SeckillSkuRelation add(SeckillSkuRelationDto seckillSkuRelationDto) {

        SeckillSkuRelation seckillSkuRelation = new SeckillSkuRelation();
        seckillSkuRelation.setPromotionId(seckillSkuRelationDto.getPromotionId());
        seckillSkuRelation.setPromotionSessionId(seckillSkuRelationDto.getPromotionSessionId());
        seckillSkuRelation.setSkuId(seckillSkuRelationDto.getSkuId());
        seckillSkuRelation.setSeckillPrice(seckillSkuRelationDto.getSeckillPrice());
        seckillSkuRelation.setSeckillCount(seckillSkuRelationDto.getSeckillCount());
        seckillSkuRelation.setSeckillLimit(seckillSkuRelationDto.getSeckillLimit());
        seckillSkuRelation.setSeckillSort(seckillSkuRelationDto.getSeckillSort());
        seckillSkuRelation.insert();
        return seckillSkuRelation;
    }

    /**
     * 更新秒杀活动商品关联
     * @param seckillSkuRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SeckillSkuRelation update(SeckillSkuRelationDto seckillSkuRelationDto) {
        SeckillSkuRelation seckillSkuRelation = baseMapper.selectById(seckillSkuRelationDto.getId());
        if (seckillSkuRelation == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(seckillSkuRelationDto.getPromotionId(), seckillSkuRelation::setPromotionId);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSkuRelationDto.getPromotionSessionId(), seckillSkuRelation::setPromotionSessionId);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSkuRelationDto.getSkuId(), seckillSkuRelation::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSkuRelationDto.getSeckillPrice(), seckillSkuRelation::setSeckillPrice);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSkuRelationDto.getSeckillCount(), seckillSkuRelation::setSeckillCount);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSkuRelationDto.getSeckillLimit(), seckillSkuRelation::setSeckillLimit);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSkuRelationDto.getSeckillSort(), seckillSkuRelation::setSeckillSort);
        seckillSkuRelation.updateById();
        return seckillSkuRelation;
    }

    /**
     * 删除秒杀活动商品关联
     * @param seckillSkuRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SeckillSkuRelation delete(SeckillSkuRelationDto seckillSkuRelationDto) {
        SeckillSkuRelation seckillSkuRelation = baseMapper.selectById(seckillSkuRelationDto.getId());
        if (seckillSkuRelation == null) {
            return null;
        }
        seckillSkuRelation.deleteById();

        return seckillSkuRelation;
    }

    /**
     * 分页查询秒杀活动商品关联
     * @param seckillSkuRelationDto
     * @param seckillSkuRelationPage
     * @return
     */
    @Override
    public IPage<SeckillSkuRelation> queryPage(SeckillSkuRelationDto seckillSkuRelationDto, Page<SeckillSkuRelation> seckillSkuRelationPage) {

        return baseMapper.queryPage(seckillSkuRelationPage, seckillSkuRelationDto);
    }


    /**
     * 校验秒杀活动商品关联名称
     * @param seckillSkuRelationDto
     * @return
     */
    @Override
    public boolean checkName(SeckillSkuRelationDto seckillSkuRelationDto, boolean isAdd) {

        QueryWrapper<SeckillSkuRelation> seckillSkuRelationQueryWrapper = new QueryWrapper<SeckillSkuRelation>();
        seckillSkuRelationQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        seckillSkuRelationQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            seckillSkuRelationQueryWrapper.ne("id", seckillSkuRelationDto.getId());
        }

        Integer count = baseMapper.selectCount(seckillSkuRelationQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有秒杀活动商品关联
     * @return
     */
    public List<SeckillSkuRelation> queryAll(SeckillSkuRelationDto seckillSkuRelationDto) {
        return baseMapper.queryAll(seckillSkuRelationDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<SeckillSkuRelation> seckillSkuRelationQueryWrapper = new QueryWrapper<>();
        seckillSkuRelationQueryWrapper.eq(params.get("promotionSessionId") != null, "promotion_session_id", params.get("promotionSessionId"));
        IPage<SeckillSkuRelation> page = this.page(
                new Query<SeckillSkuRelation>().getPage(params),
                seckillSkuRelationQueryWrapper
        );

        return new PageUtils(page);
    }

}
