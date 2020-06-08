package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.HomeAdv;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.HomeAdvDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 首页轮播广告 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface HomeAdvMapper extends BaseMapper<HomeAdv> {

    /**
     * 分页查询首页轮播广告
     * @param homeAdvPage
     * @param homeAdvDto
     * @return
     */
    IPage<HomeAdv> queryPage(Page<HomeAdv> homeAdvPage, @Param("dto") HomeAdvDto homeAdvDto);

    /**
     * 导出查询数量
     * @param homeAdvDto
     * @return
     */
    Integer queryCount(@Param("dto") HomeAdvDto homeAdvDto);

    /**
     * 导出分页查询首页轮播广告
     * @param homeAdvPage
     * @param homeAdvDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> homeAdvPage, @Param("dto") HomeAdvDto homeAdvDto);

    /**
     *
     * 查询所有首页轮播广告
     * @return
     */
    List<HomeAdv> queryAll(@Param("dto") HomeAdvDto homeAdvDto);

}
