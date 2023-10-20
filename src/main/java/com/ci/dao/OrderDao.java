package com.ci.dao;

import com.ci.pojo.entity.Order;
import com.ci.pojo.vo.OrderListView;

import java.util.List;

public interface OrderDao {
    List<OrderListView> getAllByUserId(String userId);
    
    Order getById(String orderId);

    boolean add(Order order);
    
    boolean update(Order order);
    
    boolean closeOrder(Order order);

    boolean deleteById(String orderId);

    Order getNewOrder(String userId);

    List<OrderListView> getWaitPayOrder(String userId);

    List<OrderListView> getWaitPostOrder(String userId);

    List<OrderListView> getShippedOrder(String userId);

    List<OrderListView> getReceivedOrder(String userId);

}
