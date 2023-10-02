package com.ci.service.impl;

import com.ci.dao.OrderItemDao;
import com.ci.pojo.entity.Good;
import com.ci.pojo.entity.OrderItem;
import com.ci.service.GoodService;
import com.ci.service.OrderItemService;
import com.ci.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/9/26 10:34
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    GoodService goodService;

    @Override
    public boolean addOrderItem(List<OrderItem> orderItems, String orderId, String userId) {
        String nowTime = TimeUtils.getNowTime();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(orderId);
            orderItem.setUserId(userId);
            orderItem.setCreateTime(nowTime);
            orderItem.setUpdateTime(nowTime);

            // 更新库存
            String goodId = orderItem.getGoodId();
            Integer count = orderItem.getCount();
            Good good = goodService.getById(goodId);
            int newStock = good.getStock() - count;
            if (newStock < 0)
                return false;
            good.setStock(newStock);
            boolean updateGoodFlag = goodService.updateGood(good);
            if (!updateGoodFlag)
                return false;
            boolean addOrderItemFlag = orderItemDao.addOrderItem(orderItem);
            if (!addOrderItemFlag)
                return false;
        }
        return true;
    }
}
