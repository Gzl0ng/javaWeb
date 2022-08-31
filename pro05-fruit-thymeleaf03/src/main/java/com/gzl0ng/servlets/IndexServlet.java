package com.gzl0ng.servlets;


import com.gzl0ng.baseservlet.ViewBaseServlet;
import com.gzl0ng.pojo.Fruits;
import com.gzl0ng.util.StringUtil;
import myssm.basedao.dao.FruitsDAO;
import myssm.basedao.dao.impl.FruitDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

//Servlet从3.0版本开始支持注解方式的注册
@WebServlet("/index.html")
public class IndexServlet extends ViewBaseServlet {
    @Override
    public void doGet(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        Integer pageNo = 1 ;
        String pageNoStr = request.getParameter("pageNo");
        if (StringUtil.isNotEmpty(pageNoStr)){
            pageNo = Integer.parseInt(pageNoStr);
        }

        HttpSession session = request.getSession() ;
        session.setAttribute("pageNo",pageNo);

        FruitsDAO fruitDAO = new FruitDAOImpl();
        List<Fruits> fruitList = fruitDAO.getFruitList(pageNo);
        session.setAttribute("fruitList",fruitList);

        int fruitCount = fruitDAO.getFruitCount();
        int pageCount = (fruitCount+5-1) /5;
        session.setAttribute("pageCount",pageCount);
//        System.out.println(fruitList);

        //总记录条数
//        int fruitCount = fruitDAO.getFruitCount();
        //总页数
//        int pageCount = (fruitCount+5-1)/5 ;
        /*
        总记录条数       总页数
        1               1
        5               1
        6               2
        10              2
        11              3
        fruitCount      (fruitCount+5-1)/5
         */
//        session.setAttribute("pageCount",pageCount);

        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ：   index
        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：      /       index       .html
        super.processTemplate("index",request,response);
    }
}
