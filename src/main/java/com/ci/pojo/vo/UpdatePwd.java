package com.ci.pojo.vo;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePwd {
    private String prePasswd;
    private String newPasswd;
}
