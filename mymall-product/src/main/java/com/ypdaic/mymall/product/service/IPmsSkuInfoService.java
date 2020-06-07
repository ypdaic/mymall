package com.ypdaic.mymall.product.service;

import com.ypdaic.mymall.product.entity.PmsSkuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.product.vo.PmsSkuInfoDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * <p>
 * sku信息 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-07
 */
public interface IPmsSkuInfoService extends IService<PmsSkuInfo> {

    /**
     * 新增sku信息
     * @param pmsSkuInfoDto
     * @return
     */
    PmsSkuInfo add(PmsSkuInfoDto pmsSkuInfoDto);

    /**
     * 更新sku信息
     * @param pmsSkuInfoDto
     * @return
     */
    PmsSkuInfo update(PmsSkuInfoDto pmsSkuInfoDto);

    /**
     * 删除sku信息
     * @param pmsSkuInfoDto
     * @return
     */
    PmsSkuInfo delete(PmsSkuInfoDto pmsSkuInfoDto);

    /**
     * 分页查询sku信息
     * @param pmsSkuInfoDto
     * @param pmsSkuInfoPage
     * @return
     */
    IPage<PmsSkuInfo> queryPage(PmsSkuInfoDto pmsSkuInfoDto, Page<PmsSkuInfo> pmsSkuInfoPage);


    /**
     * 校验sku信息名称
     * @param pmsSkuInfoDto
     * @return
     */
    boolean checkName(PmsSkuInfoDto pmsSkuInfoDto, boolean isAdd);

    /**
     *
     * 查询所有sku信息
     * @return
     */
    List<PmsSkuInfo> queryAll(PmsSkuInfoDto pmsSkuInfoDto);
}
