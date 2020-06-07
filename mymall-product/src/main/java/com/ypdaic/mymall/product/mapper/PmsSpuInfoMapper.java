package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsSpuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsSpuInfoDto;
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
 * @since 2020-06-07
 */
public interface PmsSpuInfoMapper extends BaseMapper<PmsSpuInfo> {

    /**
     * 分页查询spu信息
     * @param pmsSpuInfoPage
     * @param pmsSpuInfoDto
     * @return
     */
    IPage<PmsSpuInfo> queryPage(Page<PmsSpuInfo> pmsSpuInfoPage, @Param("dto") PmsSpuInfoDto pmsSpuInfoDto);

    /**
     * 导出查询数量
     * @param pmsSpuInfoDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsSpuInfoDto pmsSpuInfoDto);

    /**
     * 导出分页查询spu信息
     * @param pmsSpuInfoPage
     * @param pmsSpuInfoDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsSpuInfoPage, @Param("dto") PmsSpuInfoDto pmsSpuInfoDto);

    /**
     *
     * 查询所有spu信息
     * @return
     */
    List<PmsSpuInfo> queryAll(@Param("dto") PmsSpuInfoDto pmsSpuInfoDto);

}
