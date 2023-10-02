package com.ci.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ci.dao.OrderDao;
import com.ci.exception.BusinessException;
import com.ci.pojo.entity.Order;
import com.ci.pojo.vo.ErrorCode;
import com.ci.service.OrderService;
import com.ci.util.TimeUtils;
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
        List<Order> userOrders = orderDao.getAllByUserId(userId);
        // 循环遍历，判断当前时间是否超过订单的过期时间10分钟，如果超过则关闭订单,并更新集合userOrders中的订单状态
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = TimeUtils.getNowTime();

        for (int i = 0; i < userOrders.size(); i++) {
            Order order = userOrders.get(i);
            if (order.getStatus() == 0)
                continue;
            if (order.getStatus() == 10) {
                String createTime = order.getCreateTime();
                // 计算时间差
                long diff;
                try {
                    Date d1 = formatter.parse(createTime);
                    Date d2 = formatter.parse(nowTime);
                    diff = d2.getTime() - d1.getTime();
                } catch (Exception e) {
                    throw new BusinessException(ErrorCode.BUSINESS_ERR, "时间转换异常", e);
                }
                // 计算时间差，单位为分钟
                long diffMinutes = diff / (60 * 1000);
                // 如果超过10分钟，则关闭订单
                if (diffMinutes >= 10) {
                    order.setStatus(0);
                    closeOrder(order.getOrderId());
                    userOrders.set(i, order);
                }
            }
        }
        return userOrders;
    }

    @Override
    public Order getById(String orderId) {
        Order order = orderDao.getById(orderId);
        // 判断当前时间是否超过订单的过期时间10分钟，如果超过则关闭订单
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = TimeUtils.getNowTime();

        if (order.getStatus() == 0)
            return order;
        if (order.getStatus() == 10) {
            String createTime = order.getCreateTime();
            // 计算时间差
            long diff;
            try {
                Date d1 = formatter.parse(createTime);
                Date d2 = formatter.parse(nowTime);
                diff = d2.getTime() - d1.getTime();
            } catch (Exception e) {
                throw new BusinessException(ErrorCode.BUSINESS_ERR, "时间转换异常", e);
            }
            // 计算时间差，单位为分钟
            long diffMinutes = diff / (60 * 1000);
            // 如果超过10分钟，则关闭订单
            if (diffMinutes >= 10) {
                order.setStatus(0);
                closeOrder(order.getOrderId());
            }
        }
        return order;
    }


    // TODO 插入订单，返回订单id
    @Override
    public String add(Order order) {
        // 获取mybatis plus生成的id
        String orderId = String.valueOf(IdWorker.getId(order));
        String nowTime = TimeUtils.getNowTime();
        order.setOrderId(orderId);
        order.setCreateTime(nowTime);
        order.setUpdateTime(nowTime);
        // 默认为未付款
        order.setStatus(10);
        boolean addOrderFlag = orderDao.add(order);

        if (addOrderFlag) {
            return orderId;
        }
        return null;
    }

    @Override
    public boolean update(Order order) {
        String nowTime = TimeUtils.getNowTime();
        order.setUpdateTime(nowTime);
        return orderDao.update(order);
    }

    @Override
    public boolean closeOrder(String orderId) {
        String nowTime = TimeUtils.getNowTime();
        Order order = orderDao.getById(orderId);
        order.setStatus(0);
        order.setUpdateTime(nowTime);
        return orderDao.update(order);
    }


}
