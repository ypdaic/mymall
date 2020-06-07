package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.SpuImages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.SpuImagesDto;
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
 * @since 2020-06-08
 */
public interface SpuImagesMapper extends BaseMapper<SpuImages> {

    /**
     * 分页查询spu图片
     * @param spuImagesPage
     * @param spuImagesDto
     * @return
     */
    IPage<SpuImages> queryPage(Page<SpuImages> spuImagesPage, @Param("dto") SpuImagesDto spuImagesDto);

    /**
     * 导出查询数量
     * @param spuImagesDto
     * @return
     */
    Integer queryCount(@Param("dto") SpuImagesDto spuImagesDto);

    /**
     * 导出分页查询spu图片
     * @param spuImagesPage
     * @param spuImagesDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> spuImagesPage, @Param("dto") SpuImagesDto spuImagesDto);

    /**
     *
     * 查询所有spu图片
     * @return
     */
    List<SpuImages> queryAll(@Param("dto") SpuImagesDto spuImagesDto);

}
