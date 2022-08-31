package com.gzl0ng.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Author: guozhenglong
 * Date:2022/8/24 18:05
 */
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //get方式目前不需要设置编码（基于tomcat8）

        /*
        //如果是get请求发送的中文数据，转码稍微用有点麻烦
        String fname1 = req.getParameter("fname");
        //1.将字符串打散成字节数组
        byte[] bytes = fname1.getBytes(StandardCharsets.ISO_8859_1);
        //2.将字节数组按照设定的编码重新组装成字符串
        fname1 = new String(bytes,"UTF-8");
         */

        //post方式下，设置编码，防止中文乱码
        //需要注意的是，设置编码这一句代码必须在所有的获取参数动作之前
        req.setCharacterEncoding("UTF-8");
        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = req.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = req.getParameter("remark");

        System.out.println("fname = " + fname);
        System.out.println("price = " + price);
        System.out.println("fount = " + fcount);
        System.out.println("remark = " + remark);
    }

}
