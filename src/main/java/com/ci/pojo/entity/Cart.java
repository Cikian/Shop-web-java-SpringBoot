package com.ci.pojo.entity;

import lombok.Data;

@Data
public class Cart {
    private String carId;
    private String userId; //用户表id
    private String goodId; //商品id
    private Integer count; //数量
    private String createTime;
    private String updateTime;
}
