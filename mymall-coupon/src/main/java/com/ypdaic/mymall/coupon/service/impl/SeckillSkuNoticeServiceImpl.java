package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.coupon.entity.SeckillSkuNotice;
import com.ypdaic.mymall.coupon.mapper.SeckillSkuNoticeMapper;
import com.ypdaic.mymall.coupon.service.ISeckillSkuNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.SeckillSkuNoticeDto;
import com.ypdaic.mymall.coupon.enums.SeckillSkuNoticeExcelHeadersEnum;
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

/**
 * <p>
 * 秒杀商品通知订阅 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class SeckillSkuNoticeServiceImpl extends ServiceImpl<SeckillSkuNoticeMapper, SeckillSkuNotice> implements ISeckillSkuNoticeService {


    /**
     * 新增秒杀商品通知订阅
     * @param seckillSkuNoticeDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SeckillSkuNotice add(SeckillSkuNoticeDto seckillSkuNoticeDto) {

        SeckillSkuNotice seckillSkuNotice = new SeckillSkuNotice();
        seckillSkuNotice.setMemberId(seckillSkuNoticeDto.getMemberId());
        seckillSkuNotice.setSkuId(seckillSkuNoticeDto.getSkuId());
        seckillSkuNotice.setSessionId(seckillSkuNoticeDto.getSessionId());
        Date date3= new Date();
        seckillSkuNotice.setSubcribeTime(date3);
        Date date4= new Date();
        seckillSkuNotice.setSendTime(date4);
        seckillSkuNotice.setNoticeType(seckillSkuNoticeDto.getNoticeType());
        seckillSkuNotice.insert();
        return seckillSkuNotice;
    }

    /**
     * 更新秒杀商品通知订阅
     * @param seckillSkuNoticeDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SeckillSkuNotice update(SeckillSkuNoticeDto seckillSkuNoticeDto) {
        SeckillSkuNotice seckillSkuNotice = baseMapper.selectById(seckillSkuNoticeDto.getId());
        if (seckillSkuNotice == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(seckillSkuNoticeDto.getMemberId(), seckillSkuNotice::setMemberId);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSkuNoticeDto.getSkuId(), seckillSkuNotice::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSkuNoticeDto.getSessionId(), seckillSkuNotice::setSessionId);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSkuNoticeDto.getSubcribeTime(), seckillSkuNotice::setSubcribeTime);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSkuNoticeDto.getSendTime(), seckillSkuNotice::setSendTime);
        JavaUtils.INSTANCE.acceptIfNotNull(seckillSkuNoticeDto.getNoticeType(), seckillSkuNotice::setNoticeType);
        seckillSkuNotice.updateById();
        return seckillSkuNotice;
    }

    /**
     * 删除秒杀商品通知订阅
     * @param seckillSkuNoticeDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SeckillSkuNotice delete(SeckillSkuNoticeDto seckillSkuNoticeDto) {
        SeckillSkuNotice seckillSkuNotice = baseMapper.selectById(seckillSkuNoticeDto.getId());
        if (seckillSkuNotice == null) {
            return null;
        }
        seckillSkuNotice.deleteById();

        return seckillSkuNotice;
    }

    /**
     * 分页查询秒杀商品通知订阅
     * @param seckillSkuNoticeDto
     * @param seckillSkuNoticePage
     * @return
     */
    @Override
    public IPage<SeckillSkuNotice> queryPage(SeckillSkuNoticeDto seckillSkuNoticeDto, Page<SeckillSkuNotice> seckillSkuNoticePage) {

        return baseMapper.queryPage(seckillSkuNoticePage, seckillSkuNoticeDto);
    }


    /**
     * 校验秒杀商品通知订阅名称
     * @param seckillSkuNoticeDto
     * @return
     */
    @Override
    public boolean checkName(SeckillSkuNoticeDto seckillSkuNoticeDto, boolean isAdd) {

        QueryWrapper<SeckillSkuNotice> seckillSkuNoticeQueryWrapper = new QueryWrapper<SeckillSkuNotice>();
        seckillSkuNoticeQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        seckillSkuNoticeQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            seckillSkuNoticeQueryWrapper.ne("id", seckillSkuNoticeDto.getId());
        }

        Integer count = baseMapper.selectCount(seckillSkuNoticeQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有秒杀商品通知订阅
     * @return
     */
    public List<SeckillSkuNotice> queryAll(SeckillSkuNoticeDto seckillSkuNoticeDto) {
        return baseMapper.queryAll(seckillSkuNoticeDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillSkuNotice> page = this.page(
                new Query<SeckillSkuNotice>().getPage(params),
                new QueryWrapper<SeckillSkuNotice>()
        );

        return new PageUtils(page);
    }

}
