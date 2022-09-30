package com.gzl0ng.book.service;

import com.gzl0ng.book.pojo.OrderBean;
import com.gzl0ng.book.pojo.User;

import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/9/22 14:55
 */
public interface OrderService {
    void addOrderBean(OrderBean orderBean);
    List<OrderBean> getOrderList(User user);
}
