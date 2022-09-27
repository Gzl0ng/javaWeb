package com.gzl0ng.book.pojo;

import java.util.Date;
import java.util.List;

//订单表
public class OrderBean {
    private Integer id ;
    private String orderNo ;
    private Date orderDate;
    private User orderUser ;
    private Double orderMoney ;
    private Integer orderStatus;

    private List<OrderItem> orderItemList ;     //1:N  1对多关系
    private Integer totalBookCount;   //这里违反三范式，但是不用连接后相加了

    public Integer getTotalBookCount() {
        return totalBookCount;
    }

    public OrderBean(){}
    public OrderBean(Integer id){
        this.id= id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(User orderUser) {
        this.orderUser = orderUser;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public void setTotalBookCount(Integer totalBookCount) {
        this.totalBookCount = totalBookCount;
    }
}
