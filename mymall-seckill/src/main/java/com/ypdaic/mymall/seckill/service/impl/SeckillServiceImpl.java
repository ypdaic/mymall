package com.ypdaic.mymall.seckill.service.impl;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ypdaic.mymall.common.to.MemberRespVo;
import com.ypdaic.mymall.common.to.SecKillSkuRedisTo;
import com.ypdaic.mymall.common.to.SeckillOrderTo;
import com.ypdaic.mymall.common.to.SkuInfoVo;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.fegin.coupon.ICouponFeignService;
import com.ypdaic.mymall.fegin.product.IProductFeignService;
import com.ypdaic.mymall.seckill.interceptor.LoginUserInterceptor;
import com.ypdaic.mymall.seckill.service.SeckillService;
import com.ypdaic.mymall.seckill.vo.SeckillSessionVo;
import com.ypdaic.mymall.seckill.vo.SeckillSkuRelationVo;
import org.apache.commons.collections4.CollectionUtils;
import org.redisson.Redisson;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
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

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Override
    public void uploadSeckillSkuLatest3Days() {
        R lates3DaySession = couponFeignService.getLates3DaySession();

        List<SeckillSessionVo> datas = lates3DaySession.getData(new TypeReference<List<SeckillSessionVo>>() {
        });
        if (CollectionUtils.isNotEmpty(datas)) {

            // 缓存到redis
            // 缓存活动信息
            saveSessionInfos(datas);
            // 缓存活动的关联商品信息
            saveSessionSkuInfos(datas);
        }

    }

    @Override
    public List<SecKillSkuRedisTo> getCurrentSeckillSkus() {
        ArrayList<SecKillSkuRedisTo> secKillSkuRedisTos1 = new ArrayList<>();
        // 1.确定当前时间属于哪个秒杀场次
        long time = new Date().getTime();
        Set<String> keys = redisTemplate.keys(SESSIONS_CACHE_PREFIX + "*");
        if (CollectionUtils.isNotEmpty(keys)) {
            for (String key : keys) {
                key = key.replace("SESSIONS_CACHE_PREFIX", "");
                String[] timeStamp = key.split("_");
                Long startTime = 0L;
                Long endTime = 0L;
                if (timeStamp.length == 1) {
                    startTime = Long.valueOf(timeStamp[0]);
                }
                if (timeStamp.length == 2) {
                    endTime = Long.valueOf(timeStamp[1]);
                }

                if (startTime <= time && endTime >= time) {
                    List<String> range = redisTemplate.opsForList().range(key, -100, 100);
                    // 批量查询
                    List<SecKillSkuRedisTo> secKillSkuRedisTos = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX).multiGet(range);
                    secKillSkuRedisTos1.addAll(secKillSkuRedisTos);
                }
            }

        }
        return secKillSkuRedisTos1;
    }

    @Override
    public SecKillSkuRedisTo getSkuSeckillInfo(Long skuId) {
        BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
        Set<String> keys = boundHashOperations.keys();
        if (CollectionUtils.isNotEmpty(keys)) {
            String reg = "\\d_" + skuId;
            for (String key : keys) {

                //6_4

                if (Pattern.matches(reg, key)) {
                    SecKillSkuRedisTo secKillSkuRedisTo = (SecKillSkuRedisTo) boundHashOperations.get(key);

                    //随机码处理
                    Long startTime = secKillSkuRedisTo.getStartTime();
                    Long endTime = secKillSkuRedisTo.getEndTime();

                    long time = new Date().getTime();
                    if (time >= startTime && time <= endTime) {
                        secKillSkuRedisTo.setRandomCode("");
                    }
                    return secKillSkuRedisTo;
                }
            }
        }
        return null;
    }


    @Override
    public String kill(String killId, String key, Integer num) {
        MemberRespVo memberRespVo = LoginUserInterceptor.threadLocal.get();

        // 1,判断是否登录

        // 获取当前秒杀商品的详细信息
        SecKillSkuRedisTo secKillSkuRedisTo = (SecKillSkuRedisTo) redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX).get(key);
        if (Objects.nonNull(secKillSkuRedisTo)) {
            // 校验合法性
            Long startTime = secKillSkuRedisTo.getStartTime();
            Long endTime = secKillSkuRedisTo.getEndTime();
            long time = new Date().getTime();

            long ttl = endTime - time;

            // 校验时间合法性
            if (startTime <= time && time <= endTime) {
                // 2. 校验随机码
                String randomCode = secKillSkuRedisTo.getRandomCode();
                String skuId = secKillSkuRedisTo.getPromotionSessionId() + "_" + secKillSkuRedisTo.getSkuId();
                if (randomCode.equals(key) && killId.equals(skuId)) {
                    // 3, 验证购物数量是否合理
                    BigDecimal seckillLimit = secKillSkuRedisTo.getSeckillLimit();
                    if (num <= seckillLimit.intValue()) {
                        // 4, 验证这个人是否买过了
                        String redisKey = memberRespVo.getId() + "_" + skuId;
                        // 自动过期
                        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(redisKey, num.toString(), ttl, TimeUnit.MILLISECONDS);
                        if (aBoolean) {
                            // 站位成功 说明从来没有买过
                            RSemaphore semaphore = redisson.getSemaphore(SKU_STOCK_SEMAPHORE + randomCode);

                            // 尝试秒杀
                            try {
                                boolean b = semaphore.tryAcquire(num, 100, TimeUnit.MILLISECONDS);
                                // 秒杀成功
                                // 快速下单，发送mq消息，10ms
                                if(b) {
                                    // 订单号
                                    String timeId = IdWorker.getTimeId();
                                    SeckillOrderTo seckillOrderTo = new SeckillOrderTo();
                                    seckillOrderTo.setOrderSn(timeId);
                                    seckillOrderTo.setMemberId(memberRespVo.getId());
                                    seckillOrderTo.setNum(num);
                                    seckillOrderTo.setPromotionSessionId(secKillSkuRedisTo.getPromotionSessionId());
                                    seckillOrderTo.setSeckillPrice(secKillSkuRedisTo.getSeckillPrice());
                                    seckillOrderTo.setSkuId(secKillSkuRedisTo.getSkuId());
                                    rabbitTemplate.convertAndSend("order.event.exchange", "order.seckill.order", seckillOrderTo );
                                    return timeId;
                                }
                            } catch (InterruptedException e) {
                                return null;
                            }


                        }
                    }
                }
            }
        }
        return null;
    }

    private void saveSessionInfos(List<SeckillSessionVo> data) {
        data.forEach(seckillSessionVo -> {
            Date startTime = seckillSessionVo.getStartTime();
            Date endTime = seckillSessionVo.getEndTime();

            long time = startTime.getTime();
            long time1 = endTime.getTime();

            String key = SESSIONS_CACHE_PREFIX + time + "_" + time1;

            // 上架过就不用再次上架了
            Boolean aBoolean = redisTemplate.hasKey(key);
            if (!aBoolean) {
                List<String> collect = seckillSessionVo.getList().stream().map(seckillSkuRelationVo -> {
                    String sessionSkuKey = seckillSkuRelationVo.getPromotionSessionId() + "_" + seckillSkuRelationVo.getSkuId();
                    return sessionSkuKey;
                }).collect(Collectors.toList());
                // 缓存活动信息
                redisTemplate.opsForList().leftPushAll(key, collect);
            }




        });
    }

    private void saveSessionSkuInfos(List<SeckillSessionVo> data) {
        data.stream().forEach(seckillSessionVo -> {
            BoundHashOperations boundHashOperations = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);

            seckillSessionVo.getList().stream().forEach(seckillSkuRelationVo -> {
                // 不存在就放入缓存
                if (!boundHashOperations.hasKey(seckillSkuRelationVo.getPromotionSessionId() + "_" + seckillSkuRelationVo.getSkuId().toString())) {
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
                    boundHashOperations.put(seckillSkuRelationVo.getPromotionSessionId() + "_" + seckillSkuRelationVo.getSkuId().toString(), secKillSkuRedisTo);
                }

            });

        });


    }
}
