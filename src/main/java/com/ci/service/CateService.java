package com.ci.service;

import com.ci.pojo.entity.Category;
import com.ci.pojo.vo.CateView;

import java.util.List;

public interface CateService {
    Category selectByName(String name);

    List<CateView> selectAllCateView();

}
