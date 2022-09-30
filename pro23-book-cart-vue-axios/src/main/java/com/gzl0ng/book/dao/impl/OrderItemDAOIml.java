package com.gzl0ng.book.dao.impl;

import com.gzl0ng.book.dao.OrderItemDAO;
import com.gzl0ng.book.pojo.OrderItem;
import com.gzl0ng.myssm.basedao.BaseDAO;

/**
 * Author: guozhenglong
 * Date:2022/9/22 14:51
 */
public class OrderItemDAOIml extends BaseDAO<OrderItemDAO> implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        super.executeUpdate("insert into t_order_item values(0,?,?,?)",orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());
    }
}
