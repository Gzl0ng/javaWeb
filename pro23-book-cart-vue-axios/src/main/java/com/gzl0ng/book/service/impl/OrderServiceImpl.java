package com.gzl0ng.book.service.impl;

import com.gzl0ng.book.dao.CartItemDAO;
import com.gzl0ng.book.dao.OrderDAO;
import com.gzl0ng.book.dao.OrderItemDAO;
import com.gzl0ng.book.pojo.CartItem;
import com.gzl0ng.book.pojo.OrderBean;
import com.gzl0ng.book.pojo.OrderItem;
import com.gzl0ng.book.pojo.User;
import com.gzl0ng.book.service.OrderService;

import java.util.List;
import java.util.Map;

/**
 * Author: guozhenglong
 * Date:2022/9/22 14:58
 */
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private CartItemDAO cartItemDAO;

    private Integer getOrderTotalBookCount;
    @Override
    public void addOrderBean(OrderBean orderBean) {
//        1) 订单表添加一条记录
//        2) 订单详情表添加7条记录
//        3) 购物车项表中需要删除对应的7条记录
        //第1步：
        orderDAO.addOrderBean(orderBean);  //执行完这一步，orderBean中的id是有值的
        //第2步:
        //orderBean中的orderItemList是空的，此处我们应该根据用户的购物车中的购物车项去转换成一个一个订单项
        User currUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = currUser.getCart().getCartItemMap();
        for (CartItem cartItem : cartItemMap.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }
        //第3步:
             for (CartItem cartItem : cartItemMap.values()) {
            cartItemDAO.delCartItem(cartItem);
        }

    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderBeanList = orderDAO.getOrderList(user);
        for (OrderBean orderBean : orderBeanList) {
            Integer totalBookCount = orderDAO.getOrderTotalBookCount(orderBean);
            orderBean.setTotalBookCount(totalBookCount);
        }
        return orderBeanList;
    }
}
