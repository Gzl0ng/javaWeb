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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/8/31 18:03
 */
@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {
    private FruitsDAO fruitsDAO = new FruitDAOImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String operate = req.getParameter("operate");
        if (StringUtil.isEmpty(operate)){
            operate = "index";
        }

        //获取当前类中所有的方法
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            //获取方法名称
            String methodName = method.getName();
            if (operate.equals(methodName)){
                //找到operate同名的方法，那么提供反射技术调用他
                try {
                    method.invoke(this,req,resp);
                    return;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new RuntimeException("operate值非法");

//        switch (operate){
//            case "index":
//                index(req,resp);
//                break;
//            case "add":
//                add(req,resp);
//                break;
//            case "del":
//                del(req,resp);
//                break;
//            case "edit":
//                edit(req,resp);
//                break;
//            case "update":
//                update(req,resp);
//                break;
//            default:
//                throw new RuntimeException("operate值非法");
//        }
    }
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        resp.sendRedirect("fruit.do");
    }
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fidStr = req.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitsDAO.delFruit(fid);

//            super.processTemplate("index",req,resp);
            resp.sendRedirect("fruit.do");
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("接受到修改请求");

        String fname = req.getParameter("fname");
        Integer price = Integer.parseInt(req.getParameter("price"));
        Integer fcount = Integer.parseInt(req.getParameter("fcount"));
        String remark = req.getParameter("remark");

        Fruits fruits = new Fruits(0, fname, price, fcount, remark);

        fruitsDAO.addFruit(fruits);

        resp.sendRedirect("fruit.do");
    }

    private void index(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        System.out.println("收到请求");

        HttpSession session = request.getSession() ;
        Integer pageNo = 1 ;

        String oper = request.getParameter("oper");
        //如果oper!=null 说明 通过表单的查询按钮点击过来的
        //如果oper是空的，说明 不是通过表单的查询按钮点击过来的

        String keyword = null ;
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            System.out.println(oper);
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
            pageNo = 1 ;
            keyword = request.getParameter("keyword");
            if(StringUtil.isEmpty(keyword)){
                keyword = "" ;
            }
            session.setAttribute("keyword",keyword);
        }else{
            //说明此处不是点击表单查询发送过来的请求（比如点击下面的上一页下一页或者直接在地址栏输入网址）
            //此时keyword应该从session作用域获取
            String pageNoStr = request.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword = (String)keywordObj ;
            }else{
                keyword = "" ;
            }
        }

        session.setAttribute("pageNo",pageNo);

        FruitsDAO fruitDAO = new FruitDAOImpl();
        List<Fruits> fruitList = fruitDAO.getFruitList(keyword , pageNo);

        session.setAttribute("fruitList",fruitList);

        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount+5-1)/5 ;
        /*
        总记录条数       总页数
        1               1
        5               1
        6               2
        10              2
        11              3
        fruitCount      (fruitCount+5-1)/5
         */
        session.setAttribute("pageCount",pageCount);

        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ：   index
        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：      /       index       .html
        super.processTemplate("index",request,response);
    }
}
