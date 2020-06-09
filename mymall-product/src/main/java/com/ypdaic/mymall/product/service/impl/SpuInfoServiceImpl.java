package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.product.entity.SpuInfo;
import com.ypdaic.mymall.product.mapper.SpuInfoMapper;
import com.ypdaic.mymall.product.service.ISpuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.SpuInfoDto;
import com.ypdaic.mymall.product.enums.SpuInfoExcelHeadersEnum;
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
import java.util.Date;
import java.util.Date;

/**
 * <p>
 * spu信息 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo> implements ISpuInfoService {


    /**
     * 新增spu信息
     * @param spuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuInfo add(SpuInfoDto spuInfoDto) {

        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setSpuName(spuInfoDto.getSpuName());
        spuInfo.setSpuDescription(spuInfoDto.getSpuDescription());
        spuInfo.setCatalogId(spuInfoDto.getCatalogId());
        spuInfo.setBrandId(spuInfoDto.getBrandId());
        spuInfo.setWeight(spuInfoDto.getWeight());
        spuInfo.setPublishStatus(spuInfoDto.getPublishStatus());
        Date date6= new Date();
        spuInfo.setCreateTime(date6);
        Date date7= new Date();
        spuInfo.setUpdateTime(date7);
        spuInfo.insert();
        return spuInfo;
    }

    /**
     * 更新spu信息
     * @param spuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuInfo update(SpuInfoDto spuInfoDto) {
        SpuInfo spuInfo = baseMapper.selectById(spuInfoDto.getId());
        if (spuInfo == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDto.getSpuName(), spuInfo::setSpuName);
        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDto.getSpuDescription(), spuInfo::setSpuDescription);
        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDto.getCatalogId(), spuInfo::setCatalogId);
        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDto.getBrandId(), spuInfo::setBrandId);
        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDto.getWeight(), spuInfo::setWeight);
        JavaUtils.INSTANCE.acceptIfNotNull(spuInfoDto.getPublishStatus(), spuInfo::setPublishStatus);
        Date date7= new Date();
        spuInfo.setUpdateTime(date7);
        spuInfo.updateById();
        return spuInfo;
    }

    /**
     * 删除spu信息
     * @param spuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuInfo delete(SpuInfoDto spuInfoDto) {
        SpuInfo spuInfo = baseMapper.selectById(spuInfoDto.getId());
        if (spuInfo == null) {
            return null;
        }
        spuInfo.deleteById();

        return spuInfo;
    }

    /**
     * 分页查询spu信息
     * @param spuInfoDto
     * @param spuInfoPage
     * @return
     */
    @Override
    public IPage<SpuInfo> queryPage(SpuInfoDto spuInfoDto, Page<SpuInfo> spuInfoPage) {

        return baseMapper.queryPage(spuInfoPage, spuInfoDto);
    }


    /**
     * 校验spu信息名称
     * @param spuInfoDto
     * @return
     */
    @Override
    public boolean checkName(SpuInfoDto spuInfoDto, boolean isAdd) {

        QueryWrapper<SpuInfo> spuInfoQueryWrapper = new QueryWrapper<SpuInfo>();
        spuInfoQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        spuInfoQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            spuInfoQueryWrapper.ne("id", spuInfoDto.getId());
        }

        Integer count = baseMapper.selectCount(spuInfoQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有spu信息
     * @return
     */
    public List<SpuInfo> queryAll(SpuInfoDto spuInfoDto) {
        return baseMapper.queryAll(spuInfoDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoDesc> page = this.page(
                new Query<SpuInfoDesc>().getPage(params),
                new QueryWrapper<SpuInfoDesc>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSpuInfoDesc(SpuInfoDescEntity descEntity) {
        this.baseMapper.insert(descEntity);
    }

}
