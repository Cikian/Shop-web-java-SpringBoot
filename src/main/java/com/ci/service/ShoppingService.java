package com.ci.service;

import com.ci.pojo.entity.Shopping;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ShoppingService {
    String add(Shopping shopping);
}
