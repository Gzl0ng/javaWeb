package com.gzl0ng.book.controller;

import com.gzl0ng.book.pojo.Book;
import com.gzl0ng.book.pojo.User;
import com.gzl0ng.book.service.BookService;
import com.gzl0ng.book.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/9/19 11:44
 */
public class UserController {
    private UserService userService;

    public String login(String uname, String pwd, HttpSession session){
        User user = userService.login(uname, pwd);
        System.out.println("user = " + user);
        if (user!= null){
            session.setAttribute("currUser",user);
            return "redirect:book.do?";
        }

        return "user/login";
    }
}
