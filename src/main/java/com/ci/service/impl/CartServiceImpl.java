package com.ci.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ci.dao.CartDao;
import com.ci.pojo.entity.Cart;
import com.ci.pojo.vo.CartView;
import com.ci.service.CartService;
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
 * @date 2023/9/25 10:56
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDao cartDao;

    @Override
    public List<CartView> getAllCartsByUserId(String userId) {
        return cartDao.getAllCartsByUserId(userId);
    }

    @Override
    public Cart selectByUserIdAndGoodId(String userId, String goodId) {
        return cartDao.selectByUserIdAndGoodId(userId, goodId);
    }

    @Override
    public boolean add(Cart cart) {
        Cart cartTemp = cartDao.selectByUserIdAndGoodId(cart.getUserId(), cart.getGoodId());
        if (cartTemp != null) {
            cartTemp.setCount(cartTemp.getCount() + cart.getCount());
            return cartDao.update(cartTemp) == 1;
        }
        String cartId = String.valueOf(IdWorker.getId(cart));
        System.out.println("生成的cartId: " + cartId);
        String nowTime = TimeUtils.getNowTime();
        cart.setCartId(cartId);
        cart.setCreateTime(nowTime);
        cart.setUpdateTime(nowTime);
        return cartDao.add(cart);
    }

    @Override
    public boolean update(Cart cart) {
        String nowTime = TimeUtils.getNowTime();
        cart.setUpdateTime(nowTime);
        int count = cartDao.update(cart);
        return count == 1;
    }

    @Override
    public boolean delete(String userId, String goodId) {
        int count = cartDao.delete(userId, goodId);
        return count > 0;
    }

    @Override
    public boolean deleteAll(String userId) {
        int count = cartDao.deleteAll(userId);
        return count > 0;
    }
}
