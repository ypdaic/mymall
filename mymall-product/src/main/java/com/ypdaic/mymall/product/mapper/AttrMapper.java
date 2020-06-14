package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.Attr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.AttrDto;
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
 * @since 2020-06-08
 */
public interface AttrMapper extends BaseMapper<Attr> {

    /**
     * 分页查询商品属性
     * @param attrPage
     * @param attrDto
     * @return
     */
    IPage<Attr> queryPage(Page<Attr> attrPage, @Param("dto") AttrDto attrDto);

    /**
     * 导出查询数量
     * @param attrDto
     * @return
     */
    Integer queryCount(@Param("dto") AttrDto attrDto);

    /**
     * 导出分页查询商品属性
     * @param attrPage
     * @param attrDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> attrPage, @Param("dto") AttrDto attrDto);

    /**
     *
     * 查询所有商品属性
     * @return
     */
    List<Attr> queryAll(@Param("dto") AttrDto attrDto);

    List<Long> selectSearchAttrIds(@Param("attrIds") List<Long> attrIds);
}
