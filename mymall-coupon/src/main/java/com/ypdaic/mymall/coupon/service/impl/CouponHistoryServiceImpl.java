package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.coupon.entity.CouponHistory;
import com.ypdaic.mymall.coupon.mapper.CouponHistoryMapper;
import com.ypdaic.mymall.coupon.service.ICouponHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.CouponHistoryDto;
import com.ypdaic.mymall.coupon.enums.CouponHistoryExcelHeadersEnum;
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
 * 优惠券领取历史记录 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryMapper, CouponHistory> implements ICouponHistoryService {


    /**
     * 新增优惠券领取历史记录
     * @param couponHistoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CouponHistory add(CouponHistoryDto couponHistoryDto) {

        CouponHistory couponHistory = new CouponHistory();
        couponHistory.setCouponId(couponHistoryDto.getCouponId());
        couponHistory.setMemberId(couponHistoryDto.getMemberId());
        couponHistory.setMemberNickName(couponHistoryDto.getMemberNickName());
        couponHistory.setGetType(couponHistoryDto.getGetType());
        Date date4= new Date();
        couponHistory.setCreateTime(date4);
        couponHistory.setUseType(couponHistoryDto.getUseType());
        Date date6= new Date();
        couponHistory.setUseTime(date6);
        couponHistory.setOrderId(couponHistoryDto.getOrderId());
        couponHistory.setOrderSn(couponHistoryDto.getOrderSn());
        couponHistory.insert();
        return couponHistory;
    }

    /**
     * 更新优惠券领取历史记录
     * @param couponHistoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CouponHistory update(CouponHistoryDto couponHistoryDto) {
        CouponHistory couponHistory = baseMapper.selectById(couponHistoryDto.getId());
        if (couponHistory == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(couponHistoryDto.getCouponId(), couponHistory::setCouponId);
        JavaUtils.INSTANCE.acceptIfNotNull(couponHistoryDto.getMemberId(), couponHistory::setMemberId);
        JavaUtils.INSTANCE.acceptIfNotNull(couponHistoryDto.getMemberNickName(), couponHistory::setMemberNickName);
        JavaUtils.INSTANCE.acceptIfNotNull(couponHistoryDto.getGetType(), couponHistory::setGetType);
        JavaUtils.INSTANCE.acceptIfNotNull(couponHistoryDto.getUseType(), couponHistory::setUseType);
        JavaUtils.INSTANCE.acceptIfNotNull(couponHistoryDto.getUseTime(), couponHistory::setUseTime);
        JavaUtils.INSTANCE.acceptIfNotNull(couponHistoryDto.getOrderId(), couponHistory::setOrderId);
        JavaUtils.INSTANCE.acceptIfNotNull(couponHistoryDto.getOrderSn(), couponHistory::setOrderSn);
        couponHistory.updateById();
        return couponHistory;
    }

    /**
     * 删除优惠券领取历史记录
     * @param couponHistoryDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CouponHistory delete(CouponHistoryDto couponHistoryDto) {
        CouponHistory couponHistory = baseMapper.selectById(couponHistoryDto.getId());
        if (couponHistory == null) {
            return null;
        }
        couponHistory.deleteById();

        return couponHistory;
    }

    /**
     * 分页查询优惠券领取历史记录
     * @param couponHistoryDto
     * @param couponHistoryPage
     * @return
     */
    @Override
    public IPage<CouponHistory> queryPage(CouponHistoryDto couponHistoryDto, Page<CouponHistory> couponHistoryPage) {

        return baseMapper.queryPage(couponHistoryPage, couponHistoryDto);
    }


    /**
     * 校验优惠券领取历史记录名称
     * @param couponHistoryDto
     * @return
     */
    @Override
    public boolean checkName(CouponHistoryDto couponHistoryDto, boolean isAdd) {

        QueryWrapper<CouponHistory> couponHistoryQueryWrapper = new QueryWrapper<CouponHistory>();
        couponHistoryQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        couponHistoryQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            couponHistoryQueryWrapper.ne("id", couponHistoryDto.getId());
        }

        Integer count = baseMapper.selectCount(couponHistoryQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有优惠券领取历史记录
     * @return
     */
    @Override
    public List<CouponHistory> queryAll(CouponHistoryDto couponHistoryDto) {
        return baseMapper.queryAll(couponHistoryDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponHistory> page = this.page(
                new Query<CouponHistory>().getPage(params),
                new QueryWrapper<CouponHistory>()
        );

        return new PageUtils(page);
    }

}
