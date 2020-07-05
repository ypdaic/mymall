package com.ypdaic.mymall.fegin.order;

import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.fegin.config.FeginConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mymall-order", configuration = {FeginConfig.class})
public interface IOrderFeginService {

    @GetMapping("/order/order/status/{orderSn}")
    R getOrderStatus(@PathVariable("orderSn") String orderSn);
}
