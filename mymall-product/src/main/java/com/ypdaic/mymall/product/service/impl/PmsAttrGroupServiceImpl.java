package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsAttrGroup;
import com.ypdaic.mymall.product.mapper.PmsAttrGroupMapper;
import com.ypdaic.mymall.product.service.IPmsAttrGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsAttrGroupDto;
import com.ypdaic.mymall.product.enums.PmsAttrGroupExcelHeadersEnum;
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
 * @since 2020-06-07
 */
@Service
public class PmsAttrGroupServiceImpl extends ServiceImpl<PmsAttrGroupMapper, PmsAttrGroup> implements IPmsAttrGroupService {


    /**
     * 新增属性分组
     * @param pmsAttrGroupDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttrGroup add(PmsAttrGroupDto pmsAttrGroupDto) {

        PmsAttrGroup pmsAttrGroup = new PmsAttrGroup();
        pmsAttrGroup.setAttrGroupId(pmsAttrGroupDto.getAttrGroupId());
        pmsAttrGroup.setAttrGroupName(pmsAttrGroupDto.getAttrGroupName());
        pmsAttrGroup.setSort(pmsAttrGroupDto.getSort());
        pmsAttrGroup.setDescript(pmsAttrGroupDto.getDescript());
        pmsAttrGroup.setIcon(pmsAttrGroupDto.getIcon());
        pmsAttrGroup.setCatelogId(pmsAttrGroupDto.getCatelogId());
        pmsAttrGroup.insert();
        return pmsAttrGroup;
    }

    /**
     * 更新属性分组
     * @param pmsAttrGroupDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttrGroup update(PmsAttrGroupDto pmsAttrGroupDto) {
        PmsAttrGroup pmsAttrGroup = baseMapper.selectById(pmsAttrGroupDto.getId());
        if (pmsAttrGroup == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrGroupDto.getAttrGroupId(), pmsAttrGroup::setAttrGroupId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrGroupDto.getAttrGroupName(), pmsAttrGroup::setAttrGroupName);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrGroupDto.getSort(), pmsAttrGroup::setSort);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrGroupDto.getDescript(), pmsAttrGroup::setDescript);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrGroupDto.getIcon(), pmsAttrGroup::setIcon);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsAttrGroupDto.getCatelogId(), pmsAttrGroup::setCatelogId);
        pmsAttrGroup.updateById();
        return pmsAttrGroup;
    }

    /**
     * 删除属性分组
     * @param pmsAttrGroupDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttrGroup delete(PmsAttrGroupDto pmsAttrGroupDto) {
        PmsAttrGroup pmsAttrGroup = baseMapper.selectById(pmsAttrGroupDto.getId());
        if (pmsAttrGroup == null) {
            return null;
        }
        pmsAttrGroup.deleteById();

        return pmsAttrGroup;
    }

    /**
     * 分页查询属性分组
     * @param pmsAttrGroupDto
     * @param pmsAttrGroupPage
     * @return
     */
    @Override
    public IPage<PmsAttrGroup> queryPage(PmsAttrGroupDto pmsAttrGroupDto, Page<PmsAttrGroup> pmsAttrGroupPage) {

        return baseMapper.queryPage(pmsAttrGroupPage, pmsAttrGroupDto);
    }


    /**
     * 校验属性分组名称
     * @param pmsAttrGroupDto
     * @return
     */
    @Override
    public boolean checkName(PmsAttrGroupDto pmsAttrGroupDto, boolean isAdd) {

        QueryWrapper<PmsAttrGroup> pmsAttrGroupQueryWrapper = new QueryWrapper<PmsAttrGroup>();
        pmsAttrGroupQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsAttrGroupQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsAttrGroupQueryWrapper.ne("id", pmsAttrGroupDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsAttrGroupQueryWrapper);
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
    public List<PmsAttrGroup> queryAll(PmsAttrGroupDto pmsAttrGroupDto) {
        return baseMapper.queryAll(pmsAttrGroupDto);
    }

}
