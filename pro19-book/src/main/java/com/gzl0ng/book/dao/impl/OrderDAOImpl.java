package com.gzl0ng.book.dao.impl;

import com.gzl0ng.book.dao.OrderDAO;
import com.gzl0ng.book.pojo.OrderBean;
import com.gzl0ng.myssm.basedao.BaseDAO;

/**
 * Author: guozhenglong
 * Date:2022/9/22 14:46
 */
public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {
    @Override
    public void addOrderBean(OrderBean orderBean) {
        int orderBeanId = super.executeUpdate("insert into t_order values(0,?,?,?,?,?)",orderBean.getOrderNo(),orderBean.getOrderDate(),orderBean.getOrderUser().getId(),orderBean.getOrderMoney(),orderBean.getOrderStatus());
        orderBean.setId(orderBeanId);
        //思考：为什么需要接受executeUpdate的返回值，然后设置到OrderBean中的id属性上
    }
}
