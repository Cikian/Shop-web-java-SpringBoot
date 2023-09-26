package com.ci.dao.impl;

import com.ci.dao.OrderItemDao;
import com.ci.mapper.OrderItemMapper;
import com.ci.pojo.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/9/26 10:30
 */

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    OrderItemMapper orderItemMapper;

    @Override
    public boolean addOrderItem(OrderItem orderItem) {
        int count = orderItemMapper.insert(orderItem);
        return count > 0;
    }
}
