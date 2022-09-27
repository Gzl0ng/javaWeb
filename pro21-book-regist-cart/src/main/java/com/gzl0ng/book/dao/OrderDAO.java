package com.gzl0ng.book.dao;

import com.gzl0ng.book.pojo.OrderBean;
import com.gzl0ng.book.pojo.User;

import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/9/22 14:42
 */
public interface OrderDAO {
    //添加订单
    void addOrderBean(OrderBean orderBean);
    //获取指定用户的订单列表
    List<OrderBean> getOrderList(User user);

    Integer getOrderTotalBookCount(OrderBean orderBean);
}
