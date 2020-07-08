package com.ypdaic.mymall.product.web;

import com.netflix.loadbalancer.ILoadBalancer;
import com.ypdaic.mymall.common.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.cloud.netflix.ribbon.RibbonHttpRequest;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.netflix.ribbon.apache.RetryableRibbonLoadBalancingHttpClient;
import org.springframework.cloud.netflix.ribbon.okhttp.RetryableOkHttpLoadBalancingClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping
public class RibbonController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SpringClientFactory springClientFactory;

    @ResponseBody
    @GetMapping("/test/ribbon")
    public String test() {
//        RetryableOkHttpLoadBalancingClient instance = springClientFactory.getInstance("mymall-ware", RetryableOkHttpLoadBalancingClient.class);
//        RibbonClientHttpRequestFactory ribbonClientHttpRequestFactory = new RibbonClientHttpRequestFactory(springClientFactory);
//        ribbonClientHttpRequestFactory.createRequest("http://mymall-ware/ware/wareinfo/fare", HttpMethod.GET);

//        restTemplate.getForEntity("http://mymall-ware/ware/wareinfo/fare", R.class, 1);
        return "ok";
    }


}
