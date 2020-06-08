package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.coupon.entity.Coupon;
import com.ypdaic.mymall.coupon.mapper.CouponMapper;
import com.ypdaic.mymall.coupon.service.ICouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.CouponDto;
import com.ypdaic.mymall.coupon.enums.CouponExcelHeadersEnum;
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
import java.util.Date;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements ICouponService {


    /**
     * 新增优惠券信息
     * @param couponDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Coupon add(CouponDto couponDto) {

        Coupon coupon = new Coupon();
        coupon.setCouponType(couponDto.getCouponType());
        coupon.setCouponImg(couponDto.getCouponImg());
        coupon.setCouponName(couponDto.getCouponName());
        coupon.setNum(couponDto.getNum());
        coupon.setAmount(couponDto.getAmount());
        coupon.setPerLimit(couponDto.getPerLimit());
        coupon.setMinPoint(couponDto.getMinPoint());
        Date date7= new Date();
        coupon.setStartTime(date7);
        Date date8= new Date();
        coupon.setEndTime(date8);
        coupon.setUseType(couponDto.getUseType());
        coupon.setNote(couponDto.getNote());
        coupon.setPublishCount(couponDto.getPublishCount());
        coupon.setUseCount(couponDto.getUseCount());
        coupon.setReceiveCount(couponDto.getReceiveCount());
        Date date14= new Date();
        coupon.setEnableStartTime(date14);
        Date date15= new Date();
        coupon.setEnableEndTime(date15);
        coupon.setCode(couponDto.getCode());
        coupon.setMemberLevel(couponDto.getMemberLevel());
        coupon.setPublish(couponDto.getPublish());
        coupon.insert();
        return coupon;
    }

    /**
     * 更新优惠券信息
     * @param couponDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Coupon update(CouponDto couponDto) {
        Coupon coupon = baseMapper.selectById(couponDto.getId());
        if (coupon == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getCouponType(), coupon::setCouponType);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getCouponImg(), coupon::setCouponImg);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getCouponName(), coupon::setCouponName);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getNum(), coupon::setNum);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getAmount(), coupon::setAmount);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getPerLimit(), coupon::setPerLimit);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getMinPoint(), coupon::setMinPoint);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getStartTime(), coupon::setStartTime);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getEndTime(), coupon::setEndTime);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getUseType(), coupon::setUseType);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getNote(), coupon::setNote);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getPublishCount(), coupon::setPublishCount);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getUseCount(), coupon::setUseCount);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getReceiveCount(), coupon::setReceiveCount);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getEnableStartTime(), coupon::setEnableStartTime);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getEnableEndTime(), coupon::setEnableEndTime);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getCode(), coupon::setCode);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getMemberLevel(), coupon::setMemberLevel);
        JavaUtils.INSTANCE.acceptIfNotNull(couponDto.getPublish(), coupon::setPublish);
        coupon.updateById();
        return coupon;
    }

    /**
     * 删除优惠券信息
     * @param couponDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Coupon delete(CouponDto couponDto) {
        Coupon coupon = baseMapper.selectById(couponDto.getId());
        if (coupon == null) {
            return null;
        }
        coupon.deleteById();

        return coupon;
    }

    /**
     * 分页查询优惠券信息
     * @param couponDto
     * @param couponPage
     * @return
     */
    @Override
    public IPage<Coupon> queryPage(CouponDto couponDto, Page<Coupon> couponPage) {

        return baseMapper.queryPage(couponPage, couponDto);
    }


    /**
     * 校验优惠券信息名称
     * @param couponDto
     * @return
     */
    @Override
    public boolean checkName(CouponDto couponDto, boolean isAdd) {

        QueryWrapper<Coupon> couponQueryWrapper = new QueryWrapper<Coupon>();
        couponQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        couponQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            couponQueryWrapper.ne("id", couponDto.getId());
        }

        Integer count = baseMapper.selectCount(couponQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有优惠券信息
     * @return
     */
    public List<Coupon> queryAll(CouponDto couponDto) {
        return baseMapper.queryAll(couponDto);
    }

}
