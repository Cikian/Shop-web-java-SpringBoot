package com.ci.service;

import com.ci.pojo.entity.User;
import com.ci.pojo.vo.Result;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
    List<User> getAll();

    User getById(String id);

    boolean add(User user);

    User getByName(String name);

    Result login(String name, String password);

    boolean update(User user);

    Result delete(String userId, String password);

    boolean updatePasswd(String newPasswd,User user);

}
