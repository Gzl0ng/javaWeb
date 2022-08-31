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
 * Date:2022/8/30 16:03
 */
@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitsDAO fruitDAO = new FruitDAOImpl();
        //1.设置编码
        req.setCharacterEncoding("utf-8");

        //2.获取参数
        String fidStr = req.getParameter("fid");
        int fid = Integer.parseInt(fidStr);

        String fname = req.getParameter("fname");

        String priceStr = req.getParameter("price");
        int price = Integer.parseInt(priceStr);

        String fcountStr = req.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);

        String remark = req.getParameter("remark");

        //3.更新数据库
        fruitDAO.updateFruit(new Fruits(fid,fname,price,fcount,remark));


        //4.更新视图,下面这种相当于内部转发
//        super.processTemplate("index",req,resp);
//        req.getRequestDispatcher("index.html").forward(req,resp);
        //此处需要重定向，目的是查询给IndexServlet发请求，重新获取fruitList，然后覆盖到session中，这样index.html页面上显示的session中的数据才是最新的
        resp.sendRedirect("/index.html");
    }
}
