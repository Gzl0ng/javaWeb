package com.gzl0ng.demo;

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
//演示session保存作用域（demo03和demo04）
@WebServlet("/demo03")
public class Demo03Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.向session保存作用域保存数据
        req.getSession().setAttribute("uname","lucy");
        //2.客户端重定向,作用域数据能获取到
        resp.sendRedirect("demo04");

        //3.服务器端内部转发，作用域数据能获取到
//        req.getRequestDispatcher("demo04").forward(req,resp);
    }
}
