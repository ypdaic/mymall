package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsSkuSaleAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsSkuSaleAttrValueDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * sku销售属性&值 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface IPmsSkuSaleAttrValueService extends IService<PmsSkuSaleAttrValue> {

    /**
     * 新增sku销售属性&值
     * @param pmsSkuSaleAttrValueDto
     * @return
     */
    PmsSkuSaleAttrValue add(PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto);

    /**
     * 更新sku销售属性&值
     * @param pmsSkuSaleAttrValueDto
     * @return
     */
    PmsSkuSaleAttrValue update(PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto);

    /**
     * 删除sku销售属性&值
     * @param pmsSkuSaleAttrValueDto
     * @return
     */
    PmsSkuSaleAttrValue delete(PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto);

    /**
     * 分页查询sku销售属性&值
     * @param pmsSkuSaleAttrValueDto
     * @param pmsSkuSaleAttrValuePage
     * @return
     */
    IPage<PmsSkuSaleAttrValue> queryPage(PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto, Page<PmsSkuSaleAttrValue> pmsSkuSaleAttrValuePage);


    /**
     * 校验sku销售属性&值名称
     * @param pmsSkuSaleAttrValueDto
     * @return
     */
    boolean checkName(PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto, boolean isAdd);

    /**
     *
     * 查询所有sku销售属性&值
     * @return
     */
    List<PmsSkuSaleAttrValue> queryAll(PmsSkuSaleAttrValueDto pmsSkuSaleAttrValueDto);
}
