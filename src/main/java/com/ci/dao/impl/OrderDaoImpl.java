package com.ci.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ci.dao.OrderDao;
import com.ci.mapper.OrderMapper;
import com.ci.pojo.entity.Order;
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

    @Override
    public List<Order> getAllByUserId(String userId) {
        QueryWrapper<Order> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        return orderMapper.selectList(qw);
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
}
