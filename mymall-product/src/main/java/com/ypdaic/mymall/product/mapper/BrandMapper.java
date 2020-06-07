package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.BrandDto;
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
 * @since 2020-06-08
 */
public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 分页查询品牌
     * @param brandPage
     * @param brandDto
     * @return
     */
    IPage<Brand> queryPage(Page<Brand> brandPage, @Param("dto") BrandDto brandDto);

    /**
     * 导出查询数量
     * @param brandDto
     * @return
     */
    Integer queryCount(@Param("dto") BrandDto brandDto);

    /**
     * 导出分页查询品牌
     * @param brandPage
     * @param brandDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> brandPage, @Param("dto") BrandDto brandDto);

    /**
     *
     * 查询所有品牌
     * @return
     */
    List<Brand> queryAll(@Param("dto") BrandDto brandDto);

}
