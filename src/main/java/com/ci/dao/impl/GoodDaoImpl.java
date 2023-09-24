package com.ci.dao.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ci.dao.GoodDao;
import com.ci.mapper.GoodMapper;
import com.ci.pojo.entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description: TODO 商品dao层实现类
 * @date 2023/9/12 18:54
 */

@Repository
public class GoodDaoImpl implements GoodDao {
    @Autowired
    GoodMapper goodMapper;

    @Override
    public List<Good> getAll() {
        return goodMapper.selectList(null);
    }

    @Override
    public Good getById(String goodId) {
        return goodMapper.selectById(goodId);
    }

    @Override
    public boolean add(Good good) {
        return goodMapper.insert(good) > 0;
    }

    @Override
    public boolean delete(String goodId) {
        return goodMapper.deleteById(goodId) > 0;
    }

    @Override
    public boolean update(Good good) {
        UpdateWrapper<Good> uw = new UpdateWrapper<>();
        uw.eq("good_id", good.getGoodId());
        return goodMapper.update(good, uw) > 0;
    }
}
