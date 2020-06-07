package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsSpuImages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsSpuImagesDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * spu图片 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface PmsSpuImagesMapper extends BaseMapper<PmsSpuImages> {

    /**
     * 分页查询spu图片
     * @param pmsSpuImagesPage
     * @param pmsSpuImagesDto
     * @return
     */
    IPage<PmsSpuImages> queryPage(Page<PmsSpuImages> pmsSpuImagesPage, @Param("dto") PmsSpuImagesDto pmsSpuImagesDto);

    /**
     * 导出查询数量
     * @param pmsSpuImagesDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsSpuImagesDto pmsSpuImagesDto);

    /**
     * 导出分页查询spu图片
     * @param pmsSpuImagesPage
     * @param pmsSpuImagesDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsSpuImagesPage, @Param("dto") PmsSpuImagesDto pmsSpuImagesDto);

    /**
     *
     * 查询所有spu图片
     * @return
     */
    List<PmsSpuImages> queryAll(@Param("dto") PmsSpuImagesDto pmsSpuImagesDto);

}
