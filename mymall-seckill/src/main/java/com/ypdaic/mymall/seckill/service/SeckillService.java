package com.ypdaic.mymall.seckill.service;

import com.ypdaic.mymall.common.to.SecKillSkuRedisTo;
import com.ypdaic.mymall.seckill.vo.SeckillSessionVo;

import java.util.List;

public interface SeckillService {
    void uploadSeckillSkuLatest3Days();

    List<SecKillSkuRedisTo> getCurrentSeckillSkus();

    SecKillSkuRedisTo getSkuSeckillInfo(Long skuId);

    String kill(String killId, String key, Integer num);
}
