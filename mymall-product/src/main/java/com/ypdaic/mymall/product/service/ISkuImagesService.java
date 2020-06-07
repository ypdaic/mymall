package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.SkuImages;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.SkuImagesDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * sku图片 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ISkuImagesService extends IService<SkuImages> {

    /**
     * 新增sku图片
     * @param skuImagesDto
     * @return
     */
    SkuImages add(SkuImagesDto skuImagesDto);

    /**
     * 更新sku图片
     * @param skuImagesDto
     * @return
     */
    SkuImages update(SkuImagesDto skuImagesDto);

    /**
     * 删除sku图片
     * @param skuImagesDto
     * @return
     */
    SkuImages delete(SkuImagesDto skuImagesDto);

    /**
     * 分页查询sku图片
     * @param skuImagesDto
     * @param skuImagesPage
     * @return
     */
    IPage<SkuImages> queryPage(SkuImagesDto skuImagesDto, Page<SkuImages> skuImagesPage);


    /**
     * 校验sku图片名称
     * @param skuImagesDto
     * @return
     */
    boolean checkName(SkuImagesDto skuImagesDto, boolean isAdd);

    /**
     *
     * 查询所有sku图片
     * @return
     */
    List<SkuImages> queryAll(SkuImagesDto skuImagesDto);
}
