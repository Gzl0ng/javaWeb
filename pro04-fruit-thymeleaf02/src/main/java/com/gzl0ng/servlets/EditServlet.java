package com.gzl0ng.servlets;

import com.gzl0ng.baseservlet.ViewBaseServlet;
import com.gzl0ng.pojo.Fruits;
import com.gzl0ng.util.StringUtil;
import myssm.basedao.dao.FruitsDAO;
import myssm.basedao.dao.impl.FruitDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/8/30 14:05
 */
@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitsDAO fruitDAO = new FruitDAOImpl();

        String fid = req.getParameter("fid");
        if (StringUtil.isNotEmpty(fid)){
            int i = Integer.parseInt(fid);

            //获得fid对应的水果信息
//            Fruits fruits = (Fruits) req.getSession().getAttribute("fruits");
//            System.out.println("s" + fruits);

            Fruits fruits = fruitDAO.getFruitByFid(i);

            req.setAttribute("fruit",fruits);
            super.processTemplate("edit",req,resp);

        }
    }
}
