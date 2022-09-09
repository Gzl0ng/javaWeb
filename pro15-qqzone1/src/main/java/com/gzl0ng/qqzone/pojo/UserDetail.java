package com.gzl0ng.qqzone.pojo;

import lombok.Data;

import java.sql.Date;

/**
 * Author: guozhenglong
 * Date:2022/9/8 14:44
 */
//用户详细信息
@Data
public class UserDetail {
    private Integer id ;
    private String realName ;
    private String tel ;
    private String email ;
    private Date birth ;
    private String star ;

    public UserDetail(){}

    public Integer getId() {
        return id;
    }

}
//父类：java.util.Date 年月日时分秒毫秒
//子类：java.sql.Date 年月日
//子类：java.sql.Time 时分秒