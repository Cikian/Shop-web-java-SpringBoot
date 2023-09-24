package com.ci.service;

import com.ci.pojo.entity.Good;

import java.util.List;

public interface GoodService {
    List<Good> getAll();

    Good getById(String goodId);

    boolean add(Good good);

    boolean deleteGood(String goodId);

    boolean updateGood(Good good);
}

