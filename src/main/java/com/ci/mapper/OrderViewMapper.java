package com.ci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ci.pojo.vo.OrderListView;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderViewMapper extends BaseMapper<OrderListView> {
}
