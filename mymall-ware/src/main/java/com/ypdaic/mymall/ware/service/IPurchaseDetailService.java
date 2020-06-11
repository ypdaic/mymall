package com.ypdaic.mymall.ware.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.ware.entity.PurchaseDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.ware.vo.PurchaseDetailDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IPurchaseDetailService extends IService<PurchaseDetail> {

    /**
     * 新增
     * @param purchaseDetailDto
     * @return
     */
    PurchaseDetail add(PurchaseDetailDto purchaseDetailDto);

    /**
     * 更新
     * @param purchaseDetailDto
     * @return
     */
    PurchaseDetail update(PurchaseDetailDto purchaseDetailDto);

    /**
     * 删除
     * @param purchaseDetailDto
     * @return
     */
    PurchaseDetail delete(PurchaseDetailDto purchaseDetailDto);

    /**
     * 分页查询
     * @param purchaseDetailDto
     * @param purchaseDetailPage
     * @return
     */
    IPage<PurchaseDetail> queryPage(PurchaseDetailDto purchaseDetailDto, Page<PurchaseDetail> purchaseDetailPage);


    /**
     * 校验名称
     * @param purchaseDetailDto
     * @return
     */
    boolean checkName(PurchaseDetailDto purchaseDetailDto, boolean isAdd);

    /**
     *
     * 查询所有
     * @return
     */
    List<PurchaseDetail> queryAll(PurchaseDetailDto purchaseDetailDto);

    PageUtils queryPage(Map<String, Object> params);

    List<PurchaseDetail> listDetailByPurchaseId(Long id);
}
