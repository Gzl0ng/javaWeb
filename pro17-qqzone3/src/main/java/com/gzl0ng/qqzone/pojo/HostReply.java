package com.gzl0ng.qqzone.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Author: guozhenglong
 * Date:2022/9/8 14:45
 */
//主人回复
    @Data
public class HostReply {
    private Integer id ;
    private String content ;
    private Date hostReplyDate ;
    private UserBasic author ; //M:1
    private Reply reply ;   //1:1

    public HostReply(){}
    public HostReply(Integer id){
        this.id=id;
    }
}
