package com.gzl0ng.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author: guozhenglong
 * Date:2022/8/26 15:21
 */
//演示session
public class Demo03Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //第一步，获取session,如获取不到，则创建一个新的
        HttpSession session = req.getSession();
        System.out.println("session ID:"+session.getId());

        session.getMaxInactiveInterval();
//        session.invalidate();
//        session.getCreationTime();
//        session.getLastAccessedTime();
//        session.getAttribute();
    }
}
