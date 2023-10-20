package com.ci.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ci.dao.AddressDao;
import com.ci.mapper.AddressMapper;
import com.ci.pojo.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/9 19:39
 */

@Repository
public class AddressDaoImpl implements AddressDao {
    @Autowired
    AddressMapper addressMapper;

    @Override
    public Address getById(String id) {
        QueryWrapper<Address> qw = new QueryWrapper<>();
        qw.eq("id", id);
        return addressMapper.selectOne(qw);
    }

    @Override
    public Address selectDefault(String userId) {
        QueryWrapper<Address> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        qw.eq("is_default", "true");
        return addressMapper.selectOne(qw);
    }

    @Override
    public boolean updateDefault(String userId) {
        UpdateWrapper<Address> uw = new UpdateWrapper<>();
        uw.eq("user_id", userId);
        uw.eq("is_default", "true");
        uw.set("is_default", "false");
        return addressMapper.update(null, uw) > 0;
    }

    @Override
    public boolean add(Address address) {
        return addressMapper.insert(address) > 0;
    }

    @Override
    public List<Address> getByUserId(String userId) {
        QueryWrapper<Address> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        return addressMapper.selectList(qw);
    }

    @Override
    public boolean deleteById(String id) {
        QueryWrapper<Address> qw = new QueryWrapper<>();
        qw.eq("id", id);
        return addressMapper.delete(qw) > 0;
    }

    @Override
    public boolean updateById(Address address) {
        QueryWrapper<Address> qw = new QueryWrapper<>();
        qw.eq("id", address.getId());
        return addressMapper.update(address, qw) > 0;
    }
}
