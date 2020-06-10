package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.common.to.MemberPrice;
import com.ypdaic.mymall.common.to.SkuReductionTo;
import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.coupon.entity.SkuFullReduction;
import com.ypdaic.mymall.coupon.entity.SkuLadder;
import com.ypdaic.mymall.coupon.mapper.SkuFullReductionMapper;
import com.ypdaic.mymall.coupon.service.IMemberPriceService;
import com.ypdaic.mymall.coupon.service.ISkuFullReductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.service.ISkuLadderService;
import com.ypdaic.mymall.coupon.vo.SkuFullReductionDto;
import com.ypdaic.mymall.coupon.enums.SkuFullReductionExcelHeadersEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypdaic.mymall.common.util.ExcelUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.util.JavaUtils;

/**
 * <p>
 * 商品满减信息 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionMapper, SkuFullReduction> implements ISkuFullReductionService {

    @Autowired
    ISkuLadderService skuLadderService;

    @Autowired
    IMemberPriceService memberPriceService;



    /**
     * 新增商品满减信息
     * @param skuFullReductionDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuFullReduction add(SkuFullReductionDto skuFullReductionDto) {

        SkuFullReduction skuFullReduction = new SkuFullReduction();
        skuFullReduction.setSkuId(skuFullReductionDto.getSkuId());
        skuFullReduction.setFullPrice(skuFullReductionDto.getFullPrice());
        skuFullReduction.setReducePrice(skuFullReductionDto.getReducePrice());
        skuFullReduction.setAddOther(skuFullReductionDto.getAddOther());
        skuFullReduction.insert();
        return skuFullReduction;
    }

    /**
     * 更新商品满减信息
     * @param skuFullReductionDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuFullReduction update(SkuFullReductionDto skuFullReductionDto) {
        SkuFullReduction skuFullReduction = baseMapper.selectById(skuFullReductionDto.getId());
        if (skuFullReduction == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(skuFullReductionDto.getSkuId(), skuFullReduction::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(skuFullReductionDto.getFullPrice(), skuFullReduction::setFullPrice);
        JavaUtils.INSTANCE.acceptIfNotNull(skuFullReductionDto.getReducePrice(), skuFullReduction::setReducePrice);
        JavaUtils.INSTANCE.acceptIfNotNull(skuFullReductionDto.getAddOther(), skuFullReduction::setAddOther);
        skuFullReduction.updateById();
        return skuFullReduction;
    }

    /**
     * 删除商品满减信息
     * @param skuFullReductionDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuFullReduction delete(SkuFullReductionDto skuFullReductionDto) {
        SkuFullReduction skuFullReduction = baseMapper.selectById(skuFullReductionDto.getId());
        if (skuFullReduction == null) {
            return null;
        }
        skuFullReduction.deleteById();

        return skuFullReduction;
    }

    /**
     * 分页查询商品满减信息
     * @param skuFullReductionDto
     * @param skuFullReductionPage
     * @return
     */
    @Override
    public IPage<SkuFullReduction> queryPage(SkuFullReductionDto skuFullReductionDto, Page<SkuFullReduction> skuFullReductionPage) {

        return baseMapper.queryPage(skuFullReductionPage, skuFullReductionDto);
    }


    /**
     * 校验商品满减信息名称
     * @param skuFullReductionDto
     * @return
     */
    @Override
    public boolean checkName(SkuFullReductionDto skuFullReductionDto, boolean isAdd) {

        QueryWrapper<SkuFullReduction> skuFullReductionQueryWrapper = new QueryWrapper<SkuFullReduction>();
        skuFullReductionQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        skuFullReductionQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            skuFullReductionQueryWrapper.ne("id", skuFullReductionDto.getId());
        }

        Integer count = baseMapper.selectCount(skuFullReductionQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有商品满减信息
     * @return
     */
    public List<SkuFullReduction> queryAll(SkuFullReductionDto skuFullReductionDto) {
        return baseMapper.queryAll(skuFullReductionDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReduction> page = this.page(
                new Query<SkuFullReduction>().getPage(params),
                new QueryWrapper<SkuFullReduction>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuReduction(SkuReductionTo reductionTo) {
        //1、// //5.4）、sku的优惠、满减等信息；gulimall_sms->sms_sku_ladder\sms_sku_full_reduction\sms_member_price
        //sms_sku_ladder
        SkuLadder skuLadderEntity = new SkuLadder();
        skuLadderEntity.setSkuId(reductionTo.getSkuId());
        skuLadderEntity.setFullCount(reductionTo.getFullCount());
        skuLadderEntity.setDiscount(reductionTo.getDiscount());
        skuLadderEntity.setAddOther(reductionTo.getCountStatus());
        if(reductionTo.getFullCount() > 0){
            skuLadderService.save(skuLadderEntity);
        }




        //2、sms_sku_full_reduction
        SkuFullReduction reductionEntity = new SkuFullReduction();
        BeanUtils.copyProperties(reductionTo,reductionEntity);
        if(reductionEntity.getFullPrice().compareTo(new BigDecimal("0"))==1){
            this.save(reductionEntity);
        }


        //3、sms_member_price
        List<MemberPrice> memberPrice = reductionTo.getMemberPrice();

        List<com.ypdaic.mymall.coupon.entity.MemberPrice> collect = memberPrice.stream().map(item -> {
            com.ypdaic.mymall.coupon.entity.MemberPrice priceEntity = new com.ypdaic.mymall.coupon.entity.MemberPrice();
            priceEntity.setSkuId(reductionTo.getSkuId());
            priceEntity.setMemberLevelId(item.getId());
            priceEntity.setMemberLevelName(item.getName());
            priceEntity.setMemberPrice(item.getPrice());
            priceEntity.setAddOther(1);
            return priceEntity;
        }).filter(item->{
            return item.getMemberPrice().compareTo(new BigDecimal("0")) == 1;
        }).collect(Collectors.toList());

        memberPriceService.saveBatch(collect);
    }

}
