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
        List<Good> allGoods = goodDao.getAll();
        if (allGoods != null) {
            System.out.println("成功");
            return allGoods;
        } else
            return null;
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

}
