package com.ci.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ci.dao.OrderDao;
import com.ci.mapper.OrderMapper;
import com.ci.pojo.entity.Order;
import com.ci.mapper.OrderViewMapper;
import com.ci.pojo.vo.OrderListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/9/25 16:47
 */

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderViewMapper orderViewMapper;

    @Override
    public List<OrderListView> getAllByUserId(String userId) {
        QueryWrapper<OrderListView> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        qw.orderByDesc("create_time");
        return orderViewMapper.selectList(qw);
    }

    @Override
    public Order getById(String orderId) {
        QueryWrapper<Order> qw = new QueryWrapper<>();
        qw.eq("order_id", orderId);
        return orderMapper.selectOne(qw);
    }

    @Override
    public boolean add(Order order) {
        int count = orderMapper.insert(order);
        return count > 0;
    }

    @Override
    public boolean update(Order order) {
        int count = orderMapper.updateById(order);
        return count > 0;
    }

    @Override
    public boolean closeOrder(Order order) {
        return update(order);
    }

    @Override
    public boolean deleteById(String orderId) {
        int count = orderMapper.deleteById(orderId);
        return count > 0;
    }

    @Override
    public Order getNewOrder(String userId) {
        QueryWrapper<Order> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        qw.orderByDesc("create_time");
        List<Order> orders = orderMapper.selectList(qw);
        return orders.get(0);
    }

    @Override
    public List<OrderListView> getWaitPayOrder(String userId) {
        QueryWrapper<OrderListView> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        qw.eq("status", 10);
        return orderViewMapper.selectList(qw);
    }

    @Override
    public List<OrderListView> getWaitPostOrder(String userId) {
        QueryWrapper<OrderListView> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        qw.eq("status", 20);
        return orderViewMapper.selectList(qw);
    }

    @Override
    public List<OrderListView> getShippedOrder(String userId) {
        QueryWrapper<OrderListView> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        qw.eq("status", 30);
        return orderViewMapper.selectList(qw);
    }

    @Override
    public List<OrderListView> getReceivedOrder(String userId) {
        QueryWrapper<OrderListView> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        qw.eq("status", 40);
        return orderViewMapper.selectList(qw);
    }
}
