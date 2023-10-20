package com.ci.service;

import com.ci.pojo.entity.Shopping;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ShoppingService {
    Shopping getByOrderId(String orderId);
    String add(Shopping shopping);
    
    boolean deleteById(String orderId);
}
