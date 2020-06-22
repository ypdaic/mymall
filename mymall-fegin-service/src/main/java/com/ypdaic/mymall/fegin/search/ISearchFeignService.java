package com.ypdaic.mymall.fegin.search;

import com.ypdaic.mymall.common.to.es.SkuEsModel;
import com.ypdaic.mymall.common.util.R;
import com.ypdaic.mymall.fegin.config.FeginConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "mymall-search", configuration = {FeginConfig.class})
public interface ISearchFeignService {

    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
