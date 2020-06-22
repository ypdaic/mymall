package com.ypdaic.mymall.fegin.ware;

import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.fegin.config.FeginConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "mymall-ware", configuration = {FeginConfig.class})
public interface IWareFeignService {

    @PostMapping("/ware/waresku/hasStock")
    R getSkuHasStock(@RequestBody List<Long> skuIds);

}
