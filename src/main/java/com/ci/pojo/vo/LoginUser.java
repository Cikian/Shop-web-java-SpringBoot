package com.ci.pojo.vo;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
    private String userName;
    private String password;
}
