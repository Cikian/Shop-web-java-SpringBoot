package com.ci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ci.pojo.entity.Address;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper extends BaseMapper<Address> {
}
