package com.ci.controller;

import com.ci.exception.BusinessException;
import com.ci.pojo.entity.User;
import com.ci.pojo.vo.*;
import com.ci.service.UserService;
import com.ci.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;

/**
 * @author Cikian, shirley
 * @version 1.0
 * @description: TODO add注册功能
 * @date 2023/9/12 16:23
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/upload")
    public Result upload(MultipartFile file, String userId) {
        return FileUtils.uploadToUpYun(file, userId);
    }


    @PostMapping("/register")
    public Result register(@RequestBody @Valid User user, BindingResult bindingResult) {
        for (ObjectError error : bindingResult.getAllErrors()) {
            return new Result(ErrorCode.ADD_FAIL, null, error.getDefaultMessage());
        }
        if (userService.getByName(user.getUserName()) != null) {
            return new Result(ErrorCode.ADD_FAIL, null, "用户名已存在");
        }
        boolean flag = userService.add(user);
        String msg = flag ? "注册成功" : "注册失败";
        String url = flag ? "/user/login" : "/user/register";
        return new Result(flag ? ErrorCode.ADD_SUCCESS : ErrorCode.ADD_FAIL, url, msg);
    }

    /**
     * @param lUser
     * @return Result
     * @author shirley
     */
    @PostMapping
    public Result login(@RequestBody LoginUser lUser, HttpServletRequest request) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>userName:" + lUser.getUserName() + "password:" + lUser.getPassword());
        Result result = userService.login(lUser.getUserName(), lUser.getPassword());
        User user = null;
        try {
            user = (User) result.getData();
        } catch (Exception e) {
            user = null;
            throw new BusinessException(ErrorCode.SYSTEM_ERR, "访问超时，请稍后再试", e);
        } finally {
            if (user != null) {
                // 将用户信息存入session
                HttpSession session = request.getSession();
                System.out.println("返回的用户：" + user);
                session.setAttribute("user", user);
                return new Result(ErrorCode.LOGIN_SUCCESS, user, "登录成功", "/my");
            } else {
                return result;
            }

        }
    }

    // 获取当前用户
    @GetMapping("/currentUser")
    public Result getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new Result(ErrorCode.GET_FAIL, null, "获取失败");
        }
        return new Result(ErrorCode.GET_SUCCESS, user, "获取成功");
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return new Result(ErrorCode.COMMON_SUCCESS, "/user/login", "退出登录成功");
    }

    // 更新用户信息
    @PutMapping("/update")
    public Result update(@RequestBody @Valid User user, BindingResult bindingResult,
                         HttpServletRequest request) {
        for (ObjectError error : bindingResult.getAllErrors()) {
            if (error.getDefaultMessage().equals("密码不能为空")) {
                // 密码为空，跳过此次循环
                continue;
            }
            return new Result(ErrorCode.UPDATE_FAIL, null, error.getDefaultMessage());
        }
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        user.setUserId(currentUser.getUserId());

        boolean flag = userService.update(user);
        String msg = flag ? "更新成功" : "更新失败";
        return new Result(flag ? ErrorCode.UPDATE_SUCCESS : ErrorCode.UPDATE_FAIL, null, msg);
    }

    // 删除用户
    @DeleteMapping
    public Result delete(@RequestParam String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        String id = currentUser.getUserId().toString();
        Result result = userService.delete(currentUser.getUserId(), password);
        if (result.getCode() == ErrorCode.DELETE_SUCCESS) {
            session.removeAttribute("user");
        }
        return result;
    }

    // 更改密码
    @PutMapping("/updatePasswd")
    public Result updatePasswd(@RequestBody UpdatePwd upwd, HttpServletRequest request) {
        // System.out.println(upwd);
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        String prePasswdStr = upwd.getPrePasswd() + "cikian";
        String md5PrePassword = DigestUtils.md5DigestAsHex(prePasswdStr.getBytes(StandardCharsets.UTF_8));

        if (!md5PrePassword.equals(currentUser.getPassword())) {
//            currentUser.setPassword(upwd.getNewPasswd());
//            session.setAttribute("user", currentUser);
            return new Result(ErrorCode.UPDATE_FAIL, null, "原密码错误");
        }
        boolean flag = userService.updatePasswd(upwd.getNewPasswd(), currentUser);
        String msg = flag ? "更新成功" : "更新失败";
        // 清除session并重定向至登录页面
        session.removeAttribute("user");
        return new Result(flag ? ErrorCode.UPDATE_SUCCESS : ErrorCode.UPDATE_FAIL, currentUser, msg);
    }


    // TODO 2023.9.21 忘记密码修改功能
    // 忘记密码
    @GetMapping("/forgetPasswd/{userName}")
    public Result forgetPasswd(@PathVariable String userName) {
        User user = userService.getByName(userName);
        if (user == null) {
            return new Result(ErrorCode.UPDATE_FAIL, null, "该用户不存在", "");
        }
        return new Result(ErrorCode.GET_SUCCESS, user, "查询成功", "");
    }

    // 忘记密码后验证及重置
    @PutMapping("/forgetPasswd")
    public Result verifyAndUpdatePW(@RequestBody ForgetPasswd fp) {
        String answer = fp.getAnswer();
        User user = userService.getById(fp.getUserId());
        if (!(answer.equals(user.getAnswer()))) {
            return new Result(ErrorCode.UPDATE_FAIL, null, "密保验证失败", "");
        }
        boolean flag = userService.updatePasswd(fp.getNewPasswd(), user);
        Integer code = flag ? ErrorCode.UPDATE_SUCCESS : ErrorCode.UPDATE_FAIL;
        String msg = flag ? "密码修改成功，请登录" : "系统繁忙，请稍后再试";
        return new Result(code, null, msg, "/login");
    }

    // 查询全部用户
    @GetMapping
    public Result getAll() {
        return new Result(ErrorCode.COMMON_SUCCESS, userService.getAll(), "查询成功");
    }


    @GetMapping("/test")
    public User test(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user;
    }
}
