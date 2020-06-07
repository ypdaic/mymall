package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsSkuSaleAttrValue;
import com.ypdaic.mymall.product.mapper.PmsSkuSaleAttrValueMapper;
import com.ypdaic.mymall.product.service.IPmsSkuSaleAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsSkuSaleAttrValueDto;
import com.ypdaic.mymall.product.enums.PmsSkuSaleAttrValueExcelHeadersEnum;
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
 * @since 2020-06-07
 */
@Service
public class PmsSkuSaleAttrValueServiceImpl extends ServiceImpl<PmsSkuSaleAttrValueMapper, PmsSkuSaleAttrValue> implements IPmsSkuSaleAttrValueService {


    /**
     * 新增sku销售属性&值
     * @param pmsSkuSaleAttrValueDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSkuSaleAttrValue add(PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto) {

        PmsSkuSaleAttrValue pmsSkuSaleAttrValue = new PmsSkuSaleAttrValue();
        pmsSkuSaleAttrValue.setSkuId(pmsSkuSaleAttrValueDto.getSkuId());
        pmsSkuSaleAttrValue.setAttrId(pmsSkuSaleAttrValueDto.getAttrId());
        pmsSkuSaleAttrValue.setAttrName(pmsSkuSaleAttrValueDto.getAttrName());
        pmsSkuSaleAttrValue.setAttrValue(pmsSkuSaleAttrValueDto.getAttrValue());
        pmsSkuSaleAttrValue.setAttrSort(pmsSkuSaleAttrValueDto.getAttrSort());
        pmsSkuSaleAttrValue.insert();
        return pmsSkuSaleAttrValue;
    }

    /**
     * 更新sku销售属性&值
     * @param pmsSkuSaleAttrValueDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSkuSaleAttrValue update(PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto) {
        PmsSkuSaleAttrValue pmsSkuSaleAttrValue = baseMapper.selectById(pmsSkuSaleAttrValueDto.getId());
        if (pmsSkuSaleAttrValue == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuSaleAttrValueDto.getSkuId(), pmsSkuSaleAttrValue::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuSaleAttrValueDto.getAttrId(), pmsSkuSaleAttrValue::setAttrId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuSaleAttrValueDto.getAttrName(), pmsSkuSaleAttrValue::setAttrName);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuSaleAttrValueDto.getAttrValue(), pmsSkuSaleAttrValue::setAttrValue);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuSaleAttrValueDto.getAttrSort(), pmsSkuSaleAttrValue::setAttrSort);
        pmsSkuSaleAttrValue.updateById();
        return pmsSkuSaleAttrValue;
    }

    /**
     * 删除sku销售属性&值
     * @param pmsSkuSaleAttrValueDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSkuSaleAttrValue delete(PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto) {
        PmsSkuSaleAttrValue pmsSkuSaleAttrValue = baseMapper.selectById(pmsSkuSaleAttrValueDto.getId());
        if (pmsSkuSaleAttrValue == null) {
            return null;
        }
        pmsSkuSaleAttrValue.deleteById();

        return pmsSkuSaleAttrValue;
    }

    /**
     * 分页查询sku销售属性&值
     * @param pmsSkuSaleAttrValueDto
     * @param pmsSkuSaleAttrValuePage
     * @return
     */
    @Override
    public IPage<PmsSkuSaleAttrValue> queryPage(PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto, Page<PmsSkuSaleAttrValue> pmsSkuSaleAttrValuePage) {

        return baseMapper.queryPage(pmsSkuSaleAttrValuePage, pmsSkuSaleAttrValueDto);
    }


    /**
     * 校验sku销售属性&值名称
     * @param pmsSkuSaleAttrValueDto
     * @return
     */
    @Override
    public boolean checkName(PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto, boolean isAdd) {

        QueryWrapper<PmsSkuSaleAttrValue> pmsSkuSaleAttrValueQueryWrapper = new QueryWrapper<PmsSkuSaleAttrValue>();
        pmsSkuSaleAttrValueQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsSkuSaleAttrValueQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsSkuSaleAttrValueQueryWrapper.ne("id", pmsSkuSaleAttrValueDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsSkuSaleAttrValueQueryWrapper);
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
    public List<PmsSkuSaleAttrValue> queryAll(PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto) {
        return baseMapper.queryAll(pmsSkuSaleAttrValueDto);
    }

}
