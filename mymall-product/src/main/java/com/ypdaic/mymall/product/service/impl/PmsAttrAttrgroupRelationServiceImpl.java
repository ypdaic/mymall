package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsAttrAttrgroupRelation;
import com.ypdaic.mymall.product.mapper.PmsAttrAttrgroupRelationMapper;
import com.ypdaic.mymall.product.service.IPmsAttrAttrgroupRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsAttrAttrgroupRelationDto;
import com.ypdaic.mymall.product.enums.PmsAttrAttrgroupRelationExcelHeadersEnum;
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
 * 属性&属性分组关联 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Service
public class PmsAttrAttrgroupRelationServiceImpl extends ServiceImpl<PmsAttrAttrgroupRelationMapper, PmsAttrAttrgroupRelation> implements IPmsAttrAttrgroupRelationService {


    /**
     * 新增属性&属性分组关联
     * @param pmsAttrAttrgroupRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttrAttrgroupRelation add(PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto) {

        PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = new PmsAttrAttrgroupRelation();
        pmsAttrAttrgroupRelation.setAttrId(pmsAttrAttrgroupRelationDto.getAttrId());
        pmsAttrAttrgroupRelation.setAttrGroupId(pmsAttrAttrgroupRelationDto.getAttrGroupId());
        pmsAttrAttrgroupRelation.setAttrSort(pmsAttrAttrgroupRelationDto.getAttrSort());
        pmsAttrAttrgroupRelation.insert();
        return pmsAttrAttrgroupRelation;
    }

    /**
     * 更新属性&属性分组关联
     * @param pmsAttrAttrgroupRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttrAttrgroupRelation update(PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto) {
        PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = baseMapper.selectById(pmsAttrAttrgroupRelationDto.getId());
        if (pmsAttrAttrgroupRelation == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrAttrgroupRelationDto.getAttrId(), pmsAttrAttrgroupRelation::setAttrId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrAttrgroupRelationDto.getAttrGroupId(), pmsAttrAttrgroupRelation::setAttrGroupId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrAttrgroupRelationDto.getAttrSort(), pmsAttrAttrgroupRelation::setAttrSort);
        pmsAttrAttrgroupRelation.updateById();
        return pmsAttrAttrgroupRelation;
    }

    /**
     * 删除属性&属性分组关联
     * @param pmsAttrAttrgroupRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttrAttrgroupRelation delete(PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto) {
        PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = baseMapper.selectById(pmsAttrAttrgroupRelationDto.getId());
        if (pmsAttrAttrgroupRelation == null) {
            return null;
        }
        pmsAttrAttrgroupRelation.deleteById();

        return pmsAttrAttrgroupRelation;
    }

    /**
     * 分页查询属性&属性分组关联
     * @param pmsAttrAttrgroupRelationDto
     * @param pmsAttrAttrgroupRelationPage
     * @return
     */
    @Override
    public IPage<PmsAttrAttrgroupRelation> queryPage(PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto, Page<PmsAttrAttrgroupRelation> pmsAttrAttrgroupRelationPage) {

        return baseMapper.queryPage(pmsAttrAttrgroupRelationPage, pmsAttrAttrgroupRelationDto);
    }


    /**
     * 校验属性&属性分组关联名称
     * @param pmsAttrAttrgroupRelationDto
     * @return
     */
    @Override
    public boolean checkName(PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto, boolean isAdd) {

        QueryWrapper<PmsAttrAttrgroupRelation> pmsAttrAttrgroupRelationQueryWrapper = new QueryWrapper<PmsAttrAttrgroupRelation>();
        pmsAttrAttrgroupRelationQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsAttrAttrgroupRelationQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsAttrAttrgroupRelationQueryWrapper.ne("id", pmsAttrAttrgroupRelationDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsAttrAttrgroupRelationQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有属性&属性分组关联
     * @return
     */
    public List<PmsAttrAttrgroupRelation> queryAll(PmsAttrAttrgroupRelationDto pmsAttrAttrgroupRelationDto) {
        return baseMapper.queryAll(pmsAttrAttrgroupRelationDto);
    }

}
