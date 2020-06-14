package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.product.entity.SkuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.SkuInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * sku信息 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface ISkuInfoService extends IService<SkuInfo> {

    /**
     * 新增sku信息
     * @param skuInfoDto
     * @return
     */
    SkuInfo add(SkuInfoDto skuInfoDto);

    /**
     * 更新sku信息
     * @param skuInfoDto
     * @return
     */
    SkuInfo update(SkuInfoDto skuInfoDto);

    /**
     * 删除sku信息
     * @param skuInfoDto
     * @return
     */
    SkuInfo delete(SkuInfoDto skuInfoDto);

    /**
     * 分页查询sku信息
     * @param skuInfoDto
     * @param skuInfoPage
     * @return
     */
    IPage<SkuInfo> queryPage(SkuInfoDto skuInfoDto, Page<SkuInfo> skuInfoPage);


    /**
     * 校验sku信息名称
     * @param skuInfoDto
     * @return
     */
    boolean checkName(SkuInfoDto skuInfoDto, boolean isAdd);

    /**
     *
     * 查询所有sku信息
     * @return
     */
    List<SkuInfo> queryAll(SkuInfoDto skuInfoDto);

    PageUtils queryPage(Map<String, Object> params);

    void saveSkuInfo(SkuInfo skuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);

    List<SkuInfo> getSkusBySpuId(Long spuId);
}
