package com.ci.pojo.entity;


import lombok.Data;

@Data
public class Shopping {
    private String shoppingId;
    private String userId;
    private String orderId;
    private String receiverName;  // 收货姓名
    private String receiverPhone;  // 收货电话
    private String receiverProvince;  // 省份
    private String receiverCity;  // 城市
    private String receiverDistrict;  // 区/县
    private String receiverAddress;  // 详细地址
    private String createTime;
    private String updateTime;
}
