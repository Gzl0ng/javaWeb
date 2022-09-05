package com.gzl0ng.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: guozhenglong
 * Date:2022/9/5 13:44
 */
//此处注解和web.xml配置效果一样
//@WebServlet(urlPatterns = {"/demo01"},
//        initParams = {
//        @WebInitParam(name = "hello",value = "world"),
//        @WebInitParam(name = "uname",value = "lucy")
//    }
//)

public class Demo01Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String initValue = config.getInitParameter("hello");
        System.out.println("initValue = " + initValue);

        ServletContext servletContext = getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation = "+contextConfigLocation);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getServletContext()
//        req.getSession().getServletContext();
    }
}

//Servlet生命周期：实例化，初始化，服务，销毁