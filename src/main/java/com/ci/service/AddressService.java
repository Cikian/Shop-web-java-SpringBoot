package com.ci.service;

import com.ci.pojo.entity.Address;
import com.ci.pojo.vo.AddressListView;

import java.util.List;

public interface AddressService {
    Address getById(String id);
    boolean add(String userId, Address address);
    
    List<AddressListView> getByUserId(String userId);

    boolean deleteById(String id);

    boolean updateById(Address address);

    Address selectDefault(String userId);
}
