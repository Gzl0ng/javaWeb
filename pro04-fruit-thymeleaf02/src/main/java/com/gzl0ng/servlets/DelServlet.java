package com.gzl0ng.servlets;

import com.gzl0ng.baseservlet.ViewBaseServlet;
import com.gzl0ng.util.StringUtil;
import myssm.basedao.dao.FruitsDAO;
import myssm.basedao.dao.impl.FruitDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: guozhenglong
 * Date:2022/8/30 17:52
 */
@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {
    private FruitsDAO fruitsDAO = new FruitDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fidStr = req.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitsDAO.delFruit(fid);

//            super.processTemplate("index",req,resp);
            resp.sendRedirect("/index.html");
        }
    }
}
