package com.ypdaic.mymall.ware.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.rabbitmq.client.Channel;
import com.ypdaic.mymall.common.to.mq.OrderTo;
import com.ypdaic.mymall.common.to.mq.StockDetailTo;
import com.ypdaic.mymall.common.to.mq.StockLockedTo;
import com.ypdaic.mymall.common.util.*;
import com.ypdaic.mymall.fegin.order.IOrderFeginService;
import com.ypdaic.mymall.fegin.product.IProductFeignService;
import com.ypdaic.mymall.ware.entity.WareOrderTask;
import com.ypdaic.mymall.ware.entity.WareOrderTaskDetail;
import com.ypdaic.mymall.ware.entity.WareSku;
import com.ypdaic.mymall.ware.mapper.WareSkuMapper;
import com.ypdaic.mymall.ware.service.IWareOrderTaskDetailService;
import com.ypdaic.mymall.ware.service.IWareOrderTaskService;
import com.ypdaic.mymall.ware.service.IWareSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypdaic.mymall.ware.vo.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ypdaic.mymall.common.enums.EnableEnum;

/**
 * <p>
 * 商品库存 服务实现类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-11
 */
@Slf4j
@Service
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper, WareSku> implements IWareSkuService {

    @Autowired
    WareSkuMapper wareSkuDao;

    @Autowired
    IProductFeignService productFeignService;

    @Autowired
    IWareOrderTaskService wareOrderTaskService;

    @Autowired
    IWareOrderTaskDetailService wareOrderTaskDetailService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    IOrderFeginService orderFeginService;

    /**
     * 新增商品库存
     * @param wareSkuDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareSku add(WareSkuDto wareSkuDto) {

        WareSku wareSku = new WareSku();
        wareSku.setSkuId(wareSkuDto.getSkuId());
        wareSku.setWareId(wareSkuDto.getWareId());
        wareSku.setStock(wareSkuDto.getStock());
        wareSku.setSkuName(wareSkuDto.getSkuName());
        wareSku.setStockLocked(wareSkuDto.getStockLocked());
        wareSku.insert();
        return wareSku;
    }

    /**
     * 更新商品库存
     * @param wareSkuDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareSku update(WareSkuDto wareSkuDto) {
        WareSku wareSku = baseMapper.selectById(wareSkuDto.getId());
        if (wareSku == null) {
            return null;
        }

        JavaUtils.INSTANCE.acceptIfNotNull(wareSkuDto.getSkuId(), wareSku::setSkuId);
        JavaUtils.INSTANCE.acceptIfNotNull(wareSkuDto.getWareId(), wareSku::setWareId);
        JavaUtils.INSTANCE.acceptIfNotNull(wareSkuDto.getStock(), wareSku::setStock);
        JavaUtils.INSTANCE.acceptIfNotNull(wareSkuDto.getSkuName(), wareSku::setSkuName);
        JavaUtils.INSTANCE.acceptIfNotNull(wareSkuDto.getStockLocked(), wareSku::setStockLocked);
        wareSku.updateById();
        return wareSku;
    }

    /**
     * 删除商品库存
     * @param wareSkuDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WareSku delete(WareSkuDto wareSkuDto) {
        WareSku wareSku = baseMapper.selectById(wareSkuDto.getId());
        if (wareSku == null) {
            return null;
        }
        wareSku.deleteById();

        return wareSku;
    }

    /**
     * 分页查询商品库存
     * @param wareSkuDto
     * @param wareSkuPage
     * @return
     */
    @Override
    public IPage<WareSku> queryPage(WareSkuDto wareSkuDto, Page<WareSku> wareSkuPage) {

        return baseMapper.queryPage(wareSkuPage, wareSkuDto);
    }


    /**
     * 校验商品库存名称
     * @param wareSkuDto
     * @return
     */
    @Override
    public boolean checkName(WareSkuDto wareSkuDto, boolean isAdd) {

        QueryWrapper<WareSku> wareSkuQueryWrapper = new QueryWrapper<WareSku>();
        wareSkuQueryWrapper.ne("enable", EnableEnum.DELETE.getValue());
        wareSkuQueryWrapper.ne("enable", EnableEnum.DELETED.getValue());
        //更新时查询
        if (!isAdd) {
            wareSkuQueryWrapper.ne("id", wareSkuDto.getId());
        }

        Integer count = baseMapper.selectCount(wareSkuQueryWrapper);
        if (count > 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * 查询所有商品库存
     * @return
     */
    @Override
    public List<WareSku> queryAll(WareSkuDto wareSkuDto) {
        return baseMapper.queryAll(wareSkuDto);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        /**
         * skuId: 1
         * wareId: 2
         */
        QueryWrapper<WareSku> queryWrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if(!StringUtils.isEmpty(skuId)){
            queryWrapper.eq("sku_id",skuId);
        }

        String wareId = (String) params.get("wareId");
        if(!StringUtils.isEmpty(wareId)){
            queryWrapper.eq("ware_id",wareId);
        }


        IPage<WareSku> page = this.page(
                new Query<WareSku>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        //1、判断如果还没有这个库存记录新增
        List<WareSku> entities = wareSkuDao.selectList(new QueryWrapper<WareSku>().eq("sku_id", skuId).eq("ware_id", wareId));
        if(entities == null || entities.size() == 0){
            WareSku skuEntity = new WareSku();
            skuEntity.setSkuId(skuId);
            skuEntity.setStock(skuNum);
            skuEntity.setWareId(wareId);
            skuEntity.setStockLocked(0);
            //TODO 远程查询sku的名字，如果失败，整个事务无需回滚
            //1、自己catch异常
            //TODO 还可以用什么办法让异常出现以后不回滚？高级
            try {
                R info = productFeignService.info(skuId);
                Map<String,Object> data = (Map<String, Object>) info.get("skuInfo");

                if(info.getCode() == 0){
                    skuEntity.setSkuName((String) data.get("skuName"));
                }
            }catch (Exception e){

            }


            wareSkuDao.insert(skuEntity);
        }else{
            wareSkuDao.addStock(skuId,wareId,skuNum);
        }

    }

    @Override
    public List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds) {

        List<SkuHasStockVo> skuHasStockVos = skuIds.stream().map(item -> {
            Long count = this.baseMapper.getSkuStock(item);
            SkuHasStockVo skuHasStockVo = new SkuHasStockVo();
            skuHasStockVo.setSkuId(item);
            skuHasStockVo.setHasStock(count == null?false:count > 0);
            return skuHasStockVo;
        }).collect(Collectors.toList());
        return skuHasStockVos;
    }

    /**
     * 锁库存
     * 库存解锁场景：
     * 1. 下订单成功，订单过期没有支付被系统自动取消，被用户手动取消，都需要解锁库存
     * 2. 下订单成功，库存锁定成功，接下来的业务调用失败，导致订单回滚
     *     之前锁定的库存就要自动解锁
     * @param wareSkuLockVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean orderLockStock(WareSkuLockVo wareSkuLockVo) {
        /**
         * 创建库存工作单详情
         */

        WareOrderTask wareOrderTask = new WareOrderTask();
        wareOrderTask.setOrderSn(wareSkuLockVo.getOrderSn());
        wareOrderTaskService.save(wareOrderTask);

        // 1，按照下单的收货地址，找到一个就近的仓库，锁定库存。

        // 1，找到每个商品在那个仓库都有库存
        List<OrderItemVo> locks = wareSkuLockVo.getLocks();
        List<SkuWareHasStock> skuWareHasStocks = locks.stream().map(orderItemVo -> {
            SkuWareHasStock skuWareHasStock = new SkuWareHasStock();
            Long skuId = orderItemVo.getSkuId();
            // 查询这个商品在哪里有库存
            List<Long> wareid = baseMapper.listWareIdHasSkuStock(skuId);
            skuWareHasStock.setWareId(wareid);
            skuWareHasStock.setSkuId(skuId);
            skuWareHasStock.setNum(orderItemVo.getCount());
            return skuWareHasStock;
        }).collect(Collectors.toList());

        // 2.锁定库存
        for (SkuWareHasStock skuWareHasStock: skuWareHasStocks) {
            Boolean skuStocked = false;
            Long skuId = skuWareHasStock.getSkuId();
            List<Long> wareIds = skuWareHasStock.getWareId();
            if (CollectionUtils.isEmpty(wareIds)) {
                log.error("没有库存：{}", skuId.toString());
                // 没有任何仓库有这个商品的库存
                throw new RuntimeException(skuId + "没有库存");
            }
            // 1，如果每一个商品都锁定成功，将当前商品锁定了几件的工作单记录发给mq
            // 2, 锁定失败，前保存的工作单信息就回滚了，发送出去的消息，即使解锁记录，由于去数据库查不到id，所以就不用解锁
            for (Long wareId : wareIds) {
                // 成功就返回1，否则就是0
                Long count = baseMapper.lockSkuStock(skuId, wareId, skuWareHasStock.getNum());
                if (count == 1) {
                    skuStocked = true;

                    // 保存工作单详情
                    WareOrderTaskDetail wareOrderTaskDetail = new WareOrderTaskDetail();
                    wareOrderTaskDetail.setSkuId(skuWareHasStock.getSkuId());
                    wareOrderTaskDetail.setWareId(wareId);
                    wareOrderTaskDetail.setTaskId(wareOrderTask.getId());
                    wareOrderTaskDetail.setSkuNum(skuWareHasStock.getNum());
                    wareOrderTaskDetail.setLockStatus(1);
                    wareOrderTaskDetail.setSkuName("");
                    wareOrderTaskDetailService.save(wareOrderTaskDetail);

                    // TODO 告诉MQ 库存锁定成功
                    StockLockedTo stockLockedTo = new StockLockedTo();
                    stockLockedTo.setId(wareOrderTask.getId());
                    StockDetailTo stockDetailTo = new StockDetailTo();
                    BeanUtils.copyProperties(wareOrderTaskDetail, stockDetailTo);
                    stockLockedTo.setStockDetailTo(stockDetailTo);
                    rabbitTemplate.convertAndSend("stock.event.exchange", "stock.locked", stockLockedTo);
                    break;
                } else {
                    // 当前仓库锁定失败，重试下一个仓库
                    log.error("没有库存，wareId:{}，skuId:{}", wareId.toString(), skuId.toString());
                }

            }

            if (!skuStocked) {
                log.error("锁定库存失败");
                throw new RuntimeException("锁定失败");
            }


        }
        // 全部锁定成功
        return true;
    }

    /**
     * 1. 库存自动解锁
     *  下单成功，库存锁定成功，接下来的业务调用失败，导致订单回滚，之前锁定的库存就要自动解锁。
     *   订单失败，锁定库存失败
     * @param stockLockedTo
     */
    @Override
    public void releaseLockStock(StockLockedTo stockLockedTo) {
        // 库存工作单id
        Long id = stockLockedTo.getId();
        StockDetailTo stockDetailTo = stockLockedTo.getStockDetailTo();
        // 解锁
        // 1.查询数据库关于这个订单的锁定库存信息
        // 有：证明库存锁定成功
        /**
         *    解锁： 订单情况
         *          1. 没有这个订单，必须解锁
         *          2. 有这个订单
         *             订单状态：已取消：解锁库存
         *                      没取消：不能解锁
         */
        // 没有，库存锁定失败了，库存回滚了，这种请情况无需解锁
        WareOrderTaskDetail wareOrderTaskDetail = wareOrderTaskDetailService.getById(stockDetailTo.getId());
        if (Objects.nonNull(wareOrderTaskDetail)) {
            // 解锁
            WareOrderTask wareOrderTask = wareOrderTaskService.getById(id);
            String orderSn = wareOrderTask.getOrderSn();
            R orderStatus = orderFeginService.getOrderStatus(orderSn);
            OrderVo orderVo = orderStatus.getData(new TypeReference<OrderVo>() {
            });
            if (orderStatus.getCode() == 0) {
                if (Objects.isNull(orderVo) || orderVo.getStatus() == 4) {
                    // 当前库存工单详情，状态为已锁定才可以解锁
                    if (wareOrderTaskDetail.getLockStatus() == 1) {
                        // 订单已经取消了，或者订单都没有生成，才能解锁库存
                        unLockStock(stockDetailTo);
                    }
                }
            } else {
                throw new RuntimeException("远程调用失败");
            }
        }

    }

    /**
     * 防止订单服务卡顿，导致订单状态消息一直改不了，库存消息优先到期，查订单状态新建状态，什么都不做就走了
     * 导致卡顿的订单，永远不能解锁库存
     * @param orderTo
     */
    @Transactional
    @Override
    public void unLockStock(OrderTo orderTo) {
        String orderSn = orderTo.getOrderSn();
        // 查询一下最新的库存状态，防止重新解锁库存
        WareOrderTask wareOrderTask = wareOrderTaskService.getOrderTaskByOrderSn(orderSn);
        Long id = wareOrderTask.getId();
        //按照工作单找到所有没有解锁的库存，进行解锁
        WareOrderTaskDetailDto wareOrderTaskDetailDto = new WareOrderTaskDetailDto();
        wareOrderTaskDetailDto.setTaskId(id);
        wareOrderTaskDetailDto.setLockStatus(1);
        List<WareOrderTaskDetail> wareOrderTaskDetails = wareOrderTaskDetailService.queryAll(wareOrderTaskDetailDto);
        wareOrderTaskDetails.forEach(wareOrderTaskDetail -> {
            StockDetailTo stockDetailTo = new StockDetailTo();
            stockDetailTo.setId(wareOrderTaskDetail.getId());
            stockDetailTo.setSkuId(wareOrderTaskDetail.getSkuId());
            stockDetailTo.setWareId(wareOrderTaskDetail.getWareId());
            stockDetailTo.setSkuNum(wareOrderTaskDetail.getSkuNum());
            unLockStock(stockDetailTo);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void unLockStock(StockDetailTo stockDetailTo) {
        // 库存解锁
        baseMapper.unLockStock(stockDetailTo.getSkuId(), stockDetailTo.getWareId(), stockDetailTo.getSkuNum());

        WareOrderTaskDetail wareOrderTaskDetail = new WareOrderTaskDetail();
        wareOrderTaskDetail.setId(stockDetailTo.getId());
        wareOrderTaskDetail.setLockStatus(2);
        wareOrderTaskDetailService.saveOrUpdate(wareOrderTaskDetail);

    }

    @Data
    public static class SkuWareHasStock {
        private Long skuId;

        private List<Long> wareId;

        private Integer num;
    }

}
