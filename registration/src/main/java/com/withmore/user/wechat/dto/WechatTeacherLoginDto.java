package com.withmore.user.wechat.dto;

import lombok.Data;

@Data
public class WechatTeacherLoginDto {
    /*教师工号*/
    private String tchNumber;
    /*登录密码*/
    private String password;
}
