package com.ci.service;

import com.ci.pojo.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getAllCartsByUserId(String userId);
    Cart selectByUserIdAndGoodId(String userId, String goodId);
    boolean add(Cart cart);
    boolean update(Cart cart);
    boolean delete(String userId, String goodId);
    boolean deleteAll(String userId);
}
