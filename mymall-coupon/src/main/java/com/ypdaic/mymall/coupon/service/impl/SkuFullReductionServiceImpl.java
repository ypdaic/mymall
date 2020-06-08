package com.ypdaic.mymall.coupon.service.impl;

import com.ypdaic.mymall.coupon.entity.SkuFullReduction;
import com.ypdaic.mymall.coupon.mapper.SkuFullReductionMapper;
import com.ypdaic.mymall.coupon.service.ISkuFullReductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.coupon.vo.SkuFullReductionDto;
import com.ypdaic.mymall.coupon.enums.SkuFullReductionExcelHeadersEnum;
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
 * 商品满减信息 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionMapper, SkuFullReduction> implements ISkuFullReductionService {


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

}
