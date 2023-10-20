package com.ci.dao;

import com.ci.pojo.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    boolean addOrderItem(OrderItem orderItem);

    List<OrderItem> getByOrderId(String orderId);

    boolean deleteById(String orderId);
}
