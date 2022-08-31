package com.gzl0ng.servlets;

import com.gzl0ng.baseservlet.ViewBaseServlet;
import com.gzl0ng.pojo.Fruits;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Author: guozhenglong
 * Date:2022/8/29 10:23
 */
//Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index.html")
public class IndexServlet extends ViewBaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取数据库结果

        ArrayList<Fruits> lists = new ArrayList<>();
        Fruits fruits = new Fruits();
        fruits.setFname("苹果");
        fruits.setPrice(10L);
        fruits.setFcount(5L);
        lists.add(fruits);
        System.out.println("收到list请求" + lists);


        //保存到session作用域
        HttpSession session = req.getSession();
        session.setAttribute("fruitList",lists);

        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图名称上去
        super.processTemplate("index",req,resp);
        /*
        逻辑视图名称： index
        物理视图名称： view-prefix + 逻辑视图名称 + view-shuffix

//        真实视图名称:  /index.html
         */
    }
}
