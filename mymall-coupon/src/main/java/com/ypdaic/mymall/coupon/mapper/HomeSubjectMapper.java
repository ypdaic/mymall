package com.ypdaic.mymall.coupon.mapper;

import com.ypdaic.mymall.coupon.entity.HomeSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.coupon.vo.HomeSubjectDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface HomeSubjectMapper extends BaseMapper<HomeSubject> {

    /**
     * 分页查询首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectPage
     * @param homeSubjectDto
     * @return
     */
    IPage<HomeSubject> queryPage(Page<HomeSubject> homeSubjectPage, @Param("dto") HomeSubjectDto homeSubjectDto);

    /**
     * 导出查询数量
     * @param homeSubjectDto
     * @return
     */
    Integer queryCount(@Param("dto") HomeSubjectDto homeSubjectDto);

    /**
     * 导出分页查询首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectPage
     * @param homeSubjectDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> homeSubjectPage, @Param("dto") HomeSubjectDto homeSubjectDto);

    /**
     *
     * 查询所有首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @return
     */
    List<HomeSubject> queryAll(@Param("dto") HomeSubjectDto homeSubjectDto);

}
