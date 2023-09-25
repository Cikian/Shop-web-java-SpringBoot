package com.ci.controller;

import com.ci.pojo.entity.Cart;
import com.ci.pojo.entity.User;
import com.ci.pojo.vo.CartView;
import com.ci.pojo.vo.ErrorCode;
import com.ci.pojo.vo.Result;
import com.ci.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO 购物车controller
 * @date 2023/9/25 15:02
 */

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping
    public Result getAll(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("购物车获取session用户：" + user);
        String userId = user.getUserId();
        List<Cart> allCarts = cartService.getAllCartsByUserId(userId);
        return new Result(ErrorCode.GET_SUCCESS, allCarts, "查询" + userId + "购物车成功");
    }

    @PutMapping
    public Result add(@RequestBody @Valid Cart cart, BindingResult bindingResult) {
        for (ObjectError error : bindingResult.getAllErrors()) {
            return new Result(ErrorCode.ADD_FAIL, null, error.getDefaultMessage());
        }
        boolean flag = cartService.add(cart);
        String msg = flag ? "添加购物车成功" : "添加购物车失败";
        Integer code = flag ? ErrorCode.ADD_SUCCESS : ErrorCode.ADD_FAIL;
        return new Result(code, null, msg);
    }

    @GetMapping("/test")
    public User test(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }
}
