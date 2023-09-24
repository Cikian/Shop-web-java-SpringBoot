package com.ci.pojo.entity;

import lombok.Data;

@Data
public class Order {
    private String orderId;
    private String userId;
    private String shoppingId;
    private Double payment;
    private Integer paymentType;
    private Integer postPrice;
    private Integer status;  // 订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
    private String paymentTime;
    private String sendTime;
    private String endTime;
    private String closeTime;
    private String createTime;
    private String updateTime;
}
