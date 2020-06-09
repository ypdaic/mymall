package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.product.entity.SpuImages;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.SpuImagesDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * spu图片 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ISpuImagesService extends IService<SpuImages> {

    /**
     * 新增spu图片
     * @param spuImagesDto
     * @return
     */
    SpuImages add(SpuImagesDto spuImagesDto);

    /**
     * 更新spu图片
     * @param spuImagesDto
     * @return
     */
    SpuImages update(SpuImagesDto spuImagesDto);

    /**
     * 删除spu图片
     * @param spuImagesDto
     * @return
     */
    SpuImages delete(SpuImagesDto spuImagesDto);

    /**
     * 分页查询spu图片
     * @param spuImagesDto
     * @param spuImagesPage
     * @return
     */
    IPage<SpuImages> queryPage(SpuImagesDto spuImagesDto, Page<SpuImages> spuImagesPage);


    /**
     * 校验spu图片名称
     * @param spuImagesDto
     * @return
     */
    boolean checkName(SpuImagesDto spuImagesDto, boolean isAdd);

    /**
     *
     * 查询所有spu图片
     * @return
     */
    List<SpuImages> queryAll(SpuImagesDto spuImagesDto);


    PageUtils queryPage(Map<String, Object> params);

    void saveImages(Long id, List<String> images);
}
