package com.ci.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ci.dao.UserDao;
import com.ci.mapper.UserMapper;
import com.ci.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

    @Override
    public User getById(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public boolean add(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public User getByName(String name) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserName, name);
        return userMapper.selectOne(lqw);
    }

    @Override
    public boolean update(User user) {
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.eq("user_id", user.getUserId());
        return userMapper.update(user, uw) > 0;
    }

    @Override
    public boolean updatePasswd(String newPasswd, User user) {
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.eq("user_id", user.getUserId());
        user.setPassword(newPasswd);
        return userMapper.update(user, uw) > 0;
    }

    @Override
    public boolean delete(String userId) {
        return userMapper.deleteById(userId) > 0;
    }

}
