package com.ci.service;

import com.ci.pojo.entity.Order;
import com.ci.pojo.vo.OrderListView;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OrderService {
    List<OrderListView> getAllByUserId(String userId);

    Order getById(String orderId);

    String add(Order order);

    boolean update(Order order);

    boolean closeOrder(String orderId);
    
    boolean deleteById(String orderId,String userId);

    Order getNewOrder(String userId);

    List<OrderListView> getWaitPayOrder(String userId);

    List<OrderListView> getWaitPostOrder(String userId);

    List<OrderListView> getShippedOrder(String userId);
    
    List<OrderListView> getReceivedOrder(String userId);
}
