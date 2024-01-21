package com.withmore.user.wechat.dto;

import lombok.Data;

@Data
public class WechatStudentEXAMLoginDto {
    /*考生号*/
    private String examNumber;
    /*真实姓名*/
    private String realName;
    /*身份证后六位*/
    private String lastIdNumber;
}
