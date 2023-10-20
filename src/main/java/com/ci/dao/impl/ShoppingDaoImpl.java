package com.ci.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ci.dao.ShoppingDao;
import com.ci.mapper.ShoppingMapper;
import com.ci.pojo.entity.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Shirley
 * @version 1.0
 * @description: TODO
 * @date 2023/9/13 10:33
 */
@Repository
public class ShoppingDaoImpl implements ShoppingDao {
    @Autowired
    ShoppingMapper shoppingMapper;

    @Override
    public Shopping getByOrderId(String orderId) {
        QueryWrapper<Shopping> qw = new QueryWrapper<>();
        qw.eq("order_id", orderId);
        return shoppingMapper.selectOne(qw);
    }

    @Override
    public boolean add(Shopping shopping){
        return shoppingMapper.insert(shopping) > 0;
    }

    @Override
    public boolean deleteById(String orderId) {
        QueryWrapper<Shopping> qw = new QueryWrapper<>();
        qw.eq("order_id", orderId);
        int delete = shoppingMapper.delete(qw);
        return delete > 0;
    }

}
