package com.gzl0ng.qqzone.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/9/8 14:44
 */
//日志
@Data
public class Topic {
    private Integer id ;
    private String title ;
    private String content ;
    private Date topicDate ;
    private UserBasic author ;          //M:1

    private List<Reply> replyList ;     //1:N

    public Topic(){}

    public Topic(Integer id){
        this.id=id;
    }

    public Integer getId() {
        return id;
    }

}
