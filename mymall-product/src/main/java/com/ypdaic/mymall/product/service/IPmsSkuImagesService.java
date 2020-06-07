package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsSkuImages;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsSkuImagesDto;
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
 * @since 2020-06-07
 */
public interface IPmsSkuImagesService extends IService<PmsSkuImages> {

    /**
     * 新增sku图片
     * @param pmsSkuImagesDto
     * @return
     */
    PmsSkuImages add(PmsSkuImagesDto pmsSkuImagesDto);

    /**
     * 更新sku图片
     * @param pmsSkuImagesDto
     * @return
     */
    PmsSkuImages update(PmsSkuImagesDto pmsSkuImagesDto);

    /**
     * 删除sku图片
     * @param pmsSkuImagesDto
     * @return
     */
    PmsSkuImages delete(PmsSkuImagesDto pmsSkuImagesDto);

    /**
     * 分页查询sku图片
     * @param pmsSkuImagesDto
     * @param pmsSkuImagesPage
     * @return
     */
    IPage<PmsSkuImages> queryPage(PmsSkuImagesDto pmsSkuImagesDto, Page<PmsSkuImages> pmsSkuImagesPage);


    /**
     * 校验sku图片名称
     * @param pmsSkuImagesDto
     * @return
     */
    boolean checkName(PmsSkuImagesDto pmsSkuImagesDto, boolean isAdd);

    /**
     *
     * 查询所有sku图片
     * @return
     */
    List<PmsSkuImages> queryAll(PmsSkuImagesDto pmsSkuImagesDto);
}
