package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsBrandDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 品牌 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface PmsBrandMapper extends BaseMapper<PmsBrand> {

    /**
     * 分页查询品牌
     * @param pmsBrandPage
     * @param pmsBrandDto
     * @return
     */
    IPage<PmsBrand> queryPage(Page<PmsBrand> pmsBrandPage, @Param("dto") PmsBrandDto pmsBrandDto);

    /**
     * 导出查询数量
     * @param pmsBrandDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsBrandDto pmsBrandDto);

    /**
     * 导出分页查询品牌
     * @param pmsBrandPage
     * @param pmsBrandDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsBrandPage, @Param("dto") PmsBrandDto pmsBrandDto);

    /**
     *
     * 查询所有品牌
     * @return
     */
    List<PmsBrand> queryAll(@Param("dto") PmsBrandDto pmsBrandDto);

}
