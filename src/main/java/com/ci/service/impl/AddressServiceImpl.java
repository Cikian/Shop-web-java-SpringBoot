package com.ci.service.impl;

import com.ci.dao.AddressDao;
import com.ci.pojo.entity.Address;
import com.ci.pojo.vo.AddressListView;
import com.ci.service.AddressService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/9 19:41
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressDao addressDao;

    @NotNull
    private static AddressListView getAddressListView(Address addressItem) {
        String address = addressItem.getProvince() + addressItem.getCity() + addressItem.getCity() + addressItem.getCounty() + addressItem.getAddressDetail();
        AddressListView addressListView = new AddressListView();
        addressListView.setId(addressItem.getId());
        addressListView.setName(addressItem.getName());
        addressListView.setTel(addressItem.getTel());
        addressListView.setAddress(address);
        addressListView.setAreaCode(addressItem.getAreaCode());
        addressListView.setDefault(!(addressItem.getIsDefault().equals("false")));
        return addressListView;
    }

    @Override
    public Address getById(String id) {
        return addressDao.getById(id);
    }

    @Override
    public boolean add(String userId, Address address) {
        address.setUserId(userId);
        if (address.getIsDefault().equals("true")) {
            boolean updateFlag = addressDao.updateDefault(userId);
        }
        return addressDao.add(address);
    }

    @Override
    public List<AddressListView> getByUserId(String userId) {
        List<Address> addressList = addressDao.getByUserId(userId);
        List<AddressListView> addressViewList = new ArrayList<>();
        if (addressList != null) {
            for (Address addressItem : addressList) {
                AddressListView addressListView = getAddressListView(addressItem);
                addressViewList.add(addressListView);
            }
        }
        return addressViewList;
    }

    @Override
    public boolean deleteById(String id) {
        return addressDao.deleteById(id);
    }

    @Override
    public boolean updateById(Address address) {
        if (address.getIsDefault().equals("true")) {
            boolean updateFlag = addressDao.updateDefault(address.getUserId());
        }
        return addressDao.updateById(address);
    }

    @Override
    public Address selectDefault(String userId) {
        Address address = addressDao.selectDefault(userId);
        if (address == null) {
            List<Address> addressList = addressDao.getByUserId(userId);
            if (addressList != null && !addressList.isEmpty()) {
                address = addressList.get(0);
            }
        }
        return address;
    }
}
