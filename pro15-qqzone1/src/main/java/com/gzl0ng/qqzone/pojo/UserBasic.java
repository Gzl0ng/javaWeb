package com.gzl0ng.qqzone.pojo;

import lombok.Data;

import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/9/8 14:44
 */
//用户基本信息
@Data
public class UserBasic {
    private Integer id ;
    private String loginId ;
    private String nickName ;
    private String pwd ;
    private String headImg ;

    private UserDetail userDetail ;     //1:1
    private List<Topic> topicList ;     //1:N
    private List<UserBasic> friendList ;//M:N

    public UserBasic(){}

    public UserBasic(Integer id){
        this.id = id;
    }
}
