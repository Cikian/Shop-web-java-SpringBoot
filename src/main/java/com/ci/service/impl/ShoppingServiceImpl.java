package com.ci.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.ci.dao.ShoppingDao;
import com.ci.pojo.entity.Shopping;
import com.ci.service.ShoppingService;
import com.ci.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/9/26 10:06
 */

@Service
public class ShoppingServiceImpl implements ShoppingService {
    @Autowired
    ShoppingDao shoppingDao;

    @Override
    public String add(Shopping shopping) {
        String nowTime = TimeUtils.getNowTime();
        shopping.setCreateTime(nowTime);
        shopping.setUpdateTime(nowTime);
        String shoppingId = String.valueOf(IdWorker.getId(shopping));
        shopping.setShoppingId(shoppingId);
        boolean addFlag = shoppingDao.add(shopping);
        if (addFlag) {
            return shoppingId;
        }
        return null;
    }
}
