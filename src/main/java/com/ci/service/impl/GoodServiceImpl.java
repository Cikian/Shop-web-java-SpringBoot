package com.ci.service.impl;

import com.ci.dao.GoodDao;
import com.ci.pojo.entity.Good;
import com.ci.service.GoodService;
import com.ci.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description: TODO
 * @date 2023/9/12 19:00
 */

@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    GoodDao goodDao;

    @Override
    public List<Good> getAll() {
        return goodDao.getAll();
    }

    @Override
    public List<Good> getAllRecGoods() {
        return goodDao.getAllRecGoods();
    }

    @Override
    public Good getById(String goodId) {
        return goodDao.getById(goodId);
    }


    @Override
    public boolean add(Good good) {
        String nowTime = TimeUtils.getNowTime();
        good.setCreateTime(nowTime);
        good.setUpdateTime(nowTime);
        return goodDao.add(good);
    }

    @Override
    public boolean deleteGood(String goodId) {
        return goodDao.delete(goodId);
    }

    @Override
    public boolean updateGood(Good good) {
        String nowTime = TimeUtils.getNowTime();
        good.setUpdateTime(nowTime);
        return goodDao.update(good);
    }

    @Override
    public List<Good> getGoodsByKeyWord(String keyWord) {
        return goodDao.getGoodsByKeyWord(keyWord);
    }

    @Override
    public List<Good> getGoodsByCateId(String cateId) {
        return goodDao.getGoodsByCateId(cateId);
    }

    @Override
    public List<Good> getGoodsByCateIdUp(String cateId) {
        return goodDao.getGoodsByCateIdUp(cateId);
    }

    @Override
    public List<Good> getGoodsByCateIdDown(String cateId) {
        return goodDao.getGoodsByCateIdDown(cateId);
    }

    @Override
    public List<Good> getAllByOrderUp() {
        return goodDao.getAllByOrderUp();
    }

    @Override
    public List<Good> getAllByOrderDown() {
        return goodDao.getAllByOrderDown();
    }

}
