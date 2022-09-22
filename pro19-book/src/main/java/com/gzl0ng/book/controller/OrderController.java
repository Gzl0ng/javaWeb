package com.gzl0ng.book.controller;

import com.gzl0ng.book.pojo.OrderBean;
import com.gzl0ng.book.pojo.User;
import com.gzl0ng.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Author: guozhenglong
 * Date:2022/9/22 15:10
 */
public class OrderController {
    private OrderService orderService;

    //结账
    public String checkout(HttpSession session){
        OrderBean orderBean = new OrderBean();
        Date now = new Date();
        int year = now.getYear();
        int month = now.getMonth();
        int day = now.getDate();
        int hour = now.getHours();
        int min = now.getMinutes();
        int sec = now.getSeconds();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String nowStr = sdf.format(now);
//        for (String s : nowStr.split("-")) {
//
//        }
        orderBean.setOrderNo(UUID.randomUUID().toString() +year+month+day+hour+min+sec );
        orderBean.setOrderDate(now);

        User user = (User) session.getAttribute("currUser");
        orderBean.setOrderUser(user);
        orderBean.setOrderMoney(user.getCart().getTotalMoney());
        orderBean.setOrderStatus(0);

        orderService.addOrderBean(orderBean);


        return "index";
    }
}
