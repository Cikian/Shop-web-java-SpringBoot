package com.ci.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/9 19:33
 */

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("addresses")
public class Address {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    @NotBlank(message = "用户名不能为空")
    private String userId;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号码格式不正确")
    @Size(max = 11, min = 11, message = "手机号码长度不正确")
    private String tel;
    @NotBlank(message = "地址有误")
    private String province;
    @NotBlank(message = "地址有误")
    private String city;
    @NotBlank(message = "地址有误")
    private String county;
    @NotBlank
    private String areaCode;
    @NotBlank(message = "地址有误")
    private String addressDetail;
    private String isDefault;
}
