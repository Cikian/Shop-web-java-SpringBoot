package com.ci.dao;

import com.ci.pojo.entity.Good;

import java.util.List;

public interface GoodDao {
    List<Good> getAll();

    Good getById(String goodId);

    boolean add(Good good);

    boolean delete(String goodId);

    boolean update(Good good);
}
