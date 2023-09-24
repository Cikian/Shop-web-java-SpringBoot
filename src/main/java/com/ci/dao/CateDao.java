package com.ci.dao;

import com.ci.pojo.entity.Category;

public interface CateDao {
    Category selectByName(String name);
}
