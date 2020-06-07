package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.ProductAttrValue;
import com.ypdaic.mymall.product.mapper.ProductAttrValueMapper;
import com.ypdaic.mymall.product.service.IProductAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.ProductAttrValueDto;
import com.ypdaic.mymall.product.enums.ProductAttrValueExcelHeadersEnum;
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
 * spu属性值 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValue> implements IProductAttrValueService {


    /**
     * 新增spu属性值
     * @param productAttrValueDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProductAttrValue add(ProductAttrValueDto productAttrValueDto) {

        ProductAttrValue productAttrValue = new ProductAttrValue();
        productAttrValue.setSpuId(productAttrValueDto.getSpuId());
        productAttrValue.setAttrId(productAttrValueDto.getAttrId());
        productAttrValue.setAttrName(productAttrValueDto.getAttrName());
        productAttrValue.setAttrValue(productAttrValueDto.getAttrValue());
        productAttrValue.setAttrSort(productAttrValueDto.getAttrSort());
        productAttrValue.setQuickShow(productAttrValueDto.getQuickShow());
        productAttrValue.insert();
        return productAttrValue;
    }

    /**
     * 更新spu属性值
     * @param productAttrValueDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProductAttrValue update(ProductAttrValueDto productAttrValueDto) {
        ProductAttrValue productAttrValue = baseMapper.selectById(productAttrValueDto.getId());
        if (productAttrValue == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(productAttrValueDto.getSpuId(), productAttrValue::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(productAttrValueDto.getAttrId(), productAttrValue::setAttrId);
        JavaUtils.INSTANCE.acceptIfNotNull(productAttrValueDto.getAttrName(), productAttrValue::setAttrName);
        JavaUtils.INSTANCE.acceptIfNotNull(productAttrValueDto.getAttrValue(), productAttrValue::setAttrValue);
        JavaUtils.INSTANCE.acceptIfNotNull(productAttrValueDto.getAttrSort(), productAttrValue::setAttrSort);
        JavaUtils.INSTANCE.acceptIfNotNull(productAttrValueDto.getQuickShow(), productAttrValue::setQuickShow);
        productAttrValue.updateById();
        return productAttrValue;
    }

    /**
     * 删除spu属性值
     * @param productAttrValueDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProductAttrValue delete(ProductAttrValueDto productAttrValueDto) {
        ProductAttrValue productAttrValue = baseMapper.selectById(productAttrValueDto.getId());
        if (productAttrValue == null) {
            return null;
        }
        productAttrValue.deleteById();

        return productAttrValue;
    }

    /**
     * 分页查询spu属性值
     * @param productAttrValueDto
     * @param productAttrValuePage
     * @return
     */
    @Override
    public IPage<ProductAttrValue> queryPage(ProductAttrValueDto productAttrValueDto, Page<ProductAttrValue> productAttrValuePage) {

        return baseMapper.queryPage(productAttrValuePage, productAttrValueDto);
    }


    /**
     * 校验spu属性值名称
     * @param productAttrValueDto
     * @return
     */
    @Override
    public boolean checkName(ProductAttrValueDto productAttrValueDto, boolean isAdd) {

        QueryWrapper<ProductAttrValue> productAttrValueQueryWrapper = new QueryWrapper<ProductAttrValue>();
        productAttrValueQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        productAttrValueQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            productAttrValueQueryWrapper.ne("id", productAttrValueDto.getId());
        }

        Integer count = baseMapper.selectCount(productAttrValueQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有spu属性值
     * @return
     */
    public List<ProductAttrValue> queryAll(ProductAttrValueDto productAttrValueDto) {
        return baseMapper.queryAll(productAttrValueDto);
    }

}
