package com.gzl0ng.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: guozhenglong
 * Date:2022/8/30 11:03
 */
//演示application保存作用域（demo05和demo06）
@WebServlet("/demo05")
public class Demo05Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.向application保存作用域保存数据
        //ServletContext : Servlet上下文
        ServletContext application = req.getServletContext();
        application.setAttribute("uname","lucy");

        //2.客户端重定向,作用域数据能获取到
        resp.sendRedirect("demo06");

        //3.服务器端内部转发，作用域数据能获取到
//        req.getRequestDispatcher("demo06").forward(req,resp);
    }
}
