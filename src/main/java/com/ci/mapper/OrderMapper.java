package com.ci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ci.pojo.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
