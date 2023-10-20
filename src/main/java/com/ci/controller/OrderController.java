package com.ci.controller;

import com.ci.exception.BusinessException;
import com.ci.pojo.entity.Order;
import com.ci.pojo.entity.OrderItem;
import com.ci.pojo.entity.Shopping;
import com.ci.pojo.entity.User;
import com.ci.pojo.vo.ErrorCode;
import com.ci.pojo.vo.OrderListView;
import com.ci.pojo.vo.OrderView;
import com.ci.pojo.vo.Result;
import com.ci.service.OrderItemService;
import com.ci.service.OrderService;
import com.ci.service.ShoppingService;
import com.ci.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO 2023/9/26 下订单：插入订单、插入收货信息、插入订单子项（更新库存）
 * @date 2023/9/26 10:00
 */

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    ShoppingService shoppingService;
    @Autowired
    OrderItemService orderItemService;

    // TODO: 2023/9/26 下订单：插入订单、插入收货信息、插入订单子项（更新库存）

    @Transactional
    @PutMapping
    public Result addOrder(@RequestBody @Valid OrderView orderView,
                           BindingResult bindingResult,
                           HttpServletRequest request) {

        for (Object error : bindingResult.getAllErrors()) {
            return new Result(ErrorCode.ADD_FAIL, null, error.toString());
        }

        // 获取当前登录用户的id, 与订单中的userId进行比较
        HttpSession session = request.getSession();
        User SessionUser = (User) session.getAttribute("user");
        String JSuserId = null;
        try {
            JSuserId = SessionUser.getUserId();
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.BUSINESS_ERR, "登录信息失效，请重新登录");
        }
        String VSuserId = orderView.getUserId();
        if (!JSuserId.equals(VSuserId) || JSuserId == null || VSuserId == null) {
            // 手动回滚事务
            throw new BusinessException(ErrorCode.ADD_FAIL, "非法操作");
        }
        String userId = JSuserId;

        // 插入订单
        Order order = Order.builder()
                .userId(userId)
                .payment(orderView.getPayment())
                .paymentType(orderView.getPaymentType())
                .postPrice(orderView.getPostPrice())
                .remark(orderView.getRemark())
                .build();
        // 获取mybatis plus生成的订单id
        String orderId = orderService.add(order);
        if (orderId == null) {
            // 手动回滚事务
            throw new BusinessException(ErrorCode.ADD_FAIL, "系统异常，请稍后再试");
        }

        // 将收货信息插入到shopping表中
        Shopping shopping = OrderView.builder()
                .shopping(orderView.getShopping())
                .build()
                .getShopping();
        shopping.setUserId(userId);
        shopping.setOrderId(orderId);
        String shoppingId = shoppingService.add(shopping);
        if (shoppingId == null) {
            // 手动回滚事务
            throw new BusinessException(ErrorCode.ADD_FAIL, "系统异常，请稍后再试");
        }
        // 更新订单中的shoppingId
        order.setShoppingId(shoppingId);
        boolean updateOrderFlag = orderService.update(order);
        if (!updateOrderFlag) {
            // 手动回滚事务
            throw new BusinessException(ErrorCode.ADD_FAIL, "系统异常，请稍后再试");
        }

        // 插入订单子项
        List<OrderItem> orderItems = orderView.getOrderItemList();
        System.out.println("子项列表：" + orderItems);
        boolean addOrderItemFlag = orderItemService.addOrderItem(orderItems, orderId, userId);
        if (!addOrderItemFlag) {
            // 手动回滚事务
            throw new BusinessException(ErrorCode.ADD_FAIL, "商品库存不足");
        }
        // 返回订单id
        return new Result(ErrorCode.ADD_SUCCESS, orderId, "下单成功");
    }

    @DeleteMapping("/close")
    public Result closeOrder(@RequestParam String orderId, HttpServletRequest request) {
        System.out.println("关闭订单的id：" + orderId);
        Order order = orderService.getById(orderId);
        if (order == null) {
            return new Result(ErrorCode.UPDATE_FAIL, null, "订单不存在");
        }
        User sessionUser = (User) request.getSession().getAttribute("user");
        String JsUserId = null;
        try {
            JsUserId = sessionUser.getUserId();
        } catch (Exception e) {
            return new Result(ErrorCode.UPDATE_FAIL, null, "登录信息失效，请重新登录");
        }
        if (!JsUserId.equals(order.getUserId())) {
            return new Result(ErrorCode.UPDATE_FAIL, null, "非法操作");
        }
        // 将子订单中的商品库存加回去
        List<OrderItem> orderItems = orderItemService.getByOrderId(orderId);
        boolean addStockFlag = orderItemService.addStock(orderItems);
        if (!addStockFlag) {
            return new Result(ErrorCode.UPDATE_FAIL, null, "系统异常，请稍后再试");
        }
        boolean closeOrderFlag = orderService.closeOrder(orderId);
        if (!closeOrderFlag) {
            return new Result(ErrorCode.UPDATE_FAIL, null, "关闭订单失败");
        }
        return new Result(ErrorCode.UPDATE_SUCCESS, null, "关闭订单成功");
    }

    @DeleteMapping("/{orderId}")
    public Result deleteOrder(@PathVariable String orderId, HttpServletRequest request) {
        System.out.println(orderId);
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        Order order = orderService.getById(orderId);
        if (order == null) {
            return new Result(ErrorCode.DELETE_FAIL, null, "订单不存在");
        }
        User sessionUser = (User) request.getSession().getAttribute("user");
        String JsUserId = null;
        try {
            JsUserId = sessionUser.getUserId();
        } catch (Exception e) {
            return new Result(ErrorCode.DELETE_FAIL, null, "登录信息失效，请重新登录");
        }
        if (!JsUserId.equals(order.getUserId())) {
            return new Result(ErrorCode.DELETE_FAIL, null, "非法操作");
        }
        boolean deleteOrderItemFlag = orderItemService.deleteById(orderId);
        if (!deleteOrderItemFlag) {
            // 手动回滚事务
            throw new BusinessException(ErrorCode.DELETE_FAIL, "系统异常，请稍后再试");
        }
        boolean deleteShoppingFlag = shoppingService.deleteById(orderId);
        if (!deleteShoppingFlag) {
            // 手动回滚事务
            throw new BusinessException(ErrorCode.DELETE_FAIL, "系统异常，请稍后再试");
        }

        boolean deleteOrderFlag = orderService.deleteById(orderId, JsUserId);
        if (!deleteOrderFlag) {
            throw new BusinessException(ErrorCode.DELETE_FAIL, "系统异常，请稍后再试");
        }
        return new Result(ErrorCode.DELETE_SUCCESS, null, "删除订单成功");
    }

    @GetMapping("/myOrders/{VsUserId}")
    public Result getAllOrderByUserId(@PathVariable String VsUserId, HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        String JsUserId = null;
        try {
            JsUserId = sessionUser.getUserId();
        } catch (Exception e) {
            return new Result(ErrorCode.GET_FAIL, null, "登录信息失效，请重新登录");
        }
        if (!JsUserId.equals(VsUserId)) {
            return new Result(ErrorCode.GET_FAIL, null, "非法操作");
        }
        List<OrderListView> orders = orderService.getAllByUserId(VsUserId);
        return new Result(ErrorCode.GET_SUCCESS, orders, "查询成功");
    }

    @GetMapping("/{orderId}")
    public Result getOrderById(@PathVariable String orderId, HttpServletRequest request) {
        Order order = orderService.getById(orderId);
        if (order == null) {
            return new Result(ErrorCode.GET_FAIL, null, "查询失败");
        }
        User sessionUser = (User) request.getSession().getAttribute("user");
        String JsUserId = null;
        try {
            JsUserId = sessionUser.getUserId();
        } catch (Exception e) {
            return new Result(ErrorCode.GET_FAIL, null, "登录信息失效，请重新登录");
        }
        if (!JsUserId.equals(order.getUserId())) {
            return new Result(ErrorCode.GET_FAIL, null, "非法操作");
        }
        return new Result(ErrorCode.GET_SUCCESS, order, "查询成功");
    }

    @GetMapping("/items/{orderId}")
    public Result getOrderItemsByOrderId(@PathVariable String orderId, HttpServletRequest request) {
        Order order = orderService.getById(orderId);
        if (order == null) {
            return new Result(ErrorCode.GET_FAIL, null, "查询失败");
        }
        User sessionUser = (User) request.getSession().getAttribute("user");
        String JsUserId = null;
        try {
            JsUserId = sessionUser.getUserId();
        } catch (Exception e) {
            return new Result(ErrorCode.GET_FAIL, null, "登录信息失效，请重新登录");
        }
        if (!JsUserId.equals(order.getUserId())) {
            return new Result(ErrorCode.GET_FAIL, null, "非法操作");
        }
        List<OrderItem> orderItems = orderItemService.getByOrderId(orderId);
        return new Result(ErrorCode.GET_SUCCESS, orderItems, "查询成功");
    }

    @GetMapping("/new")
    public Result getNewOrder(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        String JsUserId = null;
        try {
            JsUserId = sessionUser.getUserId();
        } catch (Exception e) {
            return new Result(ErrorCode.GET_FAIL, null, "登录信息失效，请重新登录");
        }
        Order order = orderService.getNewOrder(JsUserId);
        return new Result(ErrorCode.GET_SUCCESS, order, "查询成功");
    }

    @GetMapping("/unpaid")
    public Result getWaitPayOrder(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        String JsUserId = null;
        try {
            JsUserId = sessionUser.getUserId();
        } catch (Exception e) {
            return new Result(ErrorCode.GET_FAIL, null, "登录信息失效，请重新登录");
        }
        List<OrderListView> orders = orderService.getWaitPayOrder(JsUserId);
        return new Result(ErrorCode.GET_SUCCESS, orders, "查询成功");
    }

    @GetMapping("/unshipped")
    public Result getWaitPostOrder(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        String JsUserId = null;
        try {
            JsUserId = sessionUser.getUserId();
        } catch (Exception e) {
            return new Result(ErrorCode.GET_FAIL, null, "登录信息失效，请重新登录");
        }
        List<OrderListView> orders = orderService.getWaitPostOrder(JsUserId);
        return new Result(ErrorCode.GET_SUCCESS, orders, "查询成功");
    }

    @GetMapping("/shipped")
    public Result getShippedOrder(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        String JsUserId = null;
        try {
            JsUserId = sessionUser.getUserId();
        } catch (Exception e) {
            return new Result(ErrorCode.GET_FAIL, null, "登录信息失效，请重新登录");
        }
        List<OrderListView> orders = orderService.getShippedOrder(JsUserId);
        return new Result(ErrorCode.GET_SUCCESS, orders, "查询成功");
    }

    @GetMapping("/received")
    public Result getReceivedOrder(HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        String JsUserId = null;
        try {
            JsUserId = sessionUser.getUserId();
        } catch (Exception e) {
            return new Result(ErrorCode.GET_FAIL, null, "登录信息失效，请重新登录");
        }
        List<OrderListView> orders = orderService.getReceivedOrder(JsUserId);
        return new Result(ErrorCode.GET_SUCCESS, orders, "查询成功");
    }

    @GetMapping("/test")
    public Result test() {
        String nowTime = TimeUtils.getNowTime();
        return new Result(ErrorCode.GET_SUCCESS, nowTime, "当前时间");
    }
}
