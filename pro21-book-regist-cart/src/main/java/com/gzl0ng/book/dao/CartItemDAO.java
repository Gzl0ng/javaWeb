package com.gzl0ng.book.dao;

import com.gzl0ng.book.pojo.CartItem;
import com.gzl0ng.book.pojo.User;

import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/9/20 14:11
 */
public interface CartItemDAO {
    //新增购物车项
    void addCartItem(CartItem cartItem);
    //修改特定的购物车项
    void updateCartItem(CartItem cartItem);
    //获取特定用户的所有购物车项
    List<CartItem> getCartItemList(User user);
    //删除特定的购物车项
    void delCartItem(CartItem cartItem);
}

