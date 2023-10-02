package com.ci.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ci.dao.CateDao;
import com.ci.mapper.CateMapper;
import com.ci.mapper.CateViewMapper;
import com.ci.pojo.entity.Category;
import com.ci.pojo.vo.CateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CateDaoImpl implements CateDao {
    @Autowired
    CateMapper cateMapper;

    @Autowired
    CateViewMapper cateViewMapper;

    @Override
    public Category selectByName(String name) {
        QueryWrapper<Category> qw = new QueryWrapper<>();
        qw.eq("name", name);
        return cateMapper.selectOne(qw);
    }

    @Override
    public List<Category> selectAll() {
        return cateMapper.selectList(null);
    }
}
