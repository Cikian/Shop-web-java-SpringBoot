package com.ci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ci.pojo.entity.Cart;
import com.ci.pojo.vo.CartView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    
    @Select("select * from cart_view where user_id = #{userId}")
    List<CartView> getAllCartsByUserId(String userId);
}
