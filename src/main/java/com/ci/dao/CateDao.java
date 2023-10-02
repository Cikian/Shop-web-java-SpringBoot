package com.ci.dao;

import com.ci.pojo.entity.Category;
import com.ci.pojo.vo.CateView;

import java.util.List;

public interface CateDao {
    Category selectByName(String name);

    List<Category> selectAll();
}
