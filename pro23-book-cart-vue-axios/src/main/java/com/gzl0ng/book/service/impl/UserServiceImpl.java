package com.gzl0ng.book.service.impl;

import com.gzl0ng.book.dao.UserDAO;
import com.gzl0ng.book.pojo.User;
import com.gzl0ng.book.service.UserService;

/**
 * Author: guozhenglong
 * Date:2022/9/19 11:49
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname,pwd);
    }

    @Override
    public void regist(User user) {
        userDAO.addUser(user);
    }

    @Override
    public User getUser(String uname) {
        return userDAO.getUser(uname);
    }
}
