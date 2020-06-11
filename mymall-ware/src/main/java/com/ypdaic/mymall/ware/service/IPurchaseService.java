package com.ypdaic.mymall.ware.service;

import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.ware.entity.Purchase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.ware.vo.MergeVo;
import com.ypdaic.mymall.ware.vo.PurchaseDoneVo;
import com.ypdaic.mymall.ware.vo.PurchaseDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 采购信息 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
public interface IPurchaseService extends IService<Purchase> {

    /**
     * 新增采购信息
     * @param purchaseDto
     * @return
     */
    Purchase add(PurchaseDto purchaseDto);

    /**
     * 更新采购信息
     * @param purchaseDto
     * @return
     */
    Purchase update(PurchaseDto purchaseDto);

    /**
     * 删除采购信息
     * @param purchaseDto
     * @return
     */
    Purchase delete(PurchaseDto purchaseDto);

    /**
     * 分页查询采购信息
     * @param purchaseDto
     * @param purchasePage
     * @return
     */
    IPage<Purchase> queryPage(PurchaseDto purchaseDto, Page<Purchase> purchasePage);


    /**
     * 校验采购信息名称
     * @param purchaseDto
     * @return
     */
    boolean checkName(PurchaseDto purchaseDto, boolean isAdd);

    /**
     *
     * 查询所有采购信息
     * @return
     */
    List<Purchase> queryAll(PurchaseDto purchaseDto);

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageUnreceivePurchase(Map<String, Object> params);


    void mergePurchase(MergeVo mergeVo);


    void received(List<Long> ids);


    void done(PurchaseDoneVo doneVo);
}
