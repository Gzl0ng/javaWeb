package com.gzl0ng.servlets;

import com.gzl0ng.baseservlet.ViewBaseServlet;
import com.gzl0ng.pojo.Fruits;
import myssm.basedao.dao.FruitsDAO;
import myssm.basedao.dao.impl.FruitDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: guozhenglong
 * Date:2022/8/31 10:03
 */
@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {
    private FruitsDAO fruitsDAO = new FruitDAOImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("接受到修改请求");
        req.setCharacterEncoding("UTF-8");

        String fname = req.getParameter("fname");
        Integer price = Integer.parseInt(req.getParameter("price"));
        Integer fcount = Integer.parseInt(req.getParameter("fcount"));
        String remark = req.getParameter("remark");

        Fruits fruits = new Fruits(0, fname, price, fcount, remark);

        fruitsDAO.addFruit(fruits);

        resp.sendRedirect("/index.html");
    }
}
