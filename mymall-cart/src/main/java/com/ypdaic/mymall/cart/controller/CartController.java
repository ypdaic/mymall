package com.ypdaic.mymall.cart.controller;

import com.sun.org.apache.bcel.internal.generic.LLOAD;
import com.ypdaic.mymall.cart.interceptor.CartInterceptor;
import com.ypdaic.mymall.cart.service.ICartService;
import com.ypdaic.mymall.cart.vo.Cart;
import com.ypdaic.mymall.cart.vo.CartItem;
import com.ypdaic.mymall.cart.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping
public class CartController {

    @Autowired
    ICartService cartService;

    /**
     * 浏览器有一个cookie，user-key: 标识用户身份，一个月后过期
     * 如果第一次使用京东的购物车功能，都会给一个临时的用户身份
     * 浏览器以后保存，每次访问都会带上这个cookie
     *
     * 登录：session有
     * 没登录，按照cookie里面带来的user-key来做。
     * 第一次；如果没有临时用户，帮忙创建一个临时用户。
     * @return
     */
    @GetMapping("/cart.html")
    public String cartListPage(Model model) {
        Cart cart = cartService.getCart();

        model.addAttribute("cart", cart);
        return "cartList";
    }

    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("num") Integer num, @RequestParam("skuId") Long skuId, RedirectAttributes redirectAttributes) {

        CartItem cartItem = cartService.addToCard(skuId, num);

        redirectAttributes.addAttribute("skuId", skuId);
        return "redirect:http://cart.mymall.com/addToCardSuccess.html";
    }

    /**
     * 前端如果刷新的话，就只是展示了，而不会触发再次添加到购物车的问题
     * @param skuId
     * @param model
     * @return
     */
    @GetMapping("/addToCardSuccess.html")
    public String addToCardSuccessPage(@RequestParam("skuId") Long skuId, Model model) {
        // 重定向到成功页面，再次查询购物车数据即可
        CartItem cartItem = cartService.getCartItem(skuId);
        model.addAttribute("item", cartItem);
        if(log.isDebugEnabled()) {
            log.debug("日志开启了debug模式");
        }
        return "success";
    }

    @GetMapping("/checkItem")
    public String checkItem(@RequestParam("skuId") Long skuId, @RequestParam("check") Integer check) {
        cartService.checkItem(skuId, check);

        return "redirect:http://cart.mymall.com/cart.html";
    }

    @GetMapping("/countItem")
    public String countItem(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        cartService.countItem(skuId, num);

        return "redirect:http://cart.mymall.com/cart.html";
    }


}
