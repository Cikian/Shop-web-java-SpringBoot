package com.ci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ci.pojo.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CateMapper extends BaseMapper<Category> {
}
