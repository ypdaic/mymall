package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.coupon.entity.HomeSubjectSpu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.HomeSubjectSpuDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 专题商品 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IHomeSubjectSpuService extends IService<HomeSubjectSpu> {

    /**
     * 新增专题商品
     * @param homeSubjectSpuDto
     * @return
     */
    HomeSubjectSpu add(HomeSubjectSpuDto homeSubjectSpuDto);

    /**
     * 更新专题商品
     * @param homeSubjectSpuDto
     * @return
     */
    HomeSubjectSpu update(HomeSubjectSpuDto homeSubjectSpuDto);

    /**
     * 删除专题商品
     * @param homeSubjectSpuDto
     * @return
     */
    HomeSubjectSpu delete(HomeSubjectSpuDto homeSubjectSpuDto);

    /**
     * 分页查询专题商品
     * @param homeSubjectSpuDto
     * @param homeSubjectSpuPage
     * @return
     */
    IPage<HomeSubjectSpu> queryPage(HomeSubjectSpuDto homeSubjectSpuDto, Page<HomeSubjectSpu> homeSubjectSpuPage);


    /**
     * 校验专题商品名称
     * @param homeSubjectSpuDto
     * @return
     */
    boolean checkName(HomeSubjectSpuDto homeSubjectSpuDto, boolean isAdd);

    /**
     *
     * 查询所有专题商品
     * @return
     */
    List<HomeSubjectSpu> queryAll(HomeSubjectSpuDto homeSubjectSpuDto);

    PageUtils queryPage(Map<String, Object> params);
}
