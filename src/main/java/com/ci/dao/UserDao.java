package com.ci.dao;

import com.ci.pojo.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();
    User getById(String userId);
    boolean add(User user);
    User getByName(String name);
    boolean update(User user);
    boolean updatePasswd(String newPasswd,User user);
    boolean delete(String userId);
    
}
