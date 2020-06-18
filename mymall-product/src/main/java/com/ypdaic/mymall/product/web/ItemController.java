package com.ypdaic.mymall.product.web;

import com.ypdaic.mymall.product.service.ISkuInfoService;
import com.ypdaic.mymall.product.vo.SkuItemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class ItemController {

    @Autowired
    ISkuInfoService skuInfoService;

    /**
     * 根据skuId取得商品的详情信息
     * @param skuId
     * @return
     */
    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable("skuId") Long skuId, Model model){
        SkuItemVo vo=skuInfoService.item(skuId);
        System.out.println(vo);
        model.addAttribute("item",vo);
        return "item";
    }
}
