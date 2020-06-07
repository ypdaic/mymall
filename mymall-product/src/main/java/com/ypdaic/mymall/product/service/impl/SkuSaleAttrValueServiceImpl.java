package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.SkuSaleAttrValue;
import com.ypdaic.mymall.product.mapper.SkuSaleAttrValueMapper;
import com.ypdaic.mymall.product.service.ISkuSaleAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.SkuSaleAttrValueDto;
import com.ypdaic.mymall.product.enums.SkuSaleAttrValueExcelHeadersEnum;
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
 * sku销售属性&值 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueMapper, SkuSaleAttrValue> implements ISkuSaleAttrValueService {


    /**
     * 新增sku销售属性&值
     * @param skuSaleAttrValueDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuSaleAttrValue add(SkuSaleAttrValueDto skuSaleAttrValueDto) {

        SkuSaleAttrValue skuSaleAttrValue = new SkuSaleAttrValue();
        skuSaleAttrValue.setSkuId(skuSaleAttrValueDto.getSkuId());
        skuSaleAttrValue.setAttrId(skuSaleAttrValueDto.getAttrId());
        skuSaleAttrValue.setAttrName(skuSaleAttrValueDto.getAttrName());
        skuSaleAttrValue.setAttrValue(skuSaleAttrValueDto.getAttrValue());
        skuSaleAttrValue.setAttrSort(skuSaleAttrValueDto.getAttrSort());
        skuSaleAttrValue.insert();
        return skuSaleAttrValue;
    }

    /**
     * 更新sku销售属性&值
     * @param skuSaleAttrValueDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuSaleAttrValue update(SkuSaleAttrValueDto skuSaleAttrValueDto) {
        SkuSaleAttrValue skuSaleAttrValue = baseMapper.selectById(skuSaleAttrValueDto.getId());
        if (skuSaleAttrValue == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(skuSaleAttrValueDto.getSkuId(), skuSaleAttrValue::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(skuSaleAttrValueDto.getAttrId(), skuSaleAttrValue::setAttrId);
        JavaUtils.INSTANCE.acceptIfNotNull(skuSaleAttrValueDto.getAttrName(), skuSaleAttrValue::setAttrName);
        JavaUtils.INSTANCE.acceptIfNotNull(skuSaleAttrValueDto.getAttrValue(), skuSaleAttrValue::setAttrValue);
        JavaUtils.INSTANCE.acceptIfNotNull(skuSaleAttrValueDto.getAttrSort(), skuSaleAttrValue::setAttrSort);
        skuSaleAttrValue.updateById();
        return skuSaleAttrValue;
    }

    /**
     * 删除sku销售属性&值
     * @param skuSaleAttrValueDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuSaleAttrValue delete(SkuSaleAttrValueDto skuSaleAttrValueDto) {
        SkuSaleAttrValue skuSaleAttrValue = baseMapper.selectById(skuSaleAttrValueDto.getId());
        if (skuSaleAttrValue == null) {
            return null;
        }
        skuSaleAttrValue.deleteById();

        return skuSaleAttrValue;
    }

    /**
     * 分页查询sku销售属性&值
     * @param skuSaleAttrValueDto
     * @param skuSaleAttrValuePage
     * @return
     */
    @Override
    public IPage<SkuSaleAttrValue> queryPage(SkuSaleAttrValueDto skuSaleAttrValueDto, Page<SkuSaleAttrValue> skuSaleAttrValuePage) {

        return baseMapper.queryPage(skuSaleAttrValuePage, skuSaleAttrValueDto);
    }


    /**
     * 校验sku销售属性&值名称
     * @param skuSaleAttrValueDto
     * @return
     */
    @Override
    public boolean checkName(SkuSaleAttrValueDto skuSaleAttrValueDto, boolean isAdd) {

        QueryWrapper<SkuSaleAttrValue> skuSaleAttrValueQueryWrapper = new QueryWrapper<SkuSaleAttrValue>();
        skuSaleAttrValueQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        skuSaleAttrValueQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            skuSaleAttrValueQueryWrapper.ne("id", skuSaleAttrValueDto.getId());
        }

        Integer count = baseMapper.selectCount(skuSaleAttrValueQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有sku销售属性&值
     * @return
     */
    public List<SkuSaleAttrValue> queryAll(SkuSaleAttrValueDto skuSaleAttrValueDto) {
        return baseMapper.queryAll(skuSaleAttrValueDto);
    }

}
