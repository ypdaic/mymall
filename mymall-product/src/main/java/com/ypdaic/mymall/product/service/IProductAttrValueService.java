package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.product.entity.ProductAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.ProductAttrValueDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * spu属性值 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IProductAttrValueService extends IService<ProductAttrValue> {

    /**
     * 新增spu属性值
     * @param productAttrValueDto
     * @return
     */
    ProductAttrValue add(ProductAttrValueDto productAttrValueDto);

    /**
     * 更新spu属性值
     * @param productAttrValueDto
     * @return
     */
    ProductAttrValue update(ProductAttrValueDto productAttrValueDto);

    /**
     * 删除spu属性值
     * @param productAttrValueDto
     * @return
     */
    ProductAttrValue delete(ProductAttrValueDto productAttrValueDto);

    /**
     * 分页查询spu属性值
     * @param productAttrValueDto
     * @param productAttrValuePage
     * @return
     */
    IPage<ProductAttrValue> queryPage(ProductAttrValueDto productAttrValueDto, Page<ProductAttrValue> productAttrValuePage);


    /**
     * 校验spu属性值名称
     * @param productAttrValueDto
     * @return
     */
    boolean checkName(ProductAttrValueDto productAttrValueDto, boolean isAdd);

    /**
     *
     * 查询所有spu属性值
     * @return
     */
    List<ProductAttrValue> queryAll(ProductAttrValueDto productAttrValueDto);

    PageUtils queryPage(Map<String, Object> params);

    void saveProductAttr(List<ProductAttrValue> collect);


    List<ProductAttrValue> baseAttrlistforspu(Long spuId);


    void updateSpuAttr(Long spuId, List<ProductAttrValue> entities);
}
