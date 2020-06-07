package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsSpuImages;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsSpuImagesDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * spu图片 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface IPmsSpuImagesService extends IService<PmsSpuImages> {

    /**
     * 新增spu图片
     * @param pmsSpuImagesDto
     * @return
     */
    PmsSpuImages add(PmsSpuImagesDto pmsSpuImagesDto);

    /**
     * 更新spu图片
     * @param pmsSpuImagesDto
     * @return
     */
    PmsSpuImages update(PmsSpuImagesDto pmsSpuImagesDto);

    /**
     * 删除spu图片
     * @param pmsSpuImagesDto
     * @return
     */
    PmsSpuImages delete(PmsSpuImagesDto pmsSpuImagesDto);

    /**
     * 分页查询spu图片
     * @param pmsSpuImagesDto
     * @param pmsSpuImagesPage
     * @return
     */
    IPage<PmsSpuImages> queryPage(PmsSpuImagesDto pmsSpuImagesDto, Page<PmsSpuImages> pmsSpuImagesPage);


    /**
     * 校验spu图片名称
     * @param pmsSpuImagesDto
     * @return
     */
    boolean checkName(PmsSpuImagesDto pmsSpuImagesDto, boolean isAdd);

    /**
     *
     * 查询所有spu图片
     * @return
     */
    List<PmsSpuImages> queryAll(PmsSpuImagesDto pmsSpuImagesDto);
}
