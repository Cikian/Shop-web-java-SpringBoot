package com.ci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ci.pojo.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/9/26 10:28
 */

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}
