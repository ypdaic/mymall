package com.ypdaic.mymall.product.mapper;

import com.ypdaic.mymall.product.entity.PmsAttrGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.product.vo.PmsAttrGroupDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 属性分组 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface PmsAttrGroupMapper extends BaseMapper<PmsAttrGroup> {

    /**
     * 分页查询属性分组
     * @param pmsAttrGroupPage
     * @param pmsAttrGroupDto
     * @return
     */
    IPage<PmsAttrGroup> queryPage(Page<PmsAttrGroup> pmsAttrGroupPage, @Param("dto") PmsAttrGroupDto pmsAttrGroupDto);

    /**
     * 导出查询数量
     * @param pmsAttrGroupDto
     * @return
     */
    Integer queryCount(@Param("dto") PmsAttrGroupDto pmsAttrGroupDto);

    /**
     * 导出分页查询属性分组
     * @param pmsAttrGroupPage
     * @param pmsAttrGroupDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> pmsAttrGroupPage, @Param("dto") PmsAttrGroupDto pmsAttrGroupDto);

    /**
     *
     * 查询所有属性分组
     * @return
     */
    List<PmsAttrGroup> queryAll(@Param("dto") PmsAttrGroupDto pmsAttrGroupDto);

}
