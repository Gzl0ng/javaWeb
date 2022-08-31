package com.gzl0ng.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: guozhenglong
 * Date:2022/8/26 10:17
 */

//演示servlet的生命周期
public class Demo02Servlet extends HttpServlet {
    private Demo02Servlet(){
        System.out.println("正在实例化。。。");
    }

    @Override
    public void init() throws ServletException {

        System.out.println("正在初始化");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("正在服务...");
    }

    @Override
    public void destroy() {

        System.out.println("正在销毁...");
    }
}
