package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.AttrGroup;
import com.ypdaic.mymall.product.mapper.AttrGroupMapper;
import com.ypdaic.mymall.product.service.IAttrGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.AttrGroupDto;
import com.ypdaic.mymall.product.enums.AttrGroupExcelHeadersEnum;
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
 * 属性分组 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroup> implements IAttrGroupService {


    /**
     * 新增属性分组
     * @param attrGroupDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AttrGroup add(AttrGroupDto attrGroupDto) {

        AttrGroup attrGroup = new AttrGroup();
        attrGroup.setAttrGroupId(attrGroupDto.getAttrGroupId());
        attrGroup.setAttrGroupName(attrGroupDto.getAttrGroupName());
        attrGroup.setSort(attrGroupDto.getSort());
        attrGroup.setDescript(attrGroupDto.getDescript());
        attrGroup.setIcon(attrGroupDto.getIcon());
        attrGroup.setCatelogId(attrGroupDto.getCatelogId());
        attrGroup.insert();
        return attrGroup;
    }

    /**
     * 更新属性分组
     * @param attrGroupDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AttrGroup update(AttrGroupDto attrGroupDto) {
        AttrGroup attrGroup = baseMapper.selectById(attrGroupDto.getId());
        if (attrGroup == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(attrGroupDto.getAttrGroupId(), attrGroup::setAttrGroupId);
        JavaUtils.INSTANCE.acceptIfNotNull(attrGroupDto.getAttrGroupName(), attrGroup::setAttrGroupName);
        JavaUtils.INSTANCE.acceptIfNotNull(attrGroupDto.getSort(), attrGroup::setSort);
        JavaUtils.INSTANCE.acceptIfNotNull(attrGroupDto.getDescript(), attrGroup::setDescript);
        JavaUtils.INSTANCE.acceptIfNotNull(attrGroupDto.getIcon(), attrGroup::setIcon);
        JavaUtils.INSTANCE.acceptIfNotNull(attrGroupDto.getCatelogId(), attrGroup::setCatelogId);
        attrGroup.updateById();
        return attrGroup;
    }

    /**
     * 删除属性分组
     * @param attrGroupDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AttrGroup delete(AttrGroupDto attrGroupDto) {
        AttrGroup attrGroup = baseMapper.selectById(attrGroupDto.getId());
        if (attrGroup == null) {
            return null;
        }
        attrGroup.deleteById();

        return attrGroup;
    }

    /**
     * 分页查询属性分组
     * @param attrGroupDto
     * @param attrGroupPage
     * @return
     */
    @Override
    public IPage<AttrGroup> queryPage(AttrGroupDto attrGroupDto, Page<AttrGroup> attrGroupPage) {

        return baseMapper.queryPage(attrGroupPage, attrGroupDto);
    }


    /**
     * 校验属性分组名称
     * @param attrGroupDto
     * @return
     */
    @Override
    public boolean checkName(AttrGroupDto attrGroupDto, boolean isAdd) {

        QueryWrapper<AttrGroup> attrGroupQueryWrapper = new QueryWrapper<AttrGroup>();
        attrGroupQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        attrGroupQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            attrGroupQueryWrapper.ne("id", attrGroupDto.getId());
        }

        Integer count = baseMapper.selectCount(attrGroupQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有属性分组
     * @return
     */
    public List<AttrGroup> queryAll(AttrGroupDto attrGroupDto) {
        return baseMapper.queryAll(attrGroupDto);
    }

}
