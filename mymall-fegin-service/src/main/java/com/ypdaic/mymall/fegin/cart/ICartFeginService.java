package com.ypdaic.mymall.fegin.cart;

import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.fegin.config.FeginConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "mymall-cart", configuration = {FeginConfig.class})
public interface ICartFeginService {

    @GetMapping("/currentUserCartItems")
    R getCurrentUserCartItems();
}
