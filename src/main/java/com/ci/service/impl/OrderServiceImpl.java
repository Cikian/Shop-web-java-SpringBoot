package com.ci.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ci.dao.OrderDao;
import com.ci.pojo.entity.Order;
import com.ci.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/9/25 17:08
 */

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Override
    public List<Order> getAllByUserId(String userId) {
        return orderDao.getAllByUserId(userId);
    }


    // 待完成
    @Override
    public boolean add(Order order) {
        // 获取mybatis plus生成的id
        String orderId = String.valueOf(IdWorker.getId(order));
        System.out.println("生成的orderId: " + orderId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String nowTime = formatter.format(date);
        order.setOrderId(orderId);
        order.setCreateTime(nowTime);
        order.setUpdateTime(nowTime);
        order.setStatus(10);
        boolean addOrderFlag = orderDao.add(order);
        if (addOrderFlag) {
            return true;
        }
        return false;
    }
}
