package com.ci.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ci.dao.CartDao;
import com.ci.mapper.CartMapper;
import com.ci.pojo.entity.Cart;
import com.ci.pojo.vo.CartView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO 添加购物车
 * @date 2023/9/25 10:44
 */

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    CartMapper cartMapper;

    @Override
    public List<CartView> getAllCartsByUserId(String userId) {
        return cartMapper.getAllCartsByUserId(userId);
    }

    @Override
    public Cart selectByUserIdAndGoodId(String userId, String goodId) {
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        qw.eq("good_id", goodId);
        return cartMapper.selectOne(qw);
    }

    @Override
    public boolean add(Cart cart) {
        return cartMapper.insert(cart) > 0;
    }


    @Override
    public boolean addCount(String userId, String goodId) {
        return false;
    }

    @Override
    public boolean subCount(String userId, String goodId) {
        return false;
    }

    @Override
    public int update(Cart cart) {
        UpdateWrapper<Cart> uw = new UpdateWrapper<>();
        uw.eq("user_id", cart.getUserId());
        uw.eq("good_id", cart.getGoodId());
        return cartMapper.update(cart, uw);
    }

    @Override
    public int delete(String userId, String goodId) {
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        qw.eq("good_id", goodId);
        return cartMapper.delete(qw);
    }

    @Override
    public int deleteAll(String userId) {
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        return cartMapper.delete(qw);
    }
}
