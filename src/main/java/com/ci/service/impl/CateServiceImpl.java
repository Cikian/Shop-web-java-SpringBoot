package com.ci.service.impl;

import com.ci.dao.CateDao;
import com.ci.pojo.entity.Category;
import com.ci.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CateServiceImpl implements CateService {
    @Autowired
    CateDao cateDao;
    @Override
    public Category selectByName(String name) {
        return cateDao.selectByName(name);
    }
}
