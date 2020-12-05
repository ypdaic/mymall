package com.ypdaic.mymall.product.web;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * openresty nginx本地缓存测试
 */
@Slf4j
@RestController
@RequestMapping("/openresty")
public class OpenrestyController {



    @Transactional
    @GetMapping("/product")
    public JSONObject product(@RequestParam("productId") Integer productId) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", productId);
        log.info("product开始请求后端");
        return jsonObject;
    }

    @Async
    @GetMapping("/shop")
    public JSONObject shop(@RequestParam("shopId") Integer shopId) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", shopId);
        log.info("shop开始请求后端");
        return jsonObject;
    }
}
