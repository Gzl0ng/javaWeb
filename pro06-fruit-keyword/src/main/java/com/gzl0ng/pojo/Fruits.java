package com.gzl0ng.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Author: guozhenglong
 * Date:2022/8/29 13:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Fruits {

    private Integer fid;


    private String fname;


    private Integer price;


    private Integer fcount;

    private String remark;
}
