package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.product.entity.SpuImages;
import com.ypdaic.mymall.product.mapper.SpuImagesMapper;
import com.ypdaic.mymall.product.service.ISpuImagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.SpuImagesDto;
import com.ypdaic.mymall.product.enums.SpuImagesExcelHeadersEnum;
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
 * @since 2020-06-08
 */
@Service
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesMapper, SpuImages> implements ISpuImagesService {


    /**
     * 新增spu图片
     * @param spuImagesDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuImages add(SpuImagesDto spuImagesDto) {

        SpuImages spuImages = new SpuImages();
        spuImages.setSpuId(spuImagesDto.getSpuId());
        spuImages.setImgName(spuImagesDto.getImgName());
        spuImages.setImgUrl(spuImagesDto.getImgUrl());
        spuImages.setImgSort(spuImagesDto.getImgSort());
        spuImages.setDefaultImg(spuImagesDto.getDefaultImg());
        spuImages.insert();
        return spuImages;
    }

    /**
     * 更新spu图片
     * @param spuImagesDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuImages update(SpuImagesDto spuImagesDto) {
        SpuImages spuImages = baseMapper.selectById(spuImagesDto.getId());
        if (spuImages == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(spuImagesDto.getSpuId(), spuImages::setSpuId);
        JavaUtils.INSTANCE.acceptIfNotNull(spuImagesDto.getImgName(), spuImages::setImgName);
        JavaUtils.INSTANCE.acceptIfNotNull(spuImagesDto.getImgUrl(), spuImages::setImgUrl);
        JavaUtils.INSTANCE.acceptIfNotNull(spuImagesDto.getImgSort(), spuImages::setImgSort);
        JavaUtils.INSTANCE.acceptIfNotNull(spuImagesDto.getDefaultImg(), spuImages::setDefaultImg);
        spuImages.updateById();
        return spuImages;
    }

    /**
     * 删除spu图片
     * @param spuImagesDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpuImages delete(SpuImagesDto spuImagesDto) {
        SpuImages spuImages = baseMapper.selectById(spuImagesDto.getId());
        if (spuImages == null) {
            return null;
        }
        spuImages.deleteById();

        return spuImages;
    }

    /**
     * 分页查询spu图片
     * @param spuImagesDto
     * @param spuImagesPage
     * @return
     */
    @Override
    public IPage<SpuImages> queryPage(SpuImagesDto spuImagesDto, Page<SpuImages> spuImagesPage) {

        return baseMapper.queryPage(spuImagesPage, spuImagesDto);
    }


    /**
     * 校验spu图片名称
     * @param spuImagesDto
     * @return
     */
    @Override
    public boolean checkName(SpuImagesDto spuImagesDto, boolean isAdd) {

        QueryWrapper<SpuImages> spuImagesQueryWrapper = new QueryWrapper<SpuImages>();
        spuImagesQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        spuImagesQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            spuImagesQueryWrapper.ne("id", spuImagesDto.getId());
        }

        Integer count = baseMapper.selectCount(spuImagesQueryWrapper);
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
    public List<SpuImages> queryAll(SpuImagesDto spuImagesDto) {
        return baseMapper.queryAll(spuImagesDto);
    }

}
