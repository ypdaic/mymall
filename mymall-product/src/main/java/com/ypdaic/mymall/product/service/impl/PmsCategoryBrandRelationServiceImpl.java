package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsCategoryBrandRelation;
import com.ypdaic.mymall.product.mapper.PmsCategoryBrandRelationMapper;
import com.ypdaic.mymall.product.service.IPmsCategoryBrandRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsCategoryBrandRelationDto;
import com.ypdaic.mymall.product.enums.PmsCategoryBrandRelationExcelHeadersEnum;
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
 * 品牌分类关联 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Service
public class PmsCategoryBrandRelationServiceImpl extends ServiceImpl<PmsCategoryBrandRelationMapper, PmsCategoryBrandRelation> implements IPmsCategoryBrandRelationService {


    /**
     * 新增品牌分类关联
     * @param pmsCategoryBrandRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsCategoryBrandRelation add(PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto) {

        PmsCategoryBrandRelation pmsCategoryBrandRelation = new PmsCategoryBrandRelation();
        pmsCategoryBrandRelation.setBrandId(pmsCategoryBrandRelationDto.getBrandId());
        pmsCategoryBrandRelation.setCatelogId(pmsCategoryBrandRelationDto.getCatelogId());
        pmsCategoryBrandRelation.setBrandName(pmsCategoryBrandRelationDto.getBrandName());
        pmsCategoryBrandRelation.setCatelogName(pmsCategoryBrandRelationDto.getCatelogName());
        pmsCategoryBrandRelation.insert();
        return pmsCategoryBrandRelation;
    }

    /**
     * 更新品牌分类关联
     * @param pmsCategoryBrandRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsCategoryBrandRelation update(PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto) {
        PmsCategoryBrandRelation pmsCategoryBrandRelation = baseMapper.selectById(pmsCategoryBrandRelationDto.getId());
        if (pmsCategoryBrandRelation == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsCategoryBrandRelationDto.getBrandId(), pmsCategoryBrandRelation::setBrandId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsCategoryBrandRelationDto.getCatelogId(), pmsCategoryBrandRelation::setCatelogId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsCategoryBrandRelationDto.getBrandName(), pmsCategoryBrandRelation::setBrandName);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsCategoryBrandRelationDto.getCatelogName(), pmsCategoryBrandRelation::setCatelogName);
        pmsCategoryBrandRelation.updateById();
        return pmsCategoryBrandRelation;
    }

    /**
     * 删除品牌分类关联
     * @param pmsCategoryBrandRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsCategoryBrandRelation delete(PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto) {
        PmsCategoryBrandRelation pmsCategoryBrandRelation = baseMapper.selectById(pmsCategoryBrandRelationDto.getId());
        if (pmsCategoryBrandRelation == null) {
            return null;
        }
        pmsCategoryBrandRelation.deleteById();

        return pmsCategoryBrandRelation;
    }

    /**
     * 分页查询品牌分类关联
     * @param pmsCategoryBrandRelationDto
     * @param pmsCategoryBrandRelationPage
     * @return
     */
    @Override
    public IPage<PmsCategoryBrandRelation> queryPage(PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto, Page<PmsCategoryBrandRelation> pmsCategoryBrandRelationPage) {

        return baseMapper.queryPage(pmsCategoryBrandRelationPage, pmsCategoryBrandRelationDto);
    }


    /**
     * 校验品牌分类关联名称
     * @param pmsCategoryBrandRelationDto
     * @return
     */
    @Override
    public boolean checkName(PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto, boolean isAdd) {

        QueryWrapper<PmsCategoryBrandRelation> pmsCategoryBrandRelationQueryWrapper = new QueryWrapper<PmsCategoryBrandRelation>();
        pmsCategoryBrandRelationQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsCategoryBrandRelationQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsCategoryBrandRelationQueryWrapper.ne("id", pmsCategoryBrandRelationDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsCategoryBrandRelationQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有品牌分类关联
     * @return
     */
    public List<PmsCategoryBrandRelation> queryAll(PmsCategoryBrandRelationDto pmsCategoryBrandRelationDto) {
        return baseMapper.queryAll(pmsCategoryBrandRelationDto);
    }

}
