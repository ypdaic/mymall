package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsAttr;
import com.ypdaic.mymall.product.mapper.PmsAttrMapper;
import com.ypdaic.mymall.product.service.IPmsAttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsAttrDto;
import com.ypdaic.mymall.product.enums.PmsAttrExcelHeadersEnum;
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
 * 商品属性 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Service
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrMapper, PmsAttr> implements IPmsAttrService {


    /**
     * 新增商品属性
     * @param pmsAttrDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttr add(PmsAttrDto pmsAttrDto) {

        PmsAttr pmsAttr = new PmsAttr();
        pmsAttr.setAttrId(pmsAttrDto.getAttrId());
        pmsAttr.setAttrName(pmsAttrDto.getAttrName());
        pmsAttr.setSearchType(pmsAttrDto.getSearchType());
        pmsAttr.setIcon(pmsAttrDto.getIcon());
        pmsAttr.setValueSelect(pmsAttrDto.getValueSelect());
        pmsAttr.setAttrType(pmsAttrDto.getAttrType());
        pmsAttr.setEnable(pmsAttrDto.getEnable());
        pmsAttr.setCatelogId(pmsAttrDto.getCatelogId());
        pmsAttr.setShowDesc(pmsAttrDto.getShowDesc());
        pmsAttr.insert();
        return pmsAttr;
    }

    /**
     * 更新商品属性
     * @param pmsAttrDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttr update(PmsAttrDto pmsAttrDto) {
        PmsAttr pmsAttr = baseMapper.selectById(pmsAttrDto.getId());
        if (pmsAttr == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrDto.getAttrId(), pmsAttr::setAttrId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrDto.getAttrName(), pmsAttr::setAttrName);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrDto.getSearchType(), pmsAttr::setSearchType);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrDto.getIcon(), pmsAttr::setIcon);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrDto.getValueSelect(), pmsAttr::setValueSelect);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrDto.getAttrType(), pmsAttr::setAttrType);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrDto.getEnable(), pmsAttr::setEnable);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrDto.getCatelogId(), pmsAttr::setCatelogId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrDto.getShowDesc(), pmsAttr::setShowDesc);
        pmsAttr.updateById();
        return pmsAttr;
    }

    /**
     * 删除商品属性
     * @param pmsAttrDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttr delete(PmsAttrDto pmsAttrDto) {
        PmsAttr pmsAttr = baseMapper.selectById(pmsAttrDto.getId());
        if (pmsAttr == null) {
            return null;
        }
        pmsAttr.deleteById();

        return pmsAttr;
    }

    /**
     * 分页查询商品属性
     * @param pmsAttrDto
     * @param pmsAttrPage
     * @return
     */
    @Override
    public IPage<PmsAttr> queryPage(PmsAttrDto pmsAttrDto, Page<PmsAttr> pmsAttrPage) {

        return baseMapper.queryPage(pmsAttrPage, pmsAttrDto);
    }


    /**
     * 校验商品属性名称
     * @param pmsAttrDto
     * @return
     */
    @Override
    public boolean checkName(PmsAttrDto pmsAttrDto, boolean isAdd) {

        QueryWrapper<PmsAttr> pmsAttrQueryWrapper = new QueryWrapper<PmsAttr>();
        pmsAttrQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsAttrQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsAttrQueryWrapper.ne("id", pmsAttrDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsAttrQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有商品属性
     * @return
     */
    public List<PmsAttr> queryAll(PmsAttrDto pmsAttrDto) {
        return baseMapper.queryAll(pmsAttrDto);
    }

}
