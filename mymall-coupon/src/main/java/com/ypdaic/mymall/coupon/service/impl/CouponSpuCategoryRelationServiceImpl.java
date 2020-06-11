package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.coupon.entity.CouponSpuCategoryRelation;
import com.ypdaic.mymall.coupon.mapper.CouponSpuCategoryRelationMapper;
import com.ypdaic.mymall.coupon.service.ICouponSpuCategoryRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.CouponSpuCategoryRelationDto;
import com.ypdaic.mymall.coupon.enums.CouponSpuCategoryRelationExcelHeadersEnum;
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
 * 优惠券分类关联 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class CouponSpuCategoryRelationServiceImpl extends ServiceImpl<CouponSpuCategoryRelationMapper, CouponSpuCategoryRelation> implements ICouponSpuCategoryRelationService {


    /**
     * 新增优惠券分类关联
     * @param couponSpuCategoryRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CouponSpuCategoryRelation add(CouponSpuCategoryRelationDto couponSpuCategoryRelationDto) {

        CouponSpuCategoryRelation couponSpuCategoryRelation = new CouponSpuCategoryRelation();
        couponSpuCategoryRelation.setCouponId(couponSpuCategoryRelationDto.getCouponId());
        couponSpuCategoryRelation.setCategoryId(couponSpuCategoryRelationDto.getCategoryId());
        couponSpuCategoryRelation.setCategoryName(couponSpuCategoryRelationDto.getCategoryName());
        couponSpuCategoryRelation.insert();
        return couponSpuCategoryRelation;
    }

    /**
     * 更新优惠券分类关联
     * @param couponSpuCategoryRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CouponSpuCategoryRelation update(CouponSpuCategoryRelationDto couponSpuCategoryRelationDto) {
        CouponSpuCategoryRelation couponSpuCategoryRelation = baseMapper.selectById(couponSpuCategoryRelationDto.getId());
        if (couponSpuCategoryRelation == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(couponSpuCategoryRelationDto.getCouponId(), couponSpuCategoryRelation::setCouponId);
        JavaUtils.INSTANCE.acceptIfNotNull(couponSpuCategoryRelationDto.getCategoryId(), couponSpuCategoryRelation::setCategoryId);
        JavaUtils.INSTANCE.acceptIfNotNull(couponSpuCategoryRelationDto.getCategoryName(), couponSpuCategoryRelation::setCategoryName);
        couponSpuCategoryRelation.updateById();
        return couponSpuCategoryRelation;
    }

    /**
     * 删除优惠券分类关联
     * @param couponSpuCategoryRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CouponSpuCategoryRelation delete(CouponSpuCategoryRelationDto couponSpuCategoryRelationDto) {
        CouponSpuCategoryRelation couponSpuCategoryRelation = baseMapper.selectById(couponSpuCategoryRelationDto.getId());
        if (couponSpuCategoryRelation == null) {
            return null;
        }
        couponSpuCategoryRelation.deleteById();

        return couponSpuCategoryRelation;
    }

    /**
     * 分页查询优惠券分类关联
     * @param couponSpuCategoryRelationDto
     * @param couponSpuCategoryRelationPage
     * @return
     */
    @Override
    public IPage<CouponSpuCategoryRelation> queryPage(CouponSpuCategoryRelationDto couponSpuCategoryRelationDto, Page<CouponSpuCategoryRelation> couponSpuCategoryRelationPage) {

        return baseMapper.queryPage(couponSpuCategoryRelationPage, couponSpuCategoryRelationDto);
    }


    /**
     * 校验优惠券分类关联名称
     * @param couponSpuCategoryRelationDto
     * @return
     */
    @Override
    public boolean checkName(CouponSpuCategoryRelationDto couponSpuCategoryRelationDto, boolean isAdd) {

        QueryWrapper<CouponSpuCategoryRelation> couponSpuCategoryRelationQueryWrapper = new QueryWrapper<CouponSpuCategoryRelation>();
        couponSpuCategoryRelationQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        couponSpuCategoryRelationQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            couponSpuCategoryRelationQueryWrapper.ne("id", couponSpuCategoryRelationDto.getId());
        }

        Integer count = baseMapper.selectCount(couponSpuCategoryRelationQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有优惠券分类关联
     * @return
     */
    @Override
    public List<CouponSpuCategoryRelation> queryAll(CouponSpuCategoryRelationDto couponSpuCategoryRelationDto) {
        return baseMapper.queryAll(couponSpuCategoryRelationDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CouponSpuCategoryRelation> page = this.page(
                new Query<CouponSpuCategoryRelation>().getPage(params),
                new QueryWrapper<CouponSpuCategoryRelation>()
        );

        return new PageUtils(page);
    }

}
