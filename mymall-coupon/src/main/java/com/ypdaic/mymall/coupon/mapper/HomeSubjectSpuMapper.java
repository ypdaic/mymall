package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.HomeSubjectSpu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.HomeSubjectSpuDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 专题商品 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface HomeSubjectSpuMapper extends BaseMapper<HomeSubjectSpu> {

    /**
     * 分页查询专题商品
     * @param homeSubjectSpuPage
     * @param homeSubjectSpuDto
     * @return
     */
    IPage<HomeSubjectSpu> queryPage(Page<HomeSubjectSpu> homeSubjectSpuPage, @Param("dto") HomeSubjectSpuDto homeSubjectSpuDto);

    /**
     * 导出查询数量
     * @param homeSubjectSpuDto
     * @return
     */
    Integer queryCount(@Param("dto") HomeSubjectSpuDto homeSubjectSpuDto);

    /**
     * 导出分页查询专题商品
     * @param homeSubjectSpuPage
     * @param homeSubjectSpuDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> homeSubjectSpuPage, @Param("dto") HomeSubjectSpuDto homeSubjectSpuDto);

    /**
     *
     * 查询所有专题商品
     * @return
     */
    List<HomeSubjectSpu> queryAll(@Param("dto") HomeSubjectSpuDto homeSubjectSpuDto);

}
