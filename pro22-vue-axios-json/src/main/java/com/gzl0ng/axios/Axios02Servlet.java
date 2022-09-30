package com.gzl0ng.axios;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.gzl0ng.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: guozhenglong
 * Date:2022/9/30 11:04
 */
@WebServlet("/axios02.do")
public class Axios02Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        String str = null;
        StringBuffer stringBuffer = new StringBuffer("");
        if ((str=bufferedReader.readLine())!=null){
            stringBuffer.append(str);
        }

        //已知String
        //需要转化成JavaObject

        //Gson有二个API
        //1.fromJson(String,T) 将字符串转化成java Object
        //2.toJson(java Object) 将javaObject转化成json字符串，这样才能返回给客户端
        str = stringBuffer.toString();
        User user = JSON.parseObject(str, User.class);
        System.out.println(user);
    }
}
