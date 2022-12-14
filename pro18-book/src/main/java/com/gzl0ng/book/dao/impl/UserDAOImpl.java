package com.gzl0ng.book.dao.impl;

import com.gzl0ng.book.dao.UserDAO;
import com.gzl0ng.book.pojo.User;
import com.gzl0ng.myssm.basedao.BaseDAO;

/**
 * Author: guozhenglong
 * Date:2022/9/19 11:47
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) {
        return load("select * from t_user where uname like ? and pwd like ?",uname,pwd);
    }
}
