package com.ypdaic.mymall.seckill.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.fegin.coupon.ICouponFeignService;
import com.ypdaic.mymall.seckill.service.SeckillService;
import com.ypdaic.mymall.seckill.vo.SeckillSessionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    ICouponFeignService couponFeignService;

    @Override
    public void uploadSeckillSkuLatest3Days() {
        R lates3DaySession = couponFeignService.getLates3DaySession();

        SeckillSessionVo data = lates3DaySession.getData(new TypeReference<SeckillSessionVo>() {
        });

        // 缓存到redis
        // 缓存活动信息
        saveSessionInfos(data);
        // 缓存活动的关联商品信息


    }

    private void saveSessionInfos(SeckillSessionVo data) {

    }

//    private void saveSessionSkuInfos();
}
