package com.gzl0ng.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: guozhenglong
 * Date:2022/8/26 17:19
 */

//演示服务器端内部转发以及客户端重定向
public class Demo06Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo06");
        //服务器端内部转发
//        req.getRequestDispatcher("demo07").forward(req,resp);

        resp.sendRedirect("demo07");
    }
}
