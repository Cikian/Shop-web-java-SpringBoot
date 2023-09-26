package com.ci.service;

import com.ci.pojo.entity.OrderItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OrderItemService {
    boolean addOrderItem(List<OrderItem> orderItems, String orderId, String userId);
}
