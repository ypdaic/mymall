package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.product.entity.Attr;
import com.ypdaic.mymall.product.entity.AttrGroup;
import com.ypdaic.mymall.product.mapper.AttrGroupMapper;
import com.ypdaic.mymall.product.service.IAttrGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.service.IAttrService;
import com.ypdaic.mymall.product.vo.AttrAttrgroupRelationDto;
import com.ypdaic.mymall.product.vo.AttrGroupDto;
import com.ypdaic.mymall.product.enums.AttrGroupExcelHeadersEnum;
import com.ypdaic.mymall.product.vo.AttrGroupWithAttrsDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypdaic.mymall.common.util.ExcelUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.util.JavaUtils;
import com.ypdaic.mymall.common.constant.*;

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

    @Autowired
    IAttrService attrService;

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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroup> page = this.page(
                new Query<AttrGroup>().getPage(params),
                new QueryWrapper<AttrGroup>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        String key = (String) params.get("key");
        //select * from pms_attr_group where catelog_id=? and (attr_group_id=key or attr_group_name like %key%)
        QueryWrapper<AttrGroup> wrapper = new QueryWrapper<AttrGroup>();
        if(!StringUtils.isEmpty(key)){
            wrapper.and((obj)->{
                return obj.eq("attr_group_id",key).or().like("attr_group_name",key);
            });
        }

        if( catelogId == 0){
            IPage<AttrGroup> page = this.page(new Query<AttrGroup>().getPage(params),
                    wrapper);
            return new PageUtils(page);
        }else {
            wrapper.eq("catelog_id",catelogId);
            IPage<AttrGroup> page = this.page(new Query<AttrGroup>().getPage(params),
                    wrapper);
            return new PageUtils(page);
        }

    }

    /**
     * 根据分类id查出所有的分组以及这些组里面的属性
     * @param catelogId
     * @return
     */
    @Override
    public List<AttrGroupWithAttrsDto> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        //com.atguigu.gulimall.product.vo
        //1、查询分组信息
        List<AttrGroup> attrGroupEntities = this.list(new QueryWrapper<AttrGroup>().eq("catelog_id", catelogId));

        //2、查询所有属性
        List<AttrGroupWithAttrsDto> collect = attrGroupEntities.stream().map(group -> {
            AttrGroupWithAttrsDto attrsVo = new AttrGroupWithAttrsDto();
            BeanUtils.copyProperties(group,attrsVo);
            List<Attr> attrs = attrService.getRelationAttr(attrsVo.getAttrGroupId());
            attrsVo.setAttrs(attrs);
            return attrsVo;
        }).collect(Collectors.toList());

        return collect;


    }

}
