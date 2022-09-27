package com.gzl0ng.book.dao;

import com.gzl0ng.book.pojo.OrderItem;

/**
 * Author: guozhenglong
 * Date:2022/9/22 14:43
 */
public interface OrderItemDAO {
    //添加订单项
    void addOrderItem(OrderItem orderItem);
}
