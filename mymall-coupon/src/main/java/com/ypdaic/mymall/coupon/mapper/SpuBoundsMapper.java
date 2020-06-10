package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.SpuBounds;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.SpuBoundsDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 商品spu积分设置 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-10
 */
public interface SpuBoundsMapper extends BaseMapper<SpuBounds> {

    /**
     * 分页查询商品spu积分设置
     * @param spuBoundsPage
     * @param spuBoundsDto
     * @return
     */
    IPage<SpuBounds> queryPage(Page<SpuBounds> spuBoundsPage, @Param("dto") SpuBoundsDto spuBoundsDto);

    /**
     * 导出查询数量
     * @param spuBoundsDto
     * @return
     */
    Integer queryCount(@Param("dto") SpuBoundsDto spuBoundsDto);

    /**
     * 导出分页查询商品spu积分设置
     * @param spuBoundsPage
     * @param spuBoundsDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> spuBoundsPage, @Param("dto") SpuBoundsDto spuBoundsDto);

    /**
     *
     * 查询所有商品spu积分设置
     * @return
     */
    List<SpuBounds> queryAll(@Param("dto") SpuBoundsDto spuBoundsDto);

}
