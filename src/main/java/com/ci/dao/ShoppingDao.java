package com.ci.dao;

import com.ci.pojo.entity.Shopping;

public interface ShoppingDao {
    Shopping getByOrderId(String orderId);
    boolean add(Shopping shopping);
    
    boolean deleteById(String orderId);
}
