package com.ci.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ci.dao.CateDao;
import com.ci.mapper.CateMapper;
import com.ci.pojo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CateDaoImpl implements CateDao {
    @Autowired
    CateMapper cateMapper;

    @Override
    public Category selectByName(String name) {
        QueryWrapper<Category> qw = new QueryWrapper<>();
        qw.eq("name", name);
        return cateMapper.selectOne(qw);
    }
}
