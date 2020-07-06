package com.ypdaic.mymall.seckill.service.impl;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.TypeReference;
import com.ypdaic.mymall.common.to.SecKillSkuRedisTo;
import com.ypdaic.mymall.common.to.SkuInfoVo;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.fegin.coupon.ICouponFeignService;
import com.ypdaic.mymall.fegin.product.IProductFeignService;
import com.ypdaic.mymall.seckill.service.SeckillService;
import com.ypdaic.mymall.seckill.vo.SeckillSessionVo;
import com.ypdaic.mymall.seckill.vo.SeckillSkuRelationVo;
import org.redisson.Redisson;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    ICouponFeignService couponFeignService;

    private final String SESSIONS_CACHE_PREFIX = "seckill:sessions:";

    private final String SKUKILL_CACHE_PREFIX = "seckill:skus";

    private final String SKU_STOCK_SEMAPHORE = "seckill:stock:";

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    IProductFeignService productFeignService;

    @Autowired
    RedissonClient redisson;



    @Override
    public void uploadSeckillSkuLatest3Days() {
        R lates3DaySession = couponFeignService.getLates3DaySession();

        List<SeckillSessionVo> datas = lates3DaySession.getData(new TypeReference<List<SeckillSessionVo>>() {
        });

        // 缓存到redis
        // 缓存活动信息
        saveSessionInfos(datas);
        // 缓存活动的关联商品信息
        saveSessionSkuInfos(datas);

    }

    private void saveSessionInfos(List<SeckillSessionVo> data) {
        data.forEach(seckillSessionVo -> {
            Date startTime = seckillSessionVo.getStartTime();
            Date endTime = seckillSessionVo.getEndTime();

            long time = startTime.getTime();
            long time1 = endTime.getTime();

            String key = SESSIONS_CACHE_PREFIX + time + "_" + time1;
            List<Long> collect = seckillSessionVo.getList().stream().map(SeckillSkuRelationVo::getSkuId).collect(Collectors.toList());
            // 缓存活动信息
            redisTemplate.opsForList().leftPushAll(key, collect);


        });
    }

    private void saveSessionSkuInfos(List<SeckillSessionVo> data) {
        data.stream().forEach(seckillSessionVo -> {
            BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);

            seckillSessionVo.getList().stream().forEach(seckillSkuRelationVo -> {
                //缓存商品
                // 1. sku的基本数据
                SecKillSkuRedisTo secKillSkuRedisTo = new SecKillSkuRedisTo();
                BeanUtils.copyProperties(seckillSkuRelationVo, secKillSkuRedisTo);
                R skuinfo = productFeignService.skuinfo(secKillSkuRedisTo.getSkuId());
                SkuInfoVo data1 = skuinfo.getData(new TypeReference<SkuInfoVo>() {
                });
                secKillSkuRedisTo.setSkuInfoVo(data1);



                // 2. sku的秒杀信息
                // 3.设置当前商品的秒杀时间信息
                secKillSkuRedisTo.setStartTime(seckillSessionVo.getStartTime().getTime());
                secKillSkuRedisTo.setEndTime(seckillSessionVo.getEndTime().getTime());
                String token = UUID.fastUUID().toString();
                // 4. 随机码
                secKillSkuRedisTo.setRandomCode(token);

                RSemaphore semaphore = redisson.getSemaphore(SKU_STOCK_SEMAPHORE + token);
                semaphore.trySetPermits(seckillSkuRelationVo.getSeckillCount());
                boundHashOperations.put(seckillSkuRelationVo.getSkuId().toString(), secKillSkuRedisTo);
            });

        });


    }
}
