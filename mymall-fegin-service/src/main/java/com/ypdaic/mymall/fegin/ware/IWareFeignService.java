package com.ypdaic.mymall.fegin.ware;

import com.ypdaic.mymall.common.to.WareSkuLockVo;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.fegin.config.FeginConfig;
import com.ypdaic.mymall.fegin.config.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mymall-ware", configuration = {FeginConfig.class})
public interface IWareFeignService {

    @PostMapping("/ware/waresku/hasStock")
    R getSkuHasStock(@RequestBody List<Long> skuIds);

    @GetMapping("/ware/wareinfo/fare")
    // 不可使用hystrix提供的注解，会被aop生成代理类，导致feign调用失败，需要使用自定义的注解
    @HystrixCommand(commandKey = "test")
    R getFare(@RequestParam("addrId") Long addrId);

    @PostMapping("/ware/waresku/lock/order")
    R orderLockStock(@RequestBody WareSkuLockVo wareSkuLockVo);

    @PostMapping("/ware/waresku/seata/test")
    R seateTest();
}
