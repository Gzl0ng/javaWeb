package com.gzl0ng.book.dao;

import com.gzl0ng.book.pojo.User;

/**
 * Author: guozhenglong
 * Date:2022/9/19 11:46
 */
public interface UserDAO {
    User getUser(String uname,String pwd);

    void addUser(User user);
    User getUser(String uname);
}
