package com.ci.dao;

import com.ci.pojo.entity.Good;

import java.util.List;

public interface GoodDao {
    List<Good> getAll();

    List<Good> getAllRecGoods();

    Good getById(String goodId);

    boolean add(Good good);

    boolean delete(String goodId);

    boolean update(Good good);

    List<Good> getGoodsByKeyWord(String keyWord);

    List<Good> getGoodsByCateId(String cateId);

    List<Good> getGoodsByCateIdUp(String cateId);

    List<Good> getGoodsByCateIdDown(String cateId);

    List<Good> getAllByOrderUp();

    List<Good> getAllByOrderDown();
}
