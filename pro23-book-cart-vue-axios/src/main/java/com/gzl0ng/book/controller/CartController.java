package com.gzl0ng.book.controller;

import com.google.gson.Gson;
import com.gzl0ng.book.pojo.Book;
import com.gzl0ng.book.pojo.Cart;
import com.gzl0ng.book.pojo.CartItem;
import com.gzl0ng.book.pojo.User;
import com.gzl0ng.book.service.CartItemService;

import javax.servlet.http.HttpSession;

/**
 * Author: guozhenglong
 * Date:2022/9/20 14:08
 */
public class CartController {
    private CartItemService cartItemService;

    //加载当前用户的购物车信息
    public String index(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser",user);
        return "cart/cart.html";
    }

    public String addCart(Integer bookId, HttpSession session){
        User user = (User)session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId),1,user);
        //将指定的图书添加到当前用户的购物车中
        cartItemService.addOrUpdateCartItem(cartItem,user.getCart());

        return "redirect:cart.do";
    }

    public String editCart(Integer cartItemId ,Integer buyCount){
         cartItemService.updateCartItem(new CartItem(cartItemId,buyCount));
         return "";
    }

    public String cartInfo(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);

        //调用Cart中的上属性get方法，目的是在此处计算这三个属性的值,否则这三个属性为null
        cart.getTotalBookCount();
        cart.getTotalMoney();
        cart.getTotalBookCount();


        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cart);
        return "json:"+cartJsonStr;
    }
}
