package com.ci.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("cart")
public class Cart {
    @TableId(type = IdType.ASSIGN_ID)
    private String cartId;

    @NotNull
    private String userId; // 用户表id

    @NotNull
    private String goodId; // 商品id

    @NotNull
    private Integer count; // 数量

    private String createTime;

    private String updateTime;
}
