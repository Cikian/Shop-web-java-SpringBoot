package com.ci.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        List<User> allUsers = userMapper.selectList(null);
        return allUsers;
    }

    @Override
    public User getById(String userId) {
        User user = userMapper.selectById(userId);
        return user;
    }

    @Override
    public boolean add(User user) {
        boolean flag = userMapper.insert(user) > 0;
        return flag;
    }

    @Override
    public User getByName(String name) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
        lqw.eq(User::getUserName, name);
        User userRes = userMapper.selectOne(lqw);
        return userRes;
    }

    @Override
    public boolean update(User user) {
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.eq("user_id", user.getUserId());
        boolean flag = userMapper.update(user, uw) > 0;
        return flag;
    }

    @Override
    public boolean updatePasswd(String newPasswd, User user) {
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.eq("user_id", user.getUserId());
        user.setPassword(newPasswd);
        boolean flag = userMapper.update(user, uw) > 0;
        return flag;
    }

    @Override
    public boolean delete(String userId) {
        return userMapper.deleteById(userId) > 0;
    }

}
