package com.ypdaic.mymall.ware.service.impl;

import com.ypdaic.mymall.ware.entity.Purchase;
import com.ypdaic.mymall.ware.mapper.PurchaseMapper;
import com.ypdaic.mymall.ware.service.IPurchaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.ware.vo.PurchaseDto;
import com.ypdaic.mymall.ware.enums.PurchaseExcelHeadersEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypdaic.mymall.common.util.ExcelUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.ypdaic.mymall.common.enums.EnableEnum;
import com.ypdaic.mymall.common.util.JavaUtils;
import java.util.Date;
import java.util.Date;

/**
 * <p>
 * 采购信息 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-08
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements IPurchaseService {


    /**
     * 新增采购信息
     * @param purchaseDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Purchase add(PurchaseDto purchaseDto) {

        Purchase purchase = new Purchase();
        purchase.setAssigneeId(purchaseDto.getAssigneeId());
        purchase.setAssigneeName(purchaseDto.getAssigneeName());
        purchase.setPhone(purchaseDto.getPhone());
        purchase.setPriority(purchaseDto.getPriority());
        purchase.setStatus(purchaseDto.getStatus());
        purchase.setWareId(purchaseDto.getWareId());
        purchase.setAmount(purchaseDto.getAmount());
        Date date7= new Date();
        purchase.setCreateTime(date7);
        Date date8= new Date();
        purchase.setUpdateTime(date8);
        purchase.insert();
        return purchase;
    }

    /**
     * 更新采购信息
     * @param purchaseDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Purchase update(PurchaseDto purchaseDto) {
        Purchase purchase = baseMapper.selectById(purchaseDto.getId());
        if (purchase == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(purchaseDto.getAssigneeId(), purchase::setAssigneeId);
        JavaUtils.INSTANCE.acceptIfNotNull(purchaseDto.getAssigneeName(), purchase::setAssigneeName);
        JavaUtils.INSTANCE.acceptIfNotNull(purchaseDto.getPhone(), purchase::setPhone);
        JavaUtils.INSTANCE.acceptIfNotNull(purchaseDto.getPriority(), purchase::setPriority);
        JavaUtils.INSTANCE.acceptIfNotNull(purchaseDto.getStatus(), purchase::setStatus);
        JavaUtils.INSTANCE.acceptIfNotNull(purchaseDto.getWareId(), purchase::setWareId);
        JavaUtils.INSTANCE.acceptIfNotNull(purchaseDto.getAmount(), purchase::setAmount);
        Date date8= new Date();
        purchase.setUpdateTime(date8);
        purchase.updateById();
        return purchase;
    }

    /**
     * 删除采购信息
     * @param purchaseDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Purchase delete(PurchaseDto purchaseDto) {
        Purchase purchase = baseMapper.selectById(purchaseDto.getId());
        if (purchase == null) {
            return null;
        }
        purchase.deleteById();

        return purchase;
    }

    /**
     * 分页查询采购信息
     * @param purchaseDto
     * @param purchasePage
     * @return
     */
    @Override
    public IPage<Purchase> queryPage(PurchaseDto purchaseDto, Page<Purchase> purchasePage) {

        return baseMapper.queryPage(purchasePage, purchaseDto);
    }


    /**
     * 校验采购信息名称
     * @param purchaseDto
     * @return
     */
    @Override
    public boolean checkName(PurchaseDto purchaseDto, boolean isAdd) {

        QueryWrapper<Purchase> purchaseQueryWrapper = new QueryWrapper<Purchase>();
        purchaseQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        purchaseQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            purchaseQueryWrapper.ne("id", purchaseDto.getId());
        }

        Integer count = baseMapper.selectCount(purchaseQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有采购信息
     * @return
     */
    public List<Purchase> queryAll(PurchaseDto purchaseDto) {
        return baseMapper.queryAll(purchaseDto);
    }

}
