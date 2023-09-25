package com.ci.service;

import com.ci.pojo.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OrderService {
    List<Order> getAllByUserId(String userId);
    boolean add(Order order);
}
