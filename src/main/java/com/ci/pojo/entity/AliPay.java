package com.ci.pojo.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/7 16:33
 */

@Data
@ToString
public class AliPay {
    private String traceNo;  // 订单号
    private double totalPrice;  // 订单金额
    private String subject;   // 订单标题
    private String alipayTradeNo;  // 支付宝交易号
}
