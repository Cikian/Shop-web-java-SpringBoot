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

    @GetMapping("/{VsUserId}")
    public Result getAll(@PathVariable String VsUserId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("购物车获取session用户：" + user);
        String JsUserId = user.getUserId();
        if (!VsUserId.equals(JsUserId)) {
            return new Result(ErrorCode.GET_FAIL, null, "登录信息失效，请重新登录");
        }
        List<CartView> allCarts = cartService.getAllCartsByUserId(JsUserId);
        return new Result(ErrorCode.GET_SUCCESS, allCarts, "查询" + JsUserId + "购物车成功");
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

    @DeleteMapping("/{VsUserId}/{goodId}")
    public Result delete(@PathVariable String VsUserId, @PathVariable String goodId, HttpServletRequest request) {
        System.out.println("--------------------用户id和商品id：" + VsUserId + " " + goodId);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("购物车获取session用户：" + user);
        String JsUserId = user.getUserId();

        System.out.println("参数userid和sessionuserid：" + VsUserId + " " + JsUserId);
        if (!VsUserId.equals(JsUserId)) {
            return new Result(ErrorCode.DELETE_FAIL, null, "登录信息失效，请重新登录");
        }
        boolean flag = cartService.delete(JsUserId, goodId);
        String msg = flag ? "删除购物车成功" : "删除购物车失败";
        Integer code = flag ? ErrorCode.DELETE_SUCCESS : ErrorCode.DELETE_FAIL;
        return new Result(code, null, msg);
    }

    @GetMapping("/test")
    public User test(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }
}
