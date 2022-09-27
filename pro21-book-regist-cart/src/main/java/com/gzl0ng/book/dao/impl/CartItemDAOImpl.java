package com.gzl0ng.book.dao.impl;

import com.gzl0ng.book.dao.CartItemDAO;
import com.gzl0ng.book.pojo.CartItem;
import com.gzl0ng.book.pojo.User;
import com.gzl0ng.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/9/20 14:11
 */
public class CartItemDAOImpl extends BaseDAO<CartItem> implements CartItemDAO {

    @Override
    public void addCartItem(CartItem cartItem) {
        executeUpdate("insert into t_cart_item values(0,?,?,?)",cartItem.getBook().getId(),cartItem.getBuyCount(),cartItem.getUesrBean().getId());
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        executeUpdate("update t_cart_item set buyCount = ? where id = ?",cartItem.getBuyCount(),cartItem.getId());
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        return executeQuery("select * from t_cart_item where userBean = ?",user.getId());
    }

    @Override
    public void delCartItem(CartItem cartItem) {
        super.executeUpdate("delete from t_cart_item where id = ?",cartItem.getId());
    }
}

