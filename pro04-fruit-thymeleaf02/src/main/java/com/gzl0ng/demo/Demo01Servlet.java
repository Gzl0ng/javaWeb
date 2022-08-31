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
//演示request保存作用域（demo01和demo02）
@WebServlet("/demo01")
public class Demo01Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.向request保存作用域保存数据
        req.setAttribute("uname","lucy");
        //2.客户端重定向,作用域数据获取不到
//        resp.sendRedirect("demo02");

        //3.服务器端内部转发，作用域数据能获取到
        req.getRequestDispatcher("demo02").forward(req,resp);
    }
}
