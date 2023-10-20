package com.ci.controller;

import com.ci.pojo.entity.Shopping;
import com.ci.pojo.vo.ErrorCode;
import com.ci.pojo.vo.Result;
import com.ci.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/10 9:28
 */
@RestController
@RequestMapping("/shopping")
public class ShoppingController {
    @Autowired
    ShoppingService shoppingService;
    
    @GetMapping("/{orderId}")
    public Result getShoppingByOrderId(@PathVariable String orderId) {
        Shopping shopping = shoppingService.getByOrderId(orderId);
        return new Result(ErrorCode.GET_SUCCESS, shopping, "查询订单" + orderId + "的收货信息成功");
    }
    
}
