package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.AttrAttrgroupRelation;
import com.ypdaic.mymall.product.mapper.AttrAttrgroupRelationMapper;
import com.ypdaic.mymall.product.service.IAttrAttrgroupRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.AttrAttrgroupRelationDto;
import com.ypdaic.mymall.product.enums.AttrAttrgroupRelationExcelHeadersEnum;
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
 * @since 2020-06-08
 */
@Service
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationMapper, AttrAttrgroupRelation> implements IAttrAttrgroupRelationService {


    /**
     * 新增属性&属性分组关联
     * @param attrAttrgroupRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AttrAttrgroupRelation add(AttrAttrgroupRelationDto attrAttrgroupRelationDto) {

        AttrAttrgroupRelation attrAttrgroupRelation = new AttrAttrgroupRelation();
        attrAttrgroupRelation.setAttrId(attrAttrgroupRelationDto.getAttrId());
        attrAttrgroupRelation.setAttrGroupId(attrAttrgroupRelationDto.getAttrGroupId());
        attrAttrgroupRelation.setAttrSort(attrAttrgroupRelationDto.getAttrSort());
        attrAttrgroupRelation.insert();
        return attrAttrgroupRelation;
    }

    /**
     * 更新属性&属性分组关联
     * @param attrAttrgroupRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AttrAttrgroupRelation update(AttrAttrgroupRelationDto attrAttrgroupRelationDto) {
        AttrAttrgroupRelation attrAttrgroupRelation = baseMapper.selectById(attrAttrgroupRelationDto.getId());
        if (attrAttrgroupRelation == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(attrAttrgroupRelationDto.getAttrId(), attrAttrgroupRelation::setAttrId);
        JavaUtils.INSTANCE.acceptIfNotNull(attrAttrgroupRelationDto.getAttrGroupId(), attrAttrgroupRelation::setAttrGroupId);
        JavaUtils.INSTANCE.acceptIfNotNull(attrAttrgroupRelationDto.getAttrSort(), attrAttrgroupRelation::setAttrSort);
        attrAttrgroupRelation.updateById();
        return attrAttrgroupRelation;
    }

    /**
     * 删除属性&属性分组关联
     * @param attrAttrgroupRelationDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AttrAttrgroupRelation delete(AttrAttrgroupRelationDto attrAttrgroupRelationDto) {
        AttrAttrgroupRelation attrAttrgroupRelation = baseMapper.selectById(attrAttrgroupRelationDto.getId());
        if (attrAttrgroupRelation == null) {
            return null;
        }
        attrAttrgroupRelation.deleteById();

        return attrAttrgroupRelation;
    }

    /**
     * 分页查询属性&属性分组关联
     * @param attrAttrgroupRelationDto
     * @param attrAttrgroupRelationPage
     * @return
     */
    @Override
    public IPage<AttrAttrgroupRelation> queryPage(AttrAttrgroupRelationDto attrAttrgroupRelationDto, Page<AttrAttrgroupRelation> attrAttrgroupRelationPage) {

        return baseMapper.queryPage(attrAttrgroupRelationPage, attrAttrgroupRelationDto);
    }


    /**
     * 校验属性&属性分组关联名称
     * @param attrAttrgroupRelationDto
     * @return
     */
    @Override
    public boolean checkName(AttrAttrgroupRelationDto attrAttrgroupRelationDto, boolean isAdd) {

        QueryWrapper<AttrAttrgroupRelation> attrAttrgroupRelationQueryWrapper = new QueryWrapper<AttrAttrgroupRelation>();
        attrAttrgroupRelationQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        attrAttrgroupRelationQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            attrAttrgroupRelationQueryWrapper.ne("id", attrAttrgroupRelationDto.getId());
        }

        Integer count = baseMapper.selectCount(attrAttrgroupRelationQueryWrapper);
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
    public List<AttrAttrgroupRelation> queryAll(AttrAttrgroupRelationDto attrAttrgroupRelationDto) {
        return baseMapper.queryAll(attrAttrgroupRelationDto);
    }

}
