package com.ci.service;

import com.ci.pojo.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OrderService {
    List<Order> getAllByUserId(String userId);
    Order getById(String orderId);
    String add(Order order);
    boolean update(Order order);
    
    boolean closeOrder(String orderId);
}
