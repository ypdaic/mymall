package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsBrand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsBrandDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * 品牌 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface IPmsBrandService extends IService<PmsBrand> {

    /**
     * 新增品牌
     * @param pmsBrandDto
     * @return
     */
    PmsBrand add(PmsBrandDto pmsBrandDto);

    /**
     * 更新品牌
     * @param pmsBrandDto
     * @return
     */
    PmsBrand update(PmsBrandDto pmsBrandDto);

    /**
     * 删除品牌
     * @param pmsBrandDto
     * @return
     */
    PmsBrand delete(PmsBrandDto pmsBrandDto);

    /**
     * 分页查询品牌
     * @param pmsBrandDto
     * @param pmsBrandPage
     * @return
     */
    IPage<PmsBrand> queryPage(PmsBrandDto pmsBrandDto, Page<PmsBrand> pmsBrandPage);


    /**
     * 校验品牌名称
     * @param pmsBrandDto
     * @return
     */
    boolean checkName(PmsBrandDto pmsBrandDto, boolean isAdd);

    /**
     *
     * 查询所有品牌
     * @return
     */
    List<PmsBrand> queryAll(PmsBrandDto pmsBrandDto);
}
