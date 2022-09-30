package com.gzl0ng.book.service;

import com.gzl0ng.book.pojo.User;

/**
 * Author: guozhenglong
 * Date:2022/9/19 11:48
 */
public interface UserService {
    User login(String uname,String pwd);
    void regist(User user);
    User getUser(String uname);
}
