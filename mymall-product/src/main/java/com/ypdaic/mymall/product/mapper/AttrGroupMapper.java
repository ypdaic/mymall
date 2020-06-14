package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.AttrGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.AttrGroupDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ypdaic.mymall.product.vo.SpuItemAttrGroupVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

/**
 * <p>
 * 属性分组 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Repository
public interface AttrGroupMapper extends BaseMapper<AttrGroup> {

    /**
     * 分页查询属性分组
     * @param attrGroupPage
     * @param attrGroupDto
     * @return
     */
    IPage<AttrGroup> queryPage(Page<AttrGroup> attrGroupPage, @Param("dto") AttrGroupDto attrGroupDto);

    /**
     * 导出查询数量
     * @param attrGroupDto
     * @return
     */
    Integer queryCount(@Param("dto") AttrGroupDto attrGroupDto);

    /**
     * 导出分页查询属性分组
     * @param attrGroupPage
     * @param attrGroupDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> attrGroupPage, @Param("dto") AttrGroupDto attrGroupDto);

    /**
     *
     * 查询所有属性分组
     * @return
     */
    List<AttrGroup> queryAll(@Param("dto") AttrGroupDto attrGroupDto);

    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId, @Param("catalogId") Long catalogId);
}
