package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.coupon.entity.SkuLadder;
import com.ypdaic.mymall.coupon.mapper.SkuLadderMapper;
import com.ypdaic.mymall.coupon.service.ISkuLadderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.SkuLadderDto;
import com.ypdaic.mymall.coupon.enums.SkuLadderExcelHeadersEnum;
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
 * 商品阶梯价格 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class SkuLadderServiceImpl extends ServiceImpl<SkuLadderMapper, SkuLadder> implements ISkuLadderService {


    /**
     * 新增商品阶梯价格
     * @param skuLadderDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuLadder add(SkuLadderDto skuLadderDto) {

        SkuLadder skuLadder = new SkuLadder();
        skuLadder.setSkuId(skuLadderDto.getSkuId());
        skuLadder.setFullCount(skuLadderDto.getFullCount());
        skuLadder.setDiscount(skuLadderDto.getDiscount());
        skuLadder.setPrice(skuLadderDto.getPrice());
        skuLadder.setAddOther(skuLadderDto.getAddOther());
        skuLadder.insert();
        return skuLadder;
    }

    /**
     * 更新商品阶梯价格
     * @param skuLadderDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuLadder update(SkuLadderDto skuLadderDto) {
        SkuLadder skuLadder = baseMapper.selectById(skuLadderDto.getId());
        if (skuLadder == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(skuLadderDto.getSkuId(), skuLadder::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(skuLadderDto.getFullCount(), skuLadder::setFullCount);
        JavaUtils.INSTANCE.acceptIfNotNull(skuLadderDto.getDiscount(), skuLadder::setDiscount);
        JavaUtils.INSTANCE.acceptIfNotNull(skuLadderDto.getPrice(), skuLadder::setPrice);
        JavaUtils.INSTANCE.acceptIfNotNull(skuLadderDto.getAddOther(), skuLadder::setAddOther);
        skuLadder.updateById();
        return skuLadder;
    }

    /**
     * 删除商品阶梯价格
     * @param skuLadderDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuLadder delete(SkuLadderDto skuLadderDto) {
        SkuLadder skuLadder = baseMapper.selectById(skuLadderDto.getId());
        if (skuLadder == null) {
            return null;
        }
        skuLadder.deleteById();

        return skuLadder;
    }

    /**
     * 分页查询商品阶梯价格
     * @param skuLadderDto
     * @param skuLadderPage
     * @return
     */
    @Override
    public IPage<SkuLadder> queryPage(SkuLadderDto skuLadderDto, Page<SkuLadder> skuLadderPage) {

        return baseMapper.queryPage(skuLadderPage, skuLadderDto);
    }


    /**
     * 校验商品阶梯价格名称
     * @param skuLadderDto
     * @return
     */
    @Override
    public boolean checkName(SkuLadderDto skuLadderDto, boolean isAdd) {

        QueryWrapper<SkuLadder> skuLadderQueryWrapper = new QueryWrapper<SkuLadder>();
        skuLadderQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        skuLadderQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            skuLadderQueryWrapper.ne("id", skuLadderDto.getId());
        }

        Integer count = baseMapper.selectCount(skuLadderQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有商品阶梯价格
     * @return
     */
    public List<SkuLadder> queryAll(SkuLadderDto skuLadderDto) {
        return baseMapper.queryAll(skuLadderDto);
    }

}
