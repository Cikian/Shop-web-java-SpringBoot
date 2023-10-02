package com.ci.dao;

import com.ci.pojo.entity.Cart;
import com.ci.pojo.vo.CartView;

import java.util.List;

public interface CartDao {
    List<CartView> getAllCartsByUserId(String userId);
    Cart selectByUserIdAndGoodId(String userId, String goodId);

    boolean add(Cart cart);

    boolean addCount(String userId, String goodId);

    boolean subCount(String userId, String goodId);

    int update(Cart cart);

    int delete(String userId, String goodId);
    
    int deleteAll(String userId);
    long getCount(String userId);
}
