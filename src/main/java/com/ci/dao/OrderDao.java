package com.ci.dao;

import com.ci.pojo.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getAllByUserId(String userId);
    
    Order getById(String orderId);

    boolean add(Order order);
    
    boolean update(Order order);
    
    boolean closeOrder(Order order);

}
