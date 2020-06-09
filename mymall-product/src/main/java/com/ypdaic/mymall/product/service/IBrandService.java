package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.product.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.BrandDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 品牌 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IBrandService extends IService<Brand> {

    /**
     * 新增品牌
     * @param brandDto
     * @return
     */
    Brand add(BrandDto brandDto);

    /**
     * 更新品牌
     * @param brandDto
     * @return
     */
    Brand update(BrandDto brandDto);

    /**
     * 删除品牌
     * @param brandDto
     * @return
     */
    Brand delete(BrandDto brandDto);

    /**
     * 分页查询品牌
     * @param brandDto
     * @param brandPage
     * @return
     */
    IPage<Brand> queryPage(BrandDto brandDto, Page<Brand> brandPage);


    /**
     * 校验品牌名称
     * @param brandDto
     * @return
     */
    boolean checkName(BrandDto brandDto, boolean isAdd);

    /**
     *
     * 查询所有品牌
     * @return
     */
    List<Brand> queryAll(BrandDto brandDto);

    PageUtils queryPage(Map<String, Object> params);

    void updateDetail(Brand brand);
}
