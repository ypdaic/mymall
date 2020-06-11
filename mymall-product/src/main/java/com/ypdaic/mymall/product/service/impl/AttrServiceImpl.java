package com.ypdaic.mymall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.util.JavaUtils;
import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.product.entity.Attr;
import com.ypdaic.mymall.product.entity.AttrAttrgroupRelation;
import com.ypdaic.mymall.product.entity.AttrGroup;
import com.ypdaic.mymall.product.entity.Category;
import com.ypdaic.mymall.product.mapper.AttrAttrgroupRelationMapper;
import com.ypdaic.mymall.product.mapper.AttrGroupMapper;
import com.ypdaic.mymall.product.mapper.AttrMapper;
import com.ypdaic.mymall.product.mapper.CategoryMapper;
import com.ypdaic.mymall.product.service.IAttrService;
import com.ypdaic.mymall.product.service.ICategoryService;
import com.ypdaic.mymall.product.vo.AttrAttrgroupRelationDto;
import com.ypdaic.mymall.product.vo.AttrDto;
import com.ypdaic.mymall.product.vo.AttrRespVo;
import com.ypdaic.mymall.product.vo.AttrVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.ypdaic.mymall.common.constant.*;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements IAttrService {

    @Autowired
    AttrAttrgroupRelationMapper relationDao;

    @Autowired
    AttrGroupMapper attrGroupDao;

    @Autowired
    CategoryMapper categoryDao;

    @Autowired
    ICategoryService categoryService;

    /**
     * 新增商品属性
     * @param attrDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Attr add(AttrDto attrDto) {

        Attr attr = new Attr();
        attr.setAttrId(attrDto.getAttrId());
        attr.setAttrName(attrDto.getAttrName());
        attr.setSearchType(attrDto.getSearchType());
        attr.setIcon(attrDto.getIcon());
        attr.setValueSelect(attrDto.getValueSelect());
        attr.setAttrType(attrDto.getAttrType());
        attr.setEnable(attrDto.getEnable());
        attr.setCatelogId(attrDto.getCatelogId());
        attr.setShowDesc(attrDto.getShowDesc());
        attr.insert();
        return attr;
    }

    /**
     * 更新商品属性
     * @param attrDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Attr update(AttrDto attrDto) {
        Attr attr = baseMapper.selectById(attrDto.getId());
        if (attr == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(attrDto.getAttrId(), attr::setAttrId);
        JavaUtils.INSTANCE.acceptIfNotNull(attrDto.getAttrName(), attr::setAttrName);
        JavaUtils.INSTANCE.acceptIfNotNull(attrDto.getSearchType(), attr::setSearchType);
        JavaUtils.INSTANCE.acceptIfNotNull(attrDto.getIcon(), attr::setIcon);
        JavaUtils.INSTANCE.acceptIfNotNull(attrDto.getValueSelect(), attr::setValueSelect);
        JavaUtils.INSTANCE.acceptIfNotNull(attrDto.getAttrType(), attr::setAttrType);
        JavaUtils.INSTANCE.acceptIfNotNull(attrDto.getEnable(), attr::setEnable);
        JavaUtils.INSTANCE.acceptIfNotNull(attrDto.getCatelogId(), attr::setCatelogId);
        JavaUtils.INSTANCE.acceptIfNotNull(attrDto.getShowDesc(), attr::setShowDesc);
        attr.updateById();
        return attr;
    }

    /**
     * 删除商品属性
     * @param attrDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Attr delete(AttrDto attrDto) {
        Attr attr = baseMapper.selectById(attrDto.getId());
        if (attr == null) {
            return null;
        }
        attr.deleteById();

        return attr;
    }

    /**
     * 分页查询商品属性
     * @param attrDto
     * @param attrPage
     * @return
     */
    @Override
    public IPage<Attr> queryPage(AttrDto attrDto, Page<Attr> attrPage) {

        return baseMapper.queryPage(attrPage, attrDto);
    }


    /**
     * 校验商品属性名称
     * @param attrDto
     * @return
     */
    @Override
    public boolean checkName(AttrDto attrDto, boolean isAdd) {

        QueryWrapper<Attr> attrQueryWrapper = new QueryWrapper<Attr>();
        attrQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        attrQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            attrQueryWrapper.ne("id", attrDto.getId());
        }

        Integer count = baseMapper.selectCount(attrQueryWrapper);
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
    public List<Attr> queryAll(AttrDto attrDto) {
        return baseMapper.queryAll(attrDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Attr> page = this.page(
                new Query<Attr>().getPage(params),
                new QueryWrapper<Attr>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        Attr attrEntity = new Attr();
//        attrEntity.setAttrName(attr.getAttrName());
        BeanUtils.copyProperties(attr,attrEntity);
        //1、保存基本数据
        this.save(attrEntity);
        //2、保存关联关系
        if(attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() && attr.getAttrGroupId()!=null){
            AttrAttrgroupRelation relationEntity = new AttrAttrgroupRelation();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attrEntity.getAttrId());
            relationDao.insert(relationEntity);
        }


    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type) {
        QueryWrapper<Attr> queryWrapper = new QueryWrapper<Attr>().eq("attr_type","base".equalsIgnoreCase(type)?ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode():ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());

        if(catelogId != 0){
            queryWrapper.eq("catelog_id",catelogId);
        }

        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            //attr_id  attr_name
            queryWrapper.and((wrapper)->{
                return wrapper.eq("attr_id",key).or().like("attr_name",key);
            });
        }

        IPage<Attr> page = this.page(
                new Query<Attr>().getPage(params),
                queryWrapper
        );

        PageUtils pageUtils = new PageUtils(page);
        List<Attr> records = page.getRecords();
        List<AttrRespVo> respVos = records.stream().map((attrEntity) -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(attrEntity, attrRespVo);

            //1、设置分类和分组的名字
            if("base".equalsIgnoreCase(type)){
                AttrAttrgroupRelation attrId = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attrEntity.getAttrId()));
                if (attrId != null && attrId.getAttrGroupId()!=null) {
                    AttrGroup attrGroupEntity = attrGroupDao.selectById(attrId.getAttrGroupId());
                    attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }

            }


            Category categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());
            if (categoryEntity != null) {
                attrRespVo.setCatelogName(categoryEntity.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());

        pageUtils.setList(respVos);
        return pageUtils;
    }

    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
        AttrRespVo respVo = new AttrRespVo();
        Attr attrEntity = this.getById(attrId);
        BeanUtils.copyProperties(attrEntity,respVo);



        if(attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
            //1、设置分组信息
            AttrAttrgroupRelation attrgroupRelation = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attrId));
            if(attrgroupRelation!=null){
                respVo.setAttrGroupId(attrgroupRelation.getAttrGroupId());
                AttrGroup attrGroupEntity = attrGroupDao.selectById(attrgroupRelation.getAttrGroupId());
                if(attrGroupEntity!=null){
                    respVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
        }


        //2、设置分类信息
        Long catelogId = attrEntity.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);
        respVo.setCatelogPath(catelogPath);

        Category categoryEntity = categoryDao.selectById(catelogId);
        if(categoryEntity!=null){
            respVo.setCatelogName(categoryEntity.getName());
        }


        return respVo;
    }

    @Transactional
    @Override
    public void updateAttr(AttrVo attr) {
        Attr attrEntity = new Attr();
        BeanUtils.copyProperties(attr,attrEntity);
        this.updateById(attrEntity);

        if(attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
            //1、修改分组关联
            AttrAttrgroupRelation relationEntity = new AttrAttrgroupRelation();

            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());

            Integer count = relationDao.selectCount(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attr.getAttrId()));
            if(count>0){

                relationDao.update(relationEntity,new UpdateWrapper<AttrAttrgroupRelation>().eq("attr_id",attr.getAttrId()));

            }else{
                relationDao.insert(relationEntity);
            }
        }


    }

    /**
     * 根据分组id查找关联的所有基本属性
     * @param attrgroupId
     * @return
     */
    @Override
    public List<Attr> getRelationAttr(Long attrgroupId) {
        List<AttrAttrgroupRelation> entities = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_group_id", attrgroupId));

        List<Long> attrIds = entities.stream().map((attr) -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        if(attrIds == null || attrIds.size() == 0){
            return null;
        }
        Collection<Attr> attrEntities = this.listByIds(attrIds);
        return (List<Attr>) attrEntities;
    }

    @Override
    public void deleteRelation(AttrAttrgroupRelationDto[] vos) {
        //relationDao.delete(new QueryWrapper<>().eq("attr_id",1L).eq("attr_group_id",1L));
        //
        List<AttrAttrgroupRelation> entities = Arrays.asList(vos).stream().map((item) -> {
            AttrAttrgroupRelation relationEntity = new AttrAttrgroupRelation();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        relationDao.deleteBatchRelation(entities);
    }

    /**
     * 获取当前分组没有关联的所有属性
     * @param params
     * @param attrgroupId
     * @return
     */
    @Override
    public PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId) {
        //1、当前分组只能关联自己所属的分类里面的所有属性
        AttrGroup attrGroupEntity = attrGroupDao.selectById(attrgroupId);
        Long catelogId = attrGroupEntity.getCatelogId();
        //2、当前分组只能关联别的分组没有引用的属性
        //2.1)、当前分类下的其他分组
        List<AttrGroup> group = attrGroupDao.selectList(new QueryWrapper<AttrGroup>().eq("catelog_id", catelogId));
        List<Long> collect = group.stream().map(item -> {
            return item.getAttrGroupId();
        }).collect(Collectors.toList());

        //2.2)、这些分组关联的属性，查询分类下所有的属性分组
        List<AttrAttrgroupRelation> groupId = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelation>().in("attr_group_id", collect));
        List<Long> attrIds = groupId.stream().map(item -> {
            return item.getAttrId();
        }).collect(Collectors.toList());

        //2.3)、从当前分类的所有属性中移除这些属性；也就是只查询还未绑定分组的基本属性
        QueryWrapper<Attr> wrapper = new QueryWrapper<Attr>().eq("catelog_id", catelogId).eq("attr_type",ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());
        wrapper.eq("enable", 1);
        if(attrIds!=null && attrIds.size()>0){
            wrapper.notIn("attr_id", attrIds);
        }
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            wrapper.and((w)->{
               return w.eq("attr_id",key).or().like("attr_name",key);

            });
        }
        IPage<Attr> page = this.page(new Query<Attr>().getPage(params), wrapper);

        PageUtils pageUtils = new PageUtils(page);

        return pageUtils;
    }

}
