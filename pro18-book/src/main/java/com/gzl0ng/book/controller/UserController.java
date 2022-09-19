package com.gzl0ng.book.controller;

import com.gzl0ng.book.pojo.User;
import com.gzl0ng.book.service.UserService;

/**
 * Author: guozhenglong
 * Date:2022/9/19 11:44
 */
public class UserController {
    private UserService userService;

    public String login(String uname,String pwd){
        User user = userService.login(uname, pwd);
        System.out.println(user);

        return "index";
    }
}
