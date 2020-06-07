package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsProductAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsProductAttrValueDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * spu属性值 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface PmsProductAttrValueMapper extends BaseMapper<PmsProductAttrValue> {

    /**
     * 分页查询spu属性值
     * @param pmsProductAttrValuePage
     * @param pmsProductAttrValueDto
     * @return
     */
    IPage<PmsProductAttrValue> queryPage(Page<PmsProductAttrValue> pmsProductAttrValuePage, @Param("dto") PmsProductAttrValueDto pmsProductAttrValueDto);

    /**
     * 导出查询数量
     * @param pmsProductAttrValueDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsProductAttrValueDto pmsProductAttrValueDto);

    /**
     * 导出分页查询spu属性值
     * @param pmsProductAttrValuePage
     * @param pmsProductAttrValueDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsProductAttrValuePage, @Param("dto") PmsProductAttrValueDto pmsProductAttrValueDto);

    /**
     *
     * 查询所有spu属性值
     * @return
     */
    List<PmsProductAttrValue> queryAll(@Param("dto") PmsProductAttrValueDto pmsProductAttrValueDto);

}
