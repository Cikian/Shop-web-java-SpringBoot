package com.ci.dao;

import com.ci.pojo.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getAllByUserId(String userId);

    boolean add(Order order);

}
