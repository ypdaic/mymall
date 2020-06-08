package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.coupon.entity.CouponSpuRelation;
import com.ypdaic.mymall.coupon.mapper.CouponSpuRelationMapper;
import com.ypdaic.mymall.coupon.service.ICouponSpuRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.CouponSpuRelationDto;
import com.ypdaic.mymall.coupon.enums.CouponSpuRelationExcelHeadersEnum;
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
 * 优惠券与产品关联 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class CouponSpuRelationServiceImpl extends ServiceImpl<CouponSpuRelationMapper, CouponSpuRelation> implements ICouponSpuRelationService {


    /**
     * 新增优惠券与产品关联
     * @param couponSpuRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CouponSpuRelation add(CouponSpuRelationDto couponSpuRelationDto) {

        CouponSpuRelation couponSpuRelation = new CouponSpuRelation();
        couponSpuRelation.setCouponId(couponSpuRelationDto.getCouponId());
        couponSpuRelation.setSpuId(couponSpuRelationDto.getSpuId());
        couponSpuRelation.setSpuName(couponSpuRelationDto.getSpuName());
        couponSpuRelation.insert();
        return couponSpuRelation;
    }

    /**
     * 更新优惠券与产品关联
     * @param couponSpuRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CouponSpuRelation update(CouponSpuRelationDto couponSpuRelationDto) {
        CouponSpuRelation couponSpuRelation = baseMapper.selectById(couponSpuRelationDto.getId());
        if (couponSpuRelation == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(couponSpuRelationDto.getCouponId(), couponSpuRelation::setCouponId);
        JavaUtils.INSTANCE.acceptIfNotNull(couponSpuRelationDto.getSpuId(), couponSpuRelation::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(couponSpuRelationDto.getSpuName(), couponSpuRelation::setSpuName);
        couponSpuRelation.updateById();
        return couponSpuRelation;
    }

    /**
     * 删除优惠券与产品关联
     * @param couponSpuRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CouponSpuRelation delete(CouponSpuRelationDto couponSpuRelationDto) {
        CouponSpuRelation couponSpuRelation = baseMapper.selectById(couponSpuRelationDto.getId());
        if (couponSpuRelation == null) {
            return null;
        }
        couponSpuRelation.deleteById();

        return couponSpuRelation;
    }

    /**
     * 分页查询优惠券与产品关联
     * @param couponSpuRelationDto
     * @param couponSpuRelationPage
     * @return
     */
    @Override
    public IPage<CouponSpuRelation> queryPage(CouponSpuRelationDto couponSpuRelationDto, Page<CouponSpuRelation> couponSpuRelationPage) {

        return baseMapper.queryPage(couponSpuRelationPage, couponSpuRelationDto);
    }


    /**
     * 校验优惠券与产品关联名称
     * @param couponSpuRelationDto
     * @return
     */
    @Override
    public boolean checkName(CouponSpuRelationDto couponSpuRelationDto, boolean isAdd) {

        QueryWrapper<CouponSpuRelation> couponSpuRelationQueryWrapper = new QueryWrapper<CouponSpuRelation>();
        couponSpuRelationQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        couponSpuRelationQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            couponSpuRelationQueryWrapper.ne("id", couponSpuRelationDto.getId());
        }

        Integer count = baseMapper.selectCount(couponSpuRelationQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有优惠券与产品关联
     * @return
     */
    public List<CouponSpuRelation> queryAll(CouponSpuRelationDto couponSpuRelationDto) {
        return baseMapper.queryAll(couponSpuRelationDto);
    }

}
