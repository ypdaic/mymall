package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.coupon.entity.MemberPrice;
import com.ypdaic.mymall.coupon.mapper.MemberPriceMapper;
import com.ypdaic.mymall.coupon.service.IMemberPriceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.MemberPriceDto;
import com.ypdaic.mymall.coupon.enums.MemberPriceExcelHeadersEnum;
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
 * 商品会员价格 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceMapper, MemberPrice> implements IMemberPriceService {


    /**
     * 新增商品会员价格
     * @param memberPriceDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberPrice add(MemberPriceDto memberPriceDto) {

        MemberPrice memberPrice = new MemberPrice();
        memberPrice.setSkuId(memberPriceDto.getSkuId());
        memberPrice.setMemberLevelId(memberPriceDto.getMemberLevelId());
        memberPrice.setMemberLevelName(memberPriceDto.getMemberLevelName());
        memberPrice.setMemberPrice(memberPriceDto.getMemberPrice());
        memberPrice.setAddOther(memberPriceDto.getAddOther());
        memberPrice.insert();
        return memberPrice;
    }

    /**
     * 更新商品会员价格
     * @param memberPriceDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberPrice update(MemberPriceDto memberPriceDto) {
        MemberPrice memberPrice = baseMapper.selectById(memberPriceDto.getId());
        if (memberPrice == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(memberPriceDto.getSkuId(), memberPrice::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(memberPriceDto.getMemberLevelId(), memberPrice::setMemberLevelId);
        JavaUtils.INSTANCE.acceptIfNotNull(memberPriceDto.getMemberLevelName(), memberPrice::setMemberLevelName);
        JavaUtils.INSTANCE.acceptIfNotNull(memberPriceDto.getMemberPrice(), memberPrice::setMemberPrice);
        JavaUtils.INSTANCE.acceptIfNotNull(memberPriceDto.getAddOther(), memberPrice::setAddOther);
        memberPrice.updateById();
        return memberPrice;
    }

    /**
     * 删除商品会员价格
     * @param memberPriceDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public MemberPrice delete(MemberPriceDto memberPriceDto) {
        MemberPrice memberPrice = baseMapper.selectById(memberPriceDto.getId());
        if (memberPrice == null) {
            return null;
        }
        memberPrice.deleteById();

        return memberPrice;
    }

    /**
     * 分页查询商品会员价格
     * @param memberPriceDto
     * @param memberPricePage
     * @return
     */
    @Override
    public IPage<MemberPrice> queryPage(MemberPriceDto memberPriceDto, Page<MemberPrice> memberPricePage) {

        return baseMapper.queryPage(memberPricePage, memberPriceDto);
    }


    /**
     * 校验商品会员价格名称
     * @param memberPriceDto
     * @return
     */
    @Override
    public boolean checkName(MemberPriceDto memberPriceDto, boolean isAdd) {

        QueryWrapper<MemberPrice> memberPriceQueryWrapper = new QueryWrapper<MemberPrice>();
        memberPriceQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        memberPriceQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            memberPriceQueryWrapper.ne("id", memberPriceDto.getId());
        }

        Integer count = baseMapper.selectCount(memberPriceQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有商品会员价格
     * @return
     */
    public List<MemberPrice> queryAll(MemberPriceDto memberPriceDto) {
        return baseMapper.queryAll(memberPriceDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberPrice> page = this.page(
                new Query<MemberPrice>().getPage(params),
                new QueryWrapper<MemberPrice>()
        );

        return new PageUtils(page);
    }

}
