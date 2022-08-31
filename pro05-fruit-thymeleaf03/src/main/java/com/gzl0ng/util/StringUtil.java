package com.gzl0ng.util;

/**
 * Author: guozhenglong
 * Date:2022/8/30 14:26
 */
public class StringUtil {
    //判断字符串是否为null或者“”
    public static boolean isEmpty(String str){
        return str==null || "".equals(str);
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
