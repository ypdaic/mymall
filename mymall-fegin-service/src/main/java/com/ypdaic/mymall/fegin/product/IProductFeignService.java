package com.ypdaic.mymall.fegin.product;

import com.ypdaic.mymall.common.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "mymall-product")
public interface IProductFeignService {

    @RequestMapping("/product/skuinfo/info/{skuId}")
    R info(Long skuId);
}
