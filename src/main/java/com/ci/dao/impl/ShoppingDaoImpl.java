package com.ci.dao.impl;

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
    public boolean add(Shopping shopping){
        return shoppingMapper.insert(shopping) > 0;
    }

}
