package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.coupon.entity.HomeAdv;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.HomeAdvDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 首页轮播广告 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IHomeAdvService extends IService<HomeAdv> {

    /**
     * 新增首页轮播广告
     * @param homeAdvDto
     * @return
     */
    HomeAdv add(HomeAdvDto homeAdvDto);

    /**
     * 更新首页轮播广告
     * @param homeAdvDto
     * @return
     */
    HomeAdv update(HomeAdvDto homeAdvDto);

    /**
     * 删除首页轮播广告
     * @param homeAdvDto
     * @return
     */
    HomeAdv delete(HomeAdvDto homeAdvDto);

    /**
     * 分页查询首页轮播广告
     * @param homeAdvDto
     * @param homeAdvPage
     * @return
     */
    IPage<HomeAdv> queryPage(HomeAdvDto homeAdvDto, Page<HomeAdv> homeAdvPage);


    /**
     * 校验首页轮播广告名称
     * @param homeAdvDto
     * @return
     */
    boolean checkName(HomeAdvDto homeAdvDto, boolean isAdd);

    /**
     *
     * 查询所有首页轮播广告
     * @return
     */
    List<HomeAdv> queryAll(HomeAdvDto homeAdvDto);
}
