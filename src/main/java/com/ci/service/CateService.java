package com.ci.service;

import com.ci.pojo.entity.Category;

public interface CateService {
    Category selectByName(String name);
}
