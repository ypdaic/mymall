package com.ypdaic.mymall.coupon.service;

import com.ypdaic.mymall.common.to.SkuReductionTo;
import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.coupon.entity.SkuFullReduction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.coupon.vo.SkuFullReductionDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 商品满减信息 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ISkuFullReductionService extends IService<SkuFullReduction> {

    /**
     * 新增商品满减信息
     * @param skuFullReductionDto
     * @return
     */
    SkuFullReduction add(SkuFullReductionDto skuFullReductionDto);

    /**
     * 更新商品满减信息
     * @param skuFullReductionDto
     * @return
     */
    SkuFullReduction update(SkuFullReductionDto skuFullReductionDto);

    /**
     * 删除商品满减信息
     * @param skuFullReductionDto
     * @return
     */
    SkuFullReduction delete(SkuFullReductionDto skuFullReductionDto);

    /**
     * 分页查询商品满减信息
     * @param skuFullReductionDto
     * @param skuFullReductionPage
     * @return
     */
    IPage<SkuFullReduction> queryPage(SkuFullReductionDto skuFullReductionDto, Page<SkuFullReduction> skuFullReductionPage);


    /**
     * 校验商品满减信息名称
     * @param skuFullReductionDto
     * @return
     */
    boolean checkName(SkuFullReductionDto skuFullReductionDto, boolean isAdd);

    /**
     *
     * 查询所有商品满减信息
     * @return
     */
    List<SkuFullReduction> queryAll(SkuFullReductionDto skuFullReductionDto);

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuReduction(SkuReductionTo reductionTo);
}
