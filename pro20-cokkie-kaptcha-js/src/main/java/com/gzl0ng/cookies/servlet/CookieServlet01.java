package com.gzl0ng.cookies.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: guozhenglong
 * Date:2022/9/27 13:40
 */
@WebServlet("/cookie01")
public class CookieServlet01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.创建一个cookie对象
        Cookie cookie = new Cookie("uname","jim");
//        cookie.setMaxAge(20);//设置cookie的有效时长
        //2.将这个Cookie对象保存到浏览器端
        resp.addCookie(cookie);

        req.getRequestDispatcher("hello01.html").forward(req,resp);
    }
}
