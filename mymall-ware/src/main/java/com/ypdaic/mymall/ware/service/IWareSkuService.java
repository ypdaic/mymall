package com.ypdaic.mymall.ware.service;

import com.rabbitmq.client.Channel;
import com.ypdaic.mymall.common.to.mq.OrderTo;
import com.ypdaic.mymall.common.to.mq.StockLockedTo;
import com.ypdaic.mymall.common.util.PageUtils;
import com.ypdaic.mymall.ware.entity.WareSku;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypdaic.mymall.ware.vo.LockStockResult;
import com.ypdaic.mymall.ware.vo.SkuHasStockVo;
import com.ypdaic.mymall.ware.vo.WareSkuDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ypdaic.mymall.ware.vo.WareSkuLockVo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 商品库存 服务类
 * </p>
 *
 * @author daiyanping
 * @since 2020-06-11
 */
public interface IWareSkuService extends IService<WareSku> {

    /**
     * 新增商品库存
     * @param wareSkuDto
     * @return
     */
    WareSku add(WareSkuDto wareSkuDto);

    /**
     * 更新商品库存
     * @param wareSkuDto
     * @return
     */
    WareSku update(WareSkuDto wareSkuDto);

    /**
     * 删除商品库存
     * @param wareSkuDto
     * @return
     */
    WareSku delete(WareSkuDto wareSkuDto);

    /**
     * 分页查询商品库存
     * @param wareSkuDto
     * @param wareSkuPage
     * @return
     */
    IPage<WareSku> queryPage(WareSkuDto wareSkuDto, Page<WareSku> wareSkuPage);


    /**
     * 校验商品库存名称
     * @param wareSkuDto
     * @return
     */
    boolean checkName(WareSkuDto wareSkuDto, boolean isAdd);

    /**
     *
     * 查询所有商品库存
     * @return
     */
    List<WareSku> queryAll(WareSkuDto wareSkuDto);

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);

    Boolean orderLockStock(WareSkuLockVo wareSkuLockVo);

    /**
     * 解锁库存
     */
    void releaseLockStock(StockLockedTo stockLockedTo);

    void unLockStock(OrderTo orderTo);
}
