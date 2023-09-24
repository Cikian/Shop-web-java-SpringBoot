package com.ci.service.impl;

import com.ci.dao.UserDao;
import com.ci.pojo.entity.User;
import com.ci.pojo.vo.ErrorCode;
import com.ci.pojo.vo.Result;
import com.ci.service.UserService;
import com.ci.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> getAll() {
        List<User> allUsers = userDao.getAll();
        return allUsers;
    }

    @Override
    public User getById(String userId) {
        User user = userDao.getById(userId);
        return user;
    }

    @Override
    public User getByName(String name) {
        User user = userDao.getByName(name);
        return user;
    }

    @Override
    public boolean add(User user) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        // 获取当前系统时间
        String nowTime = formatter.format(date);
        System.out.println("当前时间为：" + nowTime);
        user.setCreateTime(nowTime);
        user.setUpdateTime(nowTime);
        String password = user.getPassword() + "cikian";
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        user.setPassword(md5Password);
        boolean flag = userDao.add(user);
        return flag;
    }

    @Override
    public Result login(String userName, String password) {
        Result result = new Result();
        User user = this.getByName(userName);
        if (user == null) {
            result.setCode(ErrorCode.LOGIN_FAIL);
            result.setData("/user/login");
            result.setMsg("用户名不存在");
            return result;
        } else {
            boolean flag = UserUtils.checkPassword(password, user.getPassword());
            if (flag) {
                result.setCode(ErrorCode.GET_SUCCESS);
                result.setData(user);
                return result;
            } else {
                result.setCode(ErrorCode.GET_FAIL);
                result.setData("/user/login");
                result.setMsg("密码错误");
                return result;
            }
        }
    }

    @Override
    public boolean update(User user) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        // 获取当前系统时间
        String nowTime = formatter.format(date);
        user.setUpdateTime(nowTime);
        boolean flag = userDao.update(user);
        return flag;
    }

    @Override
    public boolean updatePasswd(String newPasswd, User user) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        // 获取当前系统时间
        String nowTime = formatter.format(date);
        System.out.println("当前时间为：" + nowTime);
        newPasswd += "cikian";
        String md5NewPasswd = DigestUtils.md5DigestAsHex(newPasswd.getBytes(StandardCharsets.UTF_8));
        user.setUpdateTime(nowTime);
        boolean flag = userDao.updatePasswd(md5NewPasswd, user);
        return flag;
    }

    @Override
    public Result delete(String userId, String password) {
        User user = this.getById(userId);
        if (!UserUtils.checkPassword(password, user.getPassword())) {
            return new Result(ErrorCode.DELETE_FAIL, null, "密码错误");
        }
        boolean flag = userDao.delete(userId);
        String msg = flag ? "删除成功" : "删除失败";
        String url = flag ? "/user/login" : "";
        return new Result(flag ? ErrorCode.DELETE_SUCCESS : ErrorCode.DELETE_FAIL, url, msg);
    }

}
