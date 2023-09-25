package com.ci.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private String userId;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    private String nickName;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "密码不能为空")
    @Length(max = 16, min = 6, message = "密码长度必须在6-16位之间")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", message = "密码只能包含中文、英文、数字和下划线")
    private String password;

    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "手机号码格式不正确")
    @Size(max = 11, min = 11, message = "手机号码长度不正确")
    private String phone;

    @NotBlank(message = "密保问题不能为空")
    private String question; // 找回密码问题

    @NotBlank(message = "答案不能为空")
    private String answer;  // 找回密码答案

    private Integer role;  // 0:管理员 1:普通用户

    private String avatar;  // 头像

    private String createTime;

    private String updateTime;

}
