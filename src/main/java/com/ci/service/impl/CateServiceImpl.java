package com.ci.service.impl;

import com.ci.dao.CateDao;
import com.ci.pojo.entity.Category;
import com.ci.pojo.vo.CateView;
import com.ci.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CateServiceImpl implements CateService {
    @Autowired
    CateDao cateDao;

    @Override
    public Category selectByName(String name) {
        return cateDao.selectByName(name);
    }

    @Override
    public List<CateView> selectAllCateView() {
        List<Category> categories = cateDao.selectAll();
        List<CateView> cateViews = new ArrayList<>();
        for (Category category : categories) {
            if (category.getStatus() == 0) {
                continue;
            }
            CateView cateView = new CateView(category.getCateId(), category.getName());
            cateViews.add(cateView);
        }
        return cateViews;
    }
}
