package com.ci.pojo.vo;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ForgetPasswd {
    private String userId;
    private String question;
    private String answer;
    private String newPasswd;
    private String userName;
}
