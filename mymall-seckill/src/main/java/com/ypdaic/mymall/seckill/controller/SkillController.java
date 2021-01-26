package com.ypdaic.mymall.seckill.controller;

import com.ypdaic.mymall.common.to.SecKillSkuRedisTo;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.seckill.service.SeckillService;
import com.ypdaic.mymall.seckill.vo.SeckillSessionVo;
import org.redisson.RedissonStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillController {

    @Autowired
    SeckillService seckillService;

    @Autowired
    RedisTemplate redisTemplate;

    private final String SKUKILL_CACHE_PREFIX = "seckill:skus";

    /**
     * 返回当前时间可以秒杀的商品
     * @return
     */
    @GetMapping("/getCurrentSeckillSkus")
    public R getCurrentSeckillSkus() {
        List<SecKillSkuRedisTo> currentSeckillSkus = seckillService.getCurrentSeckillSkus();
        return R.ok().setData(currentSeckillSkus);
    }

    /**
     * 返回当前时间可以秒杀的商品
     * @return
     */
    @GetMapping("/sku/seckill/{skuId}")
    public R getSkuSeckillInfo(@PathVariable("skuId") Long skuId) {
        SecKillSkuRedisTo secKillSkuRedisTo = seckillService.getSkuSeckillInfo(skuId);
        return R.ok().setData(secKillSkuRedisTo);
    }

    /**
     * 秒杀请求
     * @return
     */
    @GetMapping("/kill")
    public R seckill(@RequestParam("killId") String killId, @RequestParam("key") String key, @RequestParam("num") Integer num) {

        String result = seckillService.kill(killId, key, num);
        return null;
    }
}
