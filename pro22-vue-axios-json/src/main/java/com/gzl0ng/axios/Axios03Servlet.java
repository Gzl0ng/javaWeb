package com.gzl0ng.axios;

import com.alibaba.fastjson2.JSON;
import com.gzl0ng.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Author: guozhenglong
 * Date:2022/9/30 11:04
 */
@WebServlet("/axios03.do")
public class Axios03Servlet extends HttpServlet {
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

        user.setUname("鸠摩智");
        user.setPwd("123456");

        //假设user是从数据库查询处理的，现在需要将其转化成json格式的字符串，然后响应给客户端
        String userJsonStr = JSON.toJSONString(user);
        //设置MIME类型，通知浏览器怎么展示
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(userJsonStr);
    }
}
