package com.withmore.user.wechat.dto;

import lombok.Data;

/**
 * 微信学生登录Dto
 */
@Data
public class WechatStudentLoginDto {
    /*学号*/
    private String stuNumber;
    /*姓名*/
    private String realName;
}
