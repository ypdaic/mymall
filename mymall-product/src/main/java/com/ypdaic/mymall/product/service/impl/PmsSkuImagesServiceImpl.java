package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsSkuImages;
import com.ypdaic.mymall.product.mapper.PmsSkuImagesMapper;
import com.ypdaic.mymall.product.service.IPmsSkuImagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsSkuImagesDto;
import com.ypdaic.mymall.product.enums.PmsSkuImagesExcelHeadersEnum;
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
 * sku图片 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Service
public class PmsSkuImagesServiceImpl extends ServiceImpl<PmsSkuImagesMapper, PmsSkuImages> implements IPmsSkuImagesService {


    /**
     * 新增sku图片
     * @param pmsSkuImagesDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSkuImages add(PmsSkuImagesDto pmsSkuImagesDto) {

        PmsSkuImages pmsSkuImages = new PmsSkuImages();
        pmsSkuImages.setSkuId(pmsSkuImagesDto.getSkuId());
        pmsSkuImages.setImgUrl(pmsSkuImagesDto.getImgUrl());
        pmsSkuImages.setImgSort(pmsSkuImagesDto.getImgSort());
        pmsSkuImages.setDefaultImg(pmsSkuImagesDto.getDefaultImg());
        pmsSkuImages.insert();
        return pmsSkuImages;
    }

    /**
     * 更新sku图片
     * @param pmsSkuImagesDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSkuImages update(PmsSkuImagesDto pmsSkuImagesDto) {
        PmsSkuImages pmsSkuImages = baseMapper.selectById(pmsSkuImagesDto.getId());
        if (pmsSkuImages == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuImagesDto.getSkuId(), pmsSkuImages::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuImagesDto.getImgUrl(), pmsSkuImages::setImgUrl);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuImagesDto.getImgSort(), pmsSkuImages::setImgSort);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSkuImagesDto.getDefaultImg(), pmsSkuImages::setDefaultImg);
        pmsSkuImages.updateById();
        return pmsSkuImages;
    }

    /**
     * 删除sku图片
     * @param pmsSkuImagesDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSkuImages delete(PmsSkuImagesDto pmsSkuImagesDto) {
        PmsSkuImages pmsSkuImages = baseMapper.selectById(pmsSkuImagesDto.getId());
        if (pmsSkuImages == null) {
            return null;
        }
        pmsSkuImages.deleteById();

        return pmsSkuImages;
    }

    /**
     * 分页查询sku图片
     * @param pmsSkuImagesDto
     * @param pmsSkuImagesPage
     * @return
     */
    @Override
    public IPage<PmsSkuImages> queryPage(PmsSkuImagesDto pmsSkuImagesDto, Page<PmsSkuImages> pmsSkuImagesPage) {

        return baseMapper.queryPage(pmsSkuImagesPage, pmsSkuImagesDto);
    }


    /**
     * 校验sku图片名称
     * @param pmsSkuImagesDto
     * @return
     */
    @Override
    public boolean checkName(PmsSkuImagesDto pmsSkuImagesDto, boolean isAdd) {

        QueryWrapper<PmsSkuImages> pmsSkuImagesQueryWrapper = new QueryWrapper<PmsSkuImages>();
        pmsSkuImagesQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsSkuImagesQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsSkuImagesQueryWrapper.ne("id", pmsSkuImagesDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsSkuImagesQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有sku图片
     * @return
     */
    public List<PmsSkuImages> queryAll(PmsSkuImagesDto pmsSkuImagesDto) {
        return baseMapper.queryAll(pmsSkuImagesDto);
    }

}
