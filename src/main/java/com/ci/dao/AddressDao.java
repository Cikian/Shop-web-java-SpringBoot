package com.ci.dao;

import com.ci.pojo.entity.Address;

import java.util.List;

public interface AddressDao {
    Address getById(String id);
    Address selectDefault(String userId);

    boolean updateDefault(String userId);

    boolean add(Address address);
    
    List<Address> getByUserId(String userId);

    boolean deleteById(String id);
    
    boolean updateById(Address address);
}
