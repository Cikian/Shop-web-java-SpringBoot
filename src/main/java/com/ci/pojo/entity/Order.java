package com.ci.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("orders")
public class Order {
    @TableId(type = IdType.ASSIGN_ID)
    private String orderId;
    @NotNull
    private String userId;

    @NotNull
    private String shoppingId;  // 收货信息

    @NotNull
    private Double payment;     // 订单金额

    @NotNull
    private Integer paymentType; // 支付类型：1-在线支付
    private Integer postPrice;  // 运费

    private Integer status;  // 订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
    private String paymentTime;  // 支付时间
    private String sendTime;  // 发货时间
    private String endTime;
    private String closeTime;
    private String createTime;
    private String updateTime;
}
