package com.gzl0ng.book.controller;

import com.gzl0ng.book.pojo.Book;
import com.gzl0ng.book.pojo.Cart;
import com.gzl0ng.book.pojo.User;
import com.gzl0ng.book.service.BookService;
import com.gzl0ng.book.service.CartItemService;
import com.gzl0ng.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/9/19 11:44
 */
public class UserController {
    private UserService userService;
    private CartItemService cartItemService;

    public String login(String uname, String pwd, HttpSession session){
        User user = userService.login(uname, pwd);
        System.out.println("user = " + user);
        if (user!= null){
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);

            session.setAttribute("currUser",user);
            return "redirect:book.do?";
        }

        return "user/login";
    }

    public String regist(String verifyCode, String uname, String pwd, String email, HttpSession session, HttpServletResponse response) throws IOException {
        Object kaptchaCodeObj = session.getAttribute("KAPTCHA_SESSION_KEY");
        if (kaptchaCodeObj == null || !verifyCode.equals(kaptchaCodeObj)){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
//            out.println("<script language='javascript'>alert('验证码不正确!');window.location.href='page.do?operate=page&page=user/regist';</script>");
            out.println("<script language='javascript'>alert('验证码不正确!');</script>");
//            return "user/regist";
            return "user/regist";

//            一般来说out输出和跳转只能2选一，这里暂时都选能成功
        }else {
            if (verifyCode.equals(kaptchaCodeObj)){
                userService.regist(new User(uname,pwd,email,0));
                return "user/login";
            }
        }

        return "user/login";
    }
}
