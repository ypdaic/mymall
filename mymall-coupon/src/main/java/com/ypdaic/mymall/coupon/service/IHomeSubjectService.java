package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.coupon.entity.HomeSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.HomeSubjectDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IHomeSubjectService extends IService<HomeSubject> {

    /**
     * 新增首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectDto
     * @return
     */
    HomeSubject add(HomeSubjectDto homeSubjectDto);

    /**
     * 更新首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectDto
     * @return
     */
    HomeSubject update(HomeSubjectDto homeSubjectDto);

    /**
     * 删除首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectDto
     * @return
     */
    HomeSubject delete(HomeSubjectDto homeSubjectDto);

    /**
     * 分页查询首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @param homeSubjectDto
     * @param homeSubjectPage
     * @return
     */
    IPage<HomeSubject> queryPage(HomeSubjectDto homeSubjectDto, Page<HomeSubject> homeSubjectPage);


    /**
     * 校验首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】名称
     * @param homeSubjectDto
     * @return
     */
    boolean checkName(HomeSubjectDto homeSubjectDto, boolean isAdd);

    /**
     *
     * 查询所有首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】
     * @return
     */
    List<HomeSubject> queryAll(HomeSubjectDto homeSubjectDto);

    PageUtils queryPage(Map<String, Object> params);
}
