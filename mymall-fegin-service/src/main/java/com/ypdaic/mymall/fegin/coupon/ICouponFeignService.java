package com.ypdaic.mymall.fegin.coupon;

import com.ypdaic.mymall.common.to.SkuReductionTo;
import com.ypdaic.mymall.common.to.SpuBoundTo;
import com.ypdaic.mymall.common.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "mymall-coupon")
public interface ICouponFeignService {

    @PostMapping("/coupon/spubounds/save")
    R saveSpuBounds(SpuBoundTo spuBoundTo);

    @PostMapping("/coupon/skufullreduction/saveinfo")
    R saveSkuReduction(SkuReductionTo skuReductionTo);

    @RequestMapping("/coupon/coupon/member/list")
    R membercoupons();
}
