package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsSkuInfo;
import com.ypdaic.mymall.product.mapper.PmsSkuInfoMapper;
import com.ypdaic.mymall.product.service.IPmsSkuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsSkuInfoDto;
import com.ypdaic.mymall.product.enums.PmsSkuInfoExcelHeadersEnum;
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
 * sku信息 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Service
public class PmsSkuInfoServiceImpl extends ServiceImpl<PmsSkuInfoMapper, PmsSkuInfo> implements IPmsSkuInfoService {


    /**
     * 新增sku信息
     * @param pmsSkuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSkuInfo add(PmsSkuInfoDto pmsSkuInfoDto) {

        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setSkuId(pmsSkuInfoDto.getSkuId());
        pmsSkuInfo.setSpuId(pmsSkuInfoDto.getSpuId());
        pmsSkuInfo.setSkuName(pmsSkuInfoDto.getSkuName());
        pmsSkuInfo.setSkuDesc(pmsSkuInfoDto.getSkuDesc());
        pmsSkuInfo.setCatalogId(pmsSkuInfoDto.getCatalogId());
        pmsSkuInfo.setBrandId(pmsSkuInfoDto.getBrandId());
        pmsSkuInfo.setSkuDefaultImg(pmsSkuInfoDto.getSkuDefaultImg());
        pmsSkuInfo.setSkuTitle(pmsSkuInfoDto.getSkuTitle());
        pmsSkuInfo.setSkuSubtitle(pmsSkuInfoDto.getSkuSubtitle());
        pmsSkuInfo.setPrice(pmsSkuInfoDto.getPrice());
        pmsSkuInfo.setSaleCount(pmsSkuInfoDto.getSaleCount());
        pmsSkuInfo.insert();
        return pmsSkuInfo;
    }

    /**
     * 更新sku信息
     * @param pmsSkuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSkuInfo update(PmsSkuInfoDto pmsSkuInfoDto) {
        PmsSkuInfo pmsSkuInfo = baseMapper.selectById(pmsSkuInfoDto.getId());
        if (pmsSkuInfo == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuInfoDto.getSkuId(), pmsSkuInfo::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuInfoDto.getSpuId(), pmsSkuInfo::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuInfoDto.getSkuName(), pmsSkuInfo::setSkuName);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuInfoDto.getSkuDesc(), pmsSkuInfo::setSkuDesc);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuInfoDto.getCatalogId(), pmsSkuInfo::setCatalogId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuInfoDto.getBrandId(), pmsSkuInfo::setBrandId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuInfoDto.getSkuDefaultImg(), pmsSkuInfo::setSkuDefaultImg);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuInfoDto.getSkuTitle(), pmsSkuInfo::setSkuTitle);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuInfoDto.getSkuSubtitle(), pmsSkuInfo::setSkuSubtitle);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuInfoDto.getPrice(), pmsSkuInfo::setPrice);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuInfoDto.getSaleCount(), pmsSkuInfo::setSaleCount);
        pmsSkuInfo.updateById();
        return pmsSkuInfo;
    }

    /**
     * 删除sku信息
     * @param pmsSkuInfoDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSkuInfo delete(PmsSkuInfoDto pmsSkuInfoDto) {
        PmsSkuInfo pmsSkuInfo = baseMapper.selectById(pmsSkuInfoDto.getId());
        if (pmsSkuInfo == null) {
            return null;
        }
        pmsSkuInfo.deleteById();

        return pmsSkuInfo;
    }

    /**
     * 分页查询sku信息
     * @param pmsSkuInfoDto
     * @param pmsSkuInfoPage
     * @return
     */
    @Override
    public IPage<PmsSkuInfo> queryPage(PmsSkuInfoDto pmsSkuInfoDto, Page<PmsSkuInfo> pmsSkuInfoPage) {

        return baseMapper.queryPage(pmsSkuInfoPage, pmsSkuInfoDto);
    }


    /**
     * 校验sku信息名称
     * @param pmsSkuInfoDto
     * @return
     */
    @Override
    public boolean checkName(PmsSkuInfoDto pmsSkuInfoDto, boolean isAdd) {

        QueryWrapper<PmsSkuInfo> pmsSkuInfoQueryWrapper = new QueryWrapper<PmsSkuInfo>();
        pmsSkuInfoQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsSkuInfoQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsSkuInfoQueryWrapper.ne("id", pmsSkuInfoDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsSkuInfoQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有sku信息
     * @return
     */
    public List<PmsSkuInfo> queryAll(PmsSkuInfoDto pmsSkuInfoDto) {
        return baseMapper.queryAll(pmsSkuInfoDto);
    }

}
