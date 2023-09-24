package com.ci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ci.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    
}
