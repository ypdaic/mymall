package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.PmsSpuImages;
import com.ypdaic.mymall.product.mapper.PmsSpuImagesMapper;
import com.ypdaic.mymall.product.service.IPmsSpuImagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.PmsSpuImagesDto;
import com.ypdaic.mymall.product.enums.PmsSpuImagesExcelHeadersEnum;
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
 * spu图片 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
@Service
public class PmsSpuImagesServiceImpl extends ServiceImpl<PmsSpuImagesMapper, PmsSpuImages> implements IPmsSpuImagesService {


    /**
     * 新增spu图片
     * @param pmsSpuImagesDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSpuImages add(PmsSpuImagesDto pmsSpuImagesDto) {

        PmsSpuImages pmsSpuImages = new PmsSpuImages();
        pmsSpuImages.setSpuId(pmsSpuImagesDto.getSpuId());
        pmsSpuImages.setImgName(pmsSpuImagesDto.getImgName());
        pmsSpuImages.setImgUrl(pmsSpuImagesDto.getImgUrl());
        pmsSpuImages.setImgSort(pmsSpuImagesDto.getImgSort());
        pmsSpuImages.setDefaultImg(pmsSpuImagesDto.getDefaultImg());
        pmsSpuImages.insert();
        return pmsSpuImages;
    }

    /**
     * 更新spu图片
     * @param pmsSpuImagesDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSpuImages update(PmsSpuImagesDto pmsSpuImagesDto) {
        PmsSpuImages pmsSpuImages = baseMapper.selectById(pmsSpuImagesDto.getId());
        if (pmsSpuImages == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuImagesDto.getSpuId(), pmsSpuImages::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuImagesDto.getImgName(), pmsSpuImages::setImgName);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuImagesDto.getImgUrl(), pmsSpuImages::setImgUrl);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuImagesDto.getImgSort(), pmsSpuImages::setImgSort);
        JavaUtils.INSTANCE.acceptIfNotNull(pmsSpuImagesDto.getDefaultImg(), pmsSpuImages::setDefaultImg);
        pmsSpuImages.updateById();
        return pmsSpuImages;
    }

    /**
     * 删除spu图片
     * @param pmsSpuImagesDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsSpuImages delete(PmsSpuImagesDto pmsSpuImagesDto) {
        PmsSpuImages pmsSpuImages = baseMapper.selectById(pmsSpuImagesDto.getId());
        if (pmsSpuImages == null) {
            return null;
        }
        pmsSpuImages.deleteById();

        return pmsSpuImages;
    }

    /**
     * 分页查询spu图片
     * @param pmsSpuImagesDto
     * @param pmsSpuImagesPage
     * @return
     */
    @Override
    public IPage<PmsSpuImages> queryPage(PmsSpuImagesDto pmsSpuImagesDto, Page<PmsSpuImages> pmsSpuImagesPage) {

        return baseMapper.queryPage(pmsSpuImagesPage, pmsSpuImagesDto);
    }


    /**
     * 校验spu图片名称
     * @param pmsSpuImagesDto
     * @return
     */
    @Override
    public boolean checkName(PmsSpuImagesDto pmsSpuImagesDto, boolean isAdd) {

        QueryWrapper<PmsSpuImages> pmsSpuImagesQueryWrapper = new QueryWrapper<PmsSpuImages>();
        pmsSpuImagesQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        pmsSpuImagesQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            pmsSpuImagesQueryWrapper.ne("id", pmsSpuImagesDto.getId());
        }

        Integer count = baseMapper.selectCount(pmsSpuImagesQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有spu图片
     * @return
     */
    public List<PmsSpuImages> queryAll(PmsSpuImagesDto pmsSpuImagesDto) {
        return baseMapper.queryAll(pmsSpuImagesDto);
    }

}
