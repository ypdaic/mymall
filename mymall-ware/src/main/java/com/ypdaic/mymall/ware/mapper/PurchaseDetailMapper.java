package com.ypdaic.mymall.ware.mapper;

import com.ypdaic.mymall.ware.entity.PurchaseDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypdaic.mymall.ware.vo.PurchaseDetailDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface PurchaseDetailMapper extends BaseMapper<PurchaseDetail> {

    /**
     * 分页查询
     * @param purchaseDetailPage
     * @param purchaseDetailDto
     * @return
     */
    IPage<PurchaseDetail> queryPage(Page<PurchaseDetail> purchaseDetailPage, @Param("dto") PurchaseDetailDto purchaseDetailDto);

    /**
     * 导出查询数量
     * @param purchaseDetailDto
     * @return
     */
    Integer queryCount(@Param("dto") PurchaseDetailDto purchaseDetailDto);

    /**
     * 导出分页查询
     * @param purchaseDetailPage
     * @param purchaseDetailDto
     * @return
     */
    IPage<Map<String, Object>> queryPageForExport(Page<Map<String, Object>> purchaseDetailPage, @Param("dto") PurchaseDetailDto purchaseDetailDto);

    /**
     *
     * 查询所有
     * @return
     */
    List<PurchaseDetail> queryAll(@Param("dto") PurchaseDetailDto purchaseDetailDto);

}
