package com.ypdaic.mymall.ware.service.impl;

import com.ypdaic.mymall.common.constant.WareConstant;
import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.common.util.Query;
import com.ypdaic.mymall.ware.entity.Purchase;
import com.ypdaic.mymall.ware.entity.PurchaseDetail;
import com.ypdaic.mymall.ware.mapper.PurchaseMapper;
import com.ypdaic.mymall.ware.service.IPurchaseDetailService;
import com.ypdaic.mymall.ware.service.IPurchaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.ware.service.IWareSkuService;
import com.ypdaic.mymall.ware.vo.MergeVo;
import com.ypdaic.mymall.ware.vo.PurchaseDoneVo;
import com.ypdaic.mymall.ware.vo.PurchaseDto;
import com.ypdaic.mymall.ware.enums.PurchaseExcelHeadersEnum;
import com.ypdaic.mymall.ware.vo.PurchaseItemDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;

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

    @Autowired
    IPurchaseDetailService detailService;

    @Autowired
    IWareSkuService wareSkuService;

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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Purchase> page = this.page(
                new Query<Purchase>().getPage(params),
                new QueryWrapper<Purchase>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageUnreceivePurchase(Map<String, Object> params) {
        IPage<Purchase> page = this.page(
                new Query<Purchase>().getPage(params),
                new QueryWrapper<Purchase>().eq("status",0).or().eq("status",1)
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void mergePurchase(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        if(purchaseId == null){
            //1、新建一个
            Purchase purchaseEntity = new Purchase();

            purchaseEntity.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());
            purchaseEntity.setCreateTime(new Date());
            purchaseEntity.setUpdateTime(new Date());
            this.save(purchaseEntity);
            purchaseId = purchaseEntity.getId();
        }

        //TODO 确认采购单状态是0,1才可以合并

        List<Long> items = mergeVo.getItems();
        Long finalPurchaseId = purchaseId;
        List<PurchaseDetail> collect = items.stream().map(i -> {
            PurchaseDetail detailEntity = new PurchaseDetail();

            detailEntity.setId(i);
            detailEntity.setPurchaseId(finalPurchaseId);
            detailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode());
            return detailEntity;
        }).collect(Collectors.toList());


        detailService.updateBatchById(collect);

        Purchase purchaseEntity = new Purchase();
        purchaseEntity.setId(purchaseId);
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);
    }

    /**
     *
     * @param ids 采购单id
     */
    @Override
    public void received(List<Long> ids) {
        //1、确认当前采购单是新建或者已分配状态
        List<Purchase> collect = ids.stream().map(id -> {
            Purchase byId = this.getById(id);
            return byId;
        }).filter(item -> {
            if (item.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getCode() ||
                    item.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()) {
                return true;
            }
            return false;
        }).map(item->{
            item.setStatus(WareConstant.PurchaseStatusEnum.RECEIVE.getCode());
            item.setUpdateTime(new Date());
            return item;
        }).collect(Collectors.toList());

        //2、改变采购单的状态
        this.updateBatchById(collect);



        //3、改变采购项的状态
        collect.forEach((item)->{
            List<PurchaseDetail> entities = detailService.listDetailByPurchaseId(item.getId());
            List<PurchaseDetail> detailEntities = entities.stream().map(entity -> {
                PurchaseDetail entity1 = new PurchaseDetail();
                entity1.setId(entity.getId());
                entity1.setStatus(WareConstant.PurchaseDetailStatusEnum.BUYING.getCode());
                return entity1;
            }).collect(Collectors.toList());
            detailService.updateBatchById(detailEntities);
        });
    }

    @Transactional
    @Override
    public void done(PurchaseDoneVo doneVo) {

        Long id = doneVo.getId();


        //2、改变采购项的状态
        Boolean flag = true;
        List<PurchaseItemDoneVo> items = doneVo.getItems();

        List<PurchaseDetail> updates = new ArrayList<>();
        for (PurchaseItemDoneVo item : items) {
            PurchaseDetail detailEntity = new PurchaseDetail();
            if(item.getStatus() == WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode()){
                flag = false;
                detailEntity.setStatus(item.getStatus());
            }else{
                detailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.FINISH.getCode());
                ////3、将成功采购的进行入库
                PurchaseDetail entity = detailService.getById(item.getItemId());
                wareSkuService.addStock(entity.getSkuId(),entity.getWareId(),entity.getSkuNum());

            }
            detailEntity.setId(item.getItemId());
            updates.add(detailEntity);
        }

        detailService.updateBatchById(updates);

        //1、改变采购单状态
        Purchase purchaseEntity = new Purchase();
        purchaseEntity.setId(id);
        purchaseEntity.setStatus(flag?WareConstant.PurchaseStatusEnum.FINISH.getCode():WareConstant.PurchaseStatusEnum.HASERROR.getCode());
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);




    }

}
