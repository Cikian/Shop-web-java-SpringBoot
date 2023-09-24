package com.ci.controller;

import com.ci.pojo.entity.User;
import com.ci.pojo.vo.ErrorCode;
import com.ci.pojo.vo.Result;
import com.ci.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description: TODO
 * @date 2023/9/13 14:13
 */

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    UserService userService;

    @GetMapping
    public Result getAll() {
        List<User> allUsers = userService.getAll();
        Integer code = allUsers != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = allUsers != null ? "":"查询失败";
        return new Result(code, allUsers, msg);
    }

    @GetMapping("/{userId}")
    public Result getUserById(@PathVariable String userId) {
        User user = userService.getById(userId);
        Integer code = user != null ? ErrorCode.GET_SUCCESS : ErrorCode.GET_FAIL;
        String msg = user != null ? "":"查询失败";
        return new Result(code, user, msg);
    }
}
