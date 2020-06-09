package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.product.entity.SkuSaleAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.SkuSaleAttrValueDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * sku销售属性&值 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ISkuSaleAttrValueService extends IService<SkuSaleAttrValue> {

    /**
     * 新增sku销售属性&值
     * @param skuSaleAttrValueDto
     * @return
     */
    SkuSaleAttrValue add(SkuSaleAttrValueDto skuSaleAttrValueDto);

    /**
     * 更新sku销售属性&值
     * @param skuSaleAttrValueDto
     * @return
     */
    SkuSaleAttrValue update(SkuSaleAttrValueDto skuSaleAttrValueDto);

    /**
     * 删除sku销售属性&值
     * @param skuSaleAttrValueDto
     * @return
     */
    SkuSaleAttrValue delete(SkuSaleAttrValueDto skuSaleAttrValueDto);

    /**
     * 分页查询sku销售属性&值
     * @param skuSaleAttrValueDto
     * @param skuSaleAttrValuePage
     * @return
     */
    IPage<SkuSaleAttrValue> queryPage(SkuSaleAttrValueDto skuSaleAttrValueDto, Page<SkuSaleAttrValue> skuSaleAttrValuePage);


    /**
     * 校验sku销售属性&值名称
     * @param skuSaleAttrValueDto
     * @return
     */
    boolean checkName(SkuSaleAttrValueDto skuSaleAttrValueDto, boolean isAdd);

    /**
     *
     * 查询所有sku销售属性&值
     * @return
     */
    List<SkuSaleAttrValue> queryAll(SkuSaleAttrValueDto skuSaleAttrValueDto);

    PageUtils queryPage(Map<String, Object> params);
}
