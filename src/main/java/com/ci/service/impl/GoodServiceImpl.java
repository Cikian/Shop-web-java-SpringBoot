package com.ci.service.impl;

import com.ci.dao.GoodDao;
import com.ci.pojo.entity.Good;
import com.ci.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public Good getById(String goodId){
        Good good = goodDao.getById(goodId);
        if (good != null)
            return good;
        else
            return null;
    }


    @Override
    public boolean add(Good good){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        // 获取当前系统时间
        String nowTime = formatter.format(date);
        good.setCreateTime(nowTime);
        good.setUpdateTime(nowTime);
        boolean flag = goodDao.add(good);
        return flag;
    }

    @Override
    public boolean deleteGood(String goodId){
        boolean flag = goodDao.delete(goodId);
        return flag;
    }

    @Override
    public  boolean updateGood(Good good){
        boolean flag = goodDao.update(good);
        return flag;
    }

}
