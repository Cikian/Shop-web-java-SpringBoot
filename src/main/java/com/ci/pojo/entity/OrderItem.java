package com.ci.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("orderitem")
public class OrderItem {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;  //订单子表id
    private String orderId;
    private String userId;
    private String goodId;
    private String goodName;
    private String goodImage; //商品图片地址
    private Double currentPrice; //生成订单时的商品单价
    private Integer count; //商品数量
    private Double totalPrice; //商品总价
    private String createTime;
    private String updateTime;
}
