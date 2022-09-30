package com.gzl0ng.book.pojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * Author: guozhenglong
 * Date:2022/9/20 14:23
 */

//这个不是对应数据库的一张表
public class Cart {
    private Map<Integer,CartItem> cartItemMap;  //购物车中购物车项的集合,这个集合中key是book的id
    private Double totalMoney;      //购物车的总金额
    private Integer totalCount;    //购物车中购物项的数量
    private Integer totalBookCount; //购物车中书本的总数量

    public Cart() {
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        totalMoney = 0.0;
        if (cartItemMap!=null && cartItemMap.size()>0){
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer, CartItem> cartItemEntry : entries) {
                CartItem cartItem = cartItemEntry.getValue();
                BigDecimal bigDecimalXj = new BigDecimal(cartItem.getXj());
                totalMoney += bigDecimalXj.doubleValue();
//                totalMoney += cartItem.getBook().getPrice() * cartItem.getBuyCount();
            }
        }
//        return totalMoney;
        return totalMoney;
    }

    public Integer getTotalCount() {
        totalCount=0;
        if (cartItemMap!=null && cartItemMap.size()>0){
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }

    public Integer getTotalBookCount() {
        totalBookCount=0;
        if (cartItemMap!=null && cartItemMap.size()>0){
            for (CartItem cartItem : cartItemMap.values()) {
                totalBookCount+=cartItem.getBuyCount();
            }
        }
        return totalBookCount;
    }
}
