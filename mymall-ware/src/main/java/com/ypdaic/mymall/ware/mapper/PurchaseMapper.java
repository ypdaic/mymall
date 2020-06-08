package com.ypdaic.mymall.ware.mapper;

import com.ypdaic.mymall.ware.entity.Purchase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.ware.vo.PurchaseDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 采购信息 Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface PurchaseMapper extends BaseMapper<Purchase> {

    /**
     * 分页查询采购信息
     * @param purchasePage
     * @param purchaseDto
     * @return
     */
    IPage<Purchase> queryPage(Page<Purchase> purchasePage, @Param("dto") PurchaseDto purchaseDto);

    /**
     * 导出查询数量
     * @param purchaseDto
     * @return
     */
    Integer queryCount(@Param("dto") PurchaseDto purchaseDto);

    /**
     * 导出分页查询采购信息
     * @param purchasePage
     * @param purchaseDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> purchasePage, @Param("dto") PurchaseDto purchaseDto);

    /**
     *
     * 查询所有采购信息
     * @return
     */
    List<Purchase> queryAll(@Param("dto") PurchaseDto purchaseDto);

}
