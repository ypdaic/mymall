package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsSpuInfo;
import com.ypdaic.mymall.product.mapper.PmsSpuInfoMapper;
import com.ypdaic.mymall.product.service.IPmsSpuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsSpuInfoDto;
import com.ypdaic.mymall.product.enums.PmsSpuInfoExcelHeadersEnum;
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
 * @since 2020-06-07
 */
@Service
public class PmsSpuInfoServiceImpl extends ServiceImpl<PmsSpuInfoMapper, PmsSpuInfo> implements IPmsSpuInfoService {


    /**
     * 新增spu信息
     * @param pmsSpuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSpuInfo add(PmsSpuInfoDto pmsSpuInfoDto) {

        PmsSpuInfo pmsSpuInfo = new PmsSpuInfo();
        pmsSpuInfo.setSpuName(pmsSpuInfoDto.getSpuName());
        pmsSpuInfo.setSpuDescription(pmsSpuInfoDto.getSpuDescription());
        pmsSpuInfo.setCatalogId(pmsSpuInfoDto.getCatalogId());
        pmsSpuInfo.setBrandId(pmsSpuInfoDto.getBrandId());
        pmsSpuInfo.setWeight(pmsSpuInfoDto.getWeight());
        pmsSpuInfo.setPublishStatus(pmsSpuInfoDto.getPublishStatus());
        Date date6= new Date();
        pmsSpuInfo.setCreateTime(date6);
        Date date7= new Date();
        pmsSpuInfo.setUpdateTime(date7);
        pmsSpuInfo.insert();
        return pmsSpuInfo;
    }

    /**
     * 更新spu信息
     * @param pmsSpuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSpuInfo update(PmsSpuInfoDto pmsSpuInfoDto) {
        PmsSpuInfo pmsSpuInfo = baseMapper.selectById(pmsSpuInfoDto.getId());
        if (pmsSpuInfo == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuInfoDto.getSpuName(), pmsSpuInfo::setSpuName);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuInfoDto.getSpuDescription(), pmsSpuInfo::setSpuDescription);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuInfoDto.getCatalogId(), pmsSpuInfo::setCatalogId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuInfoDto.getBrandId(), pmsSpuInfo::setBrandId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuInfoDto.getWeight(), pmsSpuInfo::setWeight);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuInfoDto.getPublishStatus(), pmsSpuInfo::setPublishStatus);
        Date date7= new Date();
        pmsSpuInfo.setUpdateTime(date7);
        pmsSpuInfo.updateById();
        return pmsSpuInfo;
    }

    /**
     * 删除spu信息
     * @param pmsSpuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSpuInfo delete(PmsSpuInfoDto pmsSpuInfoDto) {
        PmsSpuInfo pmsSpuInfo = baseMapper.selectById(pmsSpuInfoDto.getId());
        if (pmsSpuInfo == null) {
            return null;
        }
        pmsSpuInfo.deleteById();

        return pmsSpuInfo;
    }

    /**
     * 分页查询spu信息
     * @param pmsSpuInfoDto
     * @param pmsSpuInfoPage
     * @return
     */
    @Override
    public IPage<PmsSpuInfo> queryPage(PmsSpuInfoDto pmsSpuInfoDto, Page<PmsSpuInfo> pmsSpuInfoPage) {

        return baseMapper.queryPage(pmsSpuInfoPage, pmsSpuInfoDto);
    }


    /**
     * 校验spu信息名称
     * @param pmsSpuInfoDto
     * @return
     */
    @Override
    public boolean checkName(PmsSpuInfoDto pmsSpuInfoDto, boolean isAdd) {

        QueryWrapper<PmsSpuInfo> pmsSpuInfoQueryWrapper = new QueryWrapper<PmsSpuInfo>();
        pmsSpuInfoQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsSpuInfoQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsSpuInfoQueryWrapper.ne("id", pmsSpuInfoDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsSpuInfoQueryWrapper);
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
    public List<PmsSpuInfo> queryAll(PmsSpuInfoDto pmsSpuInfoDto) {
        return baseMapper.queryAll(pmsSpuInfoDto);
    }

}
