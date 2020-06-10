package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.SpuInfoDesc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.SpuInfoDescDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * spu信息介绍 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-10
 */
public interface SpuInfoDescMapper extends BaseMapper<SpuInfoDesc> {

    /**
     * 分页查询spu信息介绍
     * @param spuInfoDescPage
     * @param spuInfoDescDto
     * @return
     */
    IPage<SpuInfoDesc> queryPage(Page<SpuInfoDesc> spuInfoDescPage, @Param("dto") SpuInfoDescDto spuInfoDescDto);

    /**
     * 导出查询数量
     * @param spuInfoDescDto
     * @return
     */
    Integer queryCount(@Param("dto") SpuInfoDescDto spuInfoDescDto);

    /**
     * 导出分页查询spu信息介绍
     * @param spuInfoDescPage
     * @param spuInfoDescDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> spuInfoDescPage, @Param("dto") SpuInfoDescDto spuInfoDescDto);

    /**
     *
     * 查询所有spu信息介绍
     * @return
     */
    List<SpuInfoDesc> queryAll(@Param("dto") SpuInfoDescDto spuInfoDescDto);

}
