package com.ypdaic.mymall.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ypdaic.mymall.order.service.DubboTestService;

//@org.apache.dubbo.config.annotation.Service
public class DubboTestServiceImpl implements DubboTestService {

    @Override
    public JSONObject test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "hello");
        return jsonObject;
    }
}
