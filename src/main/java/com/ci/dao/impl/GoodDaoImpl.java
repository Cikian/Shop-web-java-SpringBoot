package com.ci.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public List<Good> getAllRecGoods() {
        // 查询所有推荐商品
        QueryWrapper<Good> qw = new QueryWrapper<>();
        qw.eq("rec", 1);
        return goodMapper.selectList(qw);
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

    @Override
    public List<Good> getGoodsByKeyWord(String keyWord) {
        // 根据关键字模糊查询商品
        QueryWrapper<Good> qw = new QueryWrapper<>();
        qw.like("name", keyWord);
        return goodMapper.selectList(qw);
    }

    @Override
    public List<Good> getGoodsByCateId(String cateId) {
        // 根据分类id查询商品
        QueryWrapper<Good> qw = new QueryWrapper<>();
        qw.eq("cate_id", cateId);
        return goodMapper.selectList(qw);
    }

    @Override
    public List<Good> getGoodsByCateIdUp(String cateId) {
        // 根据分类id查询商品，按价格升序排序
        QueryWrapper<Good> qw = new QueryWrapper<>();
        qw.eq("cate_id", cateId);
        qw.orderByAsc("price");
        return goodMapper.selectList(qw);
    }

    @Override
    public List<Good> getGoodsByCateIdDown(String cateId) {
        // 根据分类id查询商品，按价格降序排序
        QueryWrapper<Good> qw = new QueryWrapper<>();
        qw.eq("cate_id", cateId);
        qw.orderByDesc("price");
        return goodMapper.selectList(qw);
    }

    @Override
    public List<Good> getAllByOrderUp() {
        // 查询所有商品，按价格升序排序
        QueryWrapper<Good> qw = new QueryWrapper<>();
        qw.orderByAsc("price");
        return goodMapper.selectList(qw);
    }

    @Override
    public List<Good> getAllByOrderDown() {
        // 查询所有商品，按价格升序排序
        QueryWrapper<Good> qw = new QueryWrapper<>();
        qw.orderByDesc("price");
        return goodMapper.selectList(qw);
    }
}
