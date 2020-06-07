package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsAttr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsAttrDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 商品属性 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface PmsAttrMapper extends BaseMapper<PmsAttr> {

    /**
     * 分页查询商品属性
     * @param pmsAttrPage
     * @param pmsAttrDto
     * @return
     */
    IPage<PmsAttr> queryPage(Page<PmsAttr> pmsAttrPage, @Param("dto") PmsAttrDto pmsAttrDto);

    /**
     * 导出查询数量
     * @param pmsAttrDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsAttrDto pmsAttrDto);

    /**
     * 导出分页查询商品属性
     * @param pmsAttrPage
     * @param pmsAttrDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsAttrPage, @Param("dto") PmsAttrDto pmsAttrDto);

    /**
     *
     * 查询所有商品属性
     * @return
     */
    List<PmsAttr> queryAll(@Param("dto") PmsAttrDto pmsAttrDto);

}
