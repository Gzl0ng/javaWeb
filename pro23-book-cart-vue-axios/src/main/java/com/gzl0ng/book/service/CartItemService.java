package com.gzl0ng.book.service;

import com.gzl0ng.book.pojo.Cart;
import com.gzl0ng.book.pojo.CartItem;
import com.gzl0ng.book.pojo.User;

import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/9/20 14:18
 */
public interface CartItemService {
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    //获取指定用户的所有购物车项列表（需要注意的是，这个方法内部查询的时候会将book的详细信息设置进去）
    List<CartItem> getCartItemList(User user);
    //加载特定用户的购物车信息
    Cart getCart(User user);
}
