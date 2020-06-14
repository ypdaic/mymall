package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.SpuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.SpuInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * spu信息 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface SpuInfoMapper extends BaseMapper<SpuInfo> {

    /**
     * 分页查询spu信息
     * @param spuInfoPage
     * @param spuInfoDto
     * @return
     */
    IPage<SpuInfo> queryPage(Page<SpuInfo> spuInfoPage, @Param("dto") SpuInfoDto spuInfoDto);

    /**
     * 导出查询数量
     * @param spuInfoDto
     * @return
     */
    Integer queryCount(@Param("dto") SpuInfoDto spuInfoDto);

    /**
     * 导出分页查询spu信息
     * @param spuInfoPage
     * @param spuInfoDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> spuInfoPage, @Param("dto") SpuInfoDto spuInfoDto);

    /**
     *
     * 查询所有spu信息
     * @return
     */
    List<SpuInfo> queryAll(@Param("dto") SpuInfoDto spuInfoDto);

    void updateSpuStatus(@Param("spuId") Long spuId, @Param("code") int code);
}
