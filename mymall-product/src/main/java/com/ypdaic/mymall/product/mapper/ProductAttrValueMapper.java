package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.ProductAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.ProductAttrValueDto;
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
 * @since 2020-06-08
 */
public interface ProductAttrValueMapper extends BaseMapper<ProductAttrValue> {

    /**
     * 分页查询spu属性值
     * @param productAttrValuePage
     * @param productAttrValueDto
     * @return
     */
    IPage<ProductAttrValue> queryPage(Page<ProductAttrValue> productAttrValuePage, @Param("dto") ProductAttrValueDto productAttrValueDto);

    /**
     * 导出查询数量
     * @param productAttrValueDto
     * @return
     */
    Integer queryCount(@Param("dto") ProductAttrValueDto productAttrValueDto);

    /**
     * 导出分页查询spu属性值
     * @param productAttrValuePage
     * @param productAttrValueDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> productAttrValuePage, @Param("dto") ProductAttrValueDto productAttrValueDto);

    /**
     *
     * 查询所有spu属性值
     * @return
     */
    List<ProductAttrValue> queryAll(@Param("dto") ProductAttrValueDto productAttrValueDto);

}
