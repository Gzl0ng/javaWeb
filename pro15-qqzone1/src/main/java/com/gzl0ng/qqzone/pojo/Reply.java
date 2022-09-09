package com.gzl0ng.qqzone.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Author: guozhenglong
 * Date:2022/9/8 14:44
 */

//回复信息
    @Data
public class Reply {
    private Integer id ;
    private String content ;
    private Date replyDate ;
    private UserBasic author ;  //M:1
    private Topic topic ;       //M:1

    private HostReply hostReply ;   //1:1

    public Reply(){}
}
