package com.ypdaic.mymall.cart.service;

import com.ypdaic.mymall.cart.vo.Cart;
import com.ypdaic.mymall.cart.vo.CartItem;

import java.util.List;

public interface ICartService {

    CartItem addToCard(Long skuId, Integer num);

    CartItem getCartItem(Long skuId);

    Cart getCart();

    void checkItem(Long skuId, Integer check);

    void countItem(Long skuId, Integer num);

    List<CartItem> getUserCartItems();
}
