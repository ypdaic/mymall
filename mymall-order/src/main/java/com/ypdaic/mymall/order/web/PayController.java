package com.ypdaic.mymall.order.web;

import com.alipay.api.AlipayApiException;
import com.ypdaic.mymall.order.config.AlipayTemplate;
import com.ypdaic.mymall.order.service.IOrderService;
import com.ypdaic.mymall.order.vo.PayVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class PayController {

    @Autowired
    AlipayTemplate alipayTemplate;

    @Autowired
    IOrderService orderService;

    @ResponseBody
    @GetMapping(value = "/payOrder", produces = "text/html")
    public String paryOrder(@RequestParam("orderSn") String orderSn) {

//        PayVo payVo = new PayVo();
//        payVo.setBody();
//        payVo.setOut_trade_no();
//        payVo.setSubject();
//        payVo.setTotal_amount();

        PayVo payVo = orderService.getOrderPay(orderSn);
        try {
            // 返回的是一个页面，将该页面交给浏览器执行
            String pay = alipayTemplate.pay(payVo);
            return pay;
        } catch (AlipayApiException e) {
            log.error("订单支付异常", e);
            throw new RuntimeException("订单支付异常");
        }
    }
}
