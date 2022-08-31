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
 * Date:2022/8/30 11:05
 */
@WebServlet("/demo06")
public class Demo06Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取application保存的作用域保存的数据
        ServletContext application = req.getServletContext();
        Object uname = application.getAttribute("uname");

        System.out.println("uname: " + uname);
    }
}
