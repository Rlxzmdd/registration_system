package com.withmore.shiro.principal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WechatStudentExamCredentials {
    /*考生号*/
    private String examNumber;
    /*身份证后六位*/
    private String lastIdNumber;
}
