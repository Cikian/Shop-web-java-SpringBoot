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
@TableName("shopping")
public class Shopping {
    @TableId(type = IdType.ASSIGN_ID)
    private String shoppingId;
    private String userId;
    private String orderId;
    @NotBlank(message = "收货人姓名不能为空")
    private String receiverName;  // 收货姓名
    @NotBlank(message = "收货人电话不能为空")
    private String receiverPhone;  // 收货电话
    @NotBlank(message = "收货人地区不完整")
    private String receiverProvince;  // 省份
    @NotBlank(message = "收货人地区不完整")
    private String receiverCity;  // 城市
    @NotBlank(message = "收货人地区不完整")
    private String receiverDistrict;  // 区/县
    @NotBlank(message = "收货人地区不完整")
    private String receiverAddress;  // 详细地址
    private String createTime;
    private String updateTime;
}
