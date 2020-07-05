package com.ypdaic.mymall.seckill.scheduled;

import com.ypdaic.mymall.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SeckillSkuScheduled {

    @Autowired
    SeckillService seckillService;

    /**
     * 每天晚上3点，上架最近3天需要秒杀的商品
     * 当天00:00:00 - 23:59:59
     * 明天00:00:00 - 23:59:59
     * 后天00:00:00 - 23:59:59
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void uploadSeckillSkuLatest3Days() {
        /**
         * 1. 处理重复上架功能
         */

        seckillService.uploadSeckillSkuLatest3Days();

    }

}
