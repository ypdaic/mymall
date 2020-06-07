package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsProductAttrValue;
import com.ypdaic.mymall.product.mapper.PmsProductAttrValueMapper;
import com.ypdaic.mymall.product.service.IPmsProductAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsProductAttrValueDto;
import com.ypdaic.mymall.product.enums.PmsProductAttrValueExcelHeadersEnum;
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
 * @since 2020-06-07
 */
@Service
public class PmsProductAttrValueServiceImpl extends ServiceImpl<PmsProductAttrValueMapper, PmsProductAttrValue> implements IPmsProductAttrValueService {


    /**
     * 新增spu属性值
     * @param pmsProductAttrValueDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsProductAttrValue add(PmsProductAttrValueDto pmsProductAttrValueDto) {

        PmsProductAttrValue pmsProductAttrValue = new PmsProductAttrValue();
        pmsProductAttrValue.setSpuId(pmsProductAttrValueDto.getSpuId());
        pmsProductAttrValue.setAttrId(pmsProductAttrValueDto.getAttrId());
        pmsProductAttrValue.setAttrName(pmsProductAttrValueDto.getAttrName());
        pmsProductAttrValue.setAttrValue(pmsProductAttrValueDto.getAttrValue());
        pmsProductAttrValue.setAttrSort(pmsProductAttrValueDto.getAttrSort());
        pmsProductAttrValue.setQuickShow(pmsProductAttrValueDto.getQuickShow());
        pmsProductAttrValue.insert();
        return pmsProductAttrValue;
    }

    /**
     * 更新spu属性值
     * @param pmsProductAttrValueDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsProductAttrValue update(PmsProductAttrValueDto pmsProductAttrValueDto) {
        PmsProductAttrValue pmsProductAttrValue = baseMapper.selectById(pmsProductAttrValueDto.getId());
        if (pmsProductAttrValue == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsProductAttrValueDto.getSpuId(), pmsProductAttrValue::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsProductAttrValueDto.getAttrId(), pmsProductAttrValue::setAttrId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsProductAttrValueDto.getAttrName(), pmsProductAttrValue::setAttrName);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsProductAttrValueDto.getAttrValue(), pmsProductAttrValue::setAttrValue);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsProductAttrValueDto.getAttrSort(), pmsProductAttrValue::setAttrSort);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsProductAttrValueDto.getQuickShow(), pmsProductAttrValue::setQuickShow);
        pmsProductAttrValue.updateById();
        return pmsProductAttrValue;
    }

    /**
     * 删除spu属性值
     * @param pmsProductAttrValueDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsProductAttrValue delete(PmsProductAttrValueDto pmsProductAttrValueDto) {
        PmsProductAttrValue pmsProductAttrValue = baseMapper.selectById(pmsProductAttrValueDto.getId());
        if (pmsProductAttrValue == null) {
            return null;
        }
        pmsProductAttrValue.deleteById();

        return pmsProductAttrValue;
    }

    /**
     * 分页查询spu属性值
     * @param pmsProductAttrValueDto
     * @param pmsProductAttrValuePage
     * @return
     */
    @Override
    public IPage<PmsProductAttrValue> queryPage(PmsProductAttrValueDto pmsProductAttrValueDto, Page<PmsProductAttrValue> pmsProductAttrValuePage) {

        return baseMapper.queryPage(pmsProductAttrValuePage, pmsProductAttrValueDto);
    }


    /**
     * 校验spu属性值名称
     * @param pmsProductAttrValueDto
     * @return
     */
    @Override
    public boolean checkName(PmsProductAttrValueDto pmsProductAttrValueDto, boolean isAdd) {

        QueryWrapper<PmsProductAttrValue> pmsProductAttrValueQueryWrapper = new QueryWrapper<PmsProductAttrValue>();
        pmsProductAttrValueQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsProductAttrValueQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsProductAttrValueQueryWrapper.ne("id", pmsProductAttrValueDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsProductAttrValueQueryWrapper);
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
    public List<PmsProductAttrValue> queryAll(PmsProductAttrValueDto pmsProductAttrValueDto) {
        return baseMapper.queryAll(pmsProductAttrValueDto);
    }

}
