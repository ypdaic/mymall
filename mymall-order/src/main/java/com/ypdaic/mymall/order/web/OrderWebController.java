package com.ypdaic.mymall.order.web;

import cn.hutool.core.lang.UUID;
import com.ypdaic.mymall.order.service.IOrderService;
import com.ypdaic.mymall.order.vo.OrderConfirmVo;
import com.ypdaic.mymall.order.vo.OrderSubmitVo;
import com.ypdaic.mymall.order.vo.SubmitResponVo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderWebController {

    @Autowired
    IOrderService orderService;

    @GetMapping("/toTrade")
    public String toTrade(Model model) {


        OrderConfirmVo orderConfirmVo = orderService.confirmOrder();

        model.addAttribute("orderConfirmData", orderConfirmVo);
        return "confirm";
    }

    /**
     * 提交订 单
     * @param
     * @return
     */
    @PostMapping("/submitOrder")
    public String submitOrder(OrderSubmitVo orderSubmitVo, Model model, RedirectAttributes redirectAttributes) {

        SubmitResponVo submitResponVo = orderService.submitOrder(orderSubmitVo);
       // 下单，去创建订单，验令牌，验价格，锁库存
        // 下单成功来到支付选择页
        // 下单失败回到订单确认页重新确认订单信息

        if (submitResponVo.getCode() == 0) {
            model.addAttribute("submitOrderResp", submitResponVo);
            return "pay";
        } else {
            String msg = "下单失败；";
            switch (submitResponVo.getCode()) {
                case 1:
                      msg += "订单信息过期，请刷新再次提交";
                      break;
                case 2:
                    msg += "订单商品价格发生变化，请确认后再次提交";
                    break;
                case 3:
                    msg += "库存锁定失败，商品库存不足";
                    break;
            }
            redirectAttributes.addFlashAttribute("msg", msg);
            return "redirect:http://order.mymall.com/toTrade";
        }
    }
}
