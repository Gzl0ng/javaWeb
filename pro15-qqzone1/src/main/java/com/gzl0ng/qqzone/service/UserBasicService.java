package com.gzl0ng.qqzone.service;

import com.gzl0ng.qqzone.dao.UserBasicDAO;
import com.gzl0ng.qqzone.pojo.UserBasic;
import com.gzl0ng.qqzone.pojo.UserBasic;

import java.util.List;

public interface UserBasicService {
    UserBasic login(String loginId , String pwd );
    List<UserBasic> getFriendList(UserBasic userBasic);
}
