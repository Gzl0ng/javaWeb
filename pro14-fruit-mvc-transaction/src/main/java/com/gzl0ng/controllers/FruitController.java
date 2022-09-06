package com.gzl0ng.controllers;

import com.gzl0ng.service.FruitService;
import com.gzl0ng.pojo.Fruits;
import com.gzl0ng.util.StringUtil;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/8/31 18:03
 */
public class FruitController{
//    private FruitsDAO fruitsDAO = new FruitDAOImpl();

    private FruitService fruitService = null;

    private String update(Integer fid,String fname,Integer price,Integer fcount,String remark) {

        //3.更新数据库
        fruitService.updateFruit(new Fruits(fid,fname,price,fcount,remark));

        //4.资源跳转
//        resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

    private String edit(Integer fid,HttpServletRequest request){

        if (fid!=null){
            Fruits fruits = fruitService.getFruitByFid(fid);

            request.setAttribute("fruit",fruits);
//            super.processTemplate("edit",req,resp);
            return "edit";
        }
        return "error";
    }

    private String del(Integer fid){

        if (fid!=null){
            fruitService.delFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String add(String fname,Integer price,Integer fcount,String remark){
//        System.out.println("接受到修改请求");

        Fruits fruits = new Fruits(0, fname, price, fcount, remark);
        fruitService.addFruit(fruits);

//        resp.sendRedirect("fruit.do");
        return "redirect:fruit.do";
    }

    private String index(String oper,String keyword,Integer pageNo,HttpServletRequest request){
        System.out.println("收到请求");

        HttpSession session = request.getSession() ;

        if (pageNo==null){
            pageNo=1;
        }
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            System.out.println(oper);
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为1 ， keyword应该从请求参数中获取
            pageNo = 1 ;
            if(StringUtil.isEmpty(keyword)){
                keyword = "" ;
            }
            session.setAttribute("keyword",keyword);
        }else{
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword = (String)keywordObj ;
            }else{
                keyword = "" ;
            }
        }

        session.setAttribute("pageNo",pageNo);

        List<Fruits> fruitList = fruitService.getFruitList(keyword , pageNo);

        session.setAttribute("fruitList",fruitList);

        //总页数
        int pageCount = fruitService.getPageCount(keyword);

        session.setAttribute("pageCount",pageCount);
//        super.processTemplate("index",request,response);
        return "index";
    }
}
