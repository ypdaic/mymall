package com.ypdaic.mymall.product.service.impl;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.product.entity.SkuImages;
import com.ypdaic.mymall.product.mapper.SkuImagesMapper;
import com.ypdaic.mymall.product.service.ISkuImagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.product.vo.SkuImagesDto;
import com.ypdaic.mymall.product.enums.SkuImagesExcelHeadersEnum;
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
 * @since 2020-06-08
 */
@Service
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesMapper, SkuImages> implements ISkuImagesService {


    /**
     * 新增sku图片
     * @param skuImagesDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuImages add(SkuImagesDto skuImagesDto) {

        SkuImages skuImages = new SkuImages();
        skuImages.setSkuId(skuImagesDto.getSkuId());
        skuImages.setImgUrl(skuImagesDto.getImgUrl());
        skuImages.setImgSort(skuImagesDto.getImgSort());
        skuImages.setDefaultImg(skuImagesDto.getDefaultImg());
        skuImages.insert();
        return skuImages;
    }

    /**
     * 更新sku图片
     * @param skuImagesDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuImages update(SkuImagesDto skuImagesDto) {
        SkuImages skuImages = baseMapper.selectById(skuImagesDto.getId());
        if (skuImages == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(skuImagesDto.getSkuId(), skuImages::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(skuImagesDto.getImgUrl(), skuImages::setImgUrl);
        JavaUtils.INSTANCE.acceptIfNotNull(skuImagesDto.getImgSort(), skuImages::setImgSort);
        JavaUtils.INSTANCE.acceptIfNotNull(skuImagesDto.getDefaultImg(), skuImages::setDefaultImg);
        skuImages.updateById();
        return skuImages;
    }

    /**
     * 删除sku图片
     * @param skuImagesDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SkuImages delete(SkuImagesDto skuImagesDto) {
        SkuImages skuImages = baseMapper.selectById(skuImagesDto.getId());
        if (skuImages == null) {
            return null;
        }
        skuImages.deleteById();

        return skuImages;
    }

    /**
     * 分页查询sku图片
     * @param skuImagesDto
     * @param skuImagesPage
     * @return
     */
    @Override
    public IPage<SkuImages> queryPage(SkuImagesDto skuImagesDto, Page<SkuImages> skuImagesPage) {

        return baseMapper.queryPage(skuImagesPage, skuImagesDto);
    }


    /**
     * 校验sku图片名称
     * @param skuImagesDto
     * @return
     */
    @Override
    public boolean checkName(SkuImagesDto skuImagesDto, boolean isAdd) {

        QueryWrapper<SkuImages> skuImagesQueryWrapper = new QueryWrapper<SkuImages>();
        skuImagesQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        skuImagesQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            skuImagesQueryWrapper.ne("id", skuImagesDto.getId());
        }

        Integer count = baseMapper.selectCount(skuImagesQueryWrapper);
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
    public List<SkuImages> queryAll(SkuImagesDto skuImagesDto) {
        return baseMapper.queryAll(skuImagesDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuImages> page = this.page(
                new Query<SkuImages>().getPage(params),
                new QueryWrapper<SkuImages>()
        );

        return new PageUtils(page);
    }

}
