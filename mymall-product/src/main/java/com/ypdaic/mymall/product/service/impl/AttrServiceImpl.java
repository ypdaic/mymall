package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.Attr;
import com.ypdaic.mymall.product.mapper.AttrMapper;
import com.ypdaic.mymall.product.service.IAttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.AttrDto;
import com.ypdaic.mymall.product.enums.AttrExcelHeadersEnum;
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
 * @since 2020-06-08
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements IAttrService {


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

}
