package com.ci.service;

import com.ci.pojo.entity.Cart;
import com.ci.pojo.vo.CartView;

import java.util.List;

public interface CartService {
    List<CartView> getAllCartsByUserId(String userId);
    Cart selectByUserIdAndGoodId(String userId, String goodId);
    boolean add(Cart cart);
    boolean update(Cart cart);
    boolean delete(String userId, String goodId);
    boolean deleteAll(String userId);
}
