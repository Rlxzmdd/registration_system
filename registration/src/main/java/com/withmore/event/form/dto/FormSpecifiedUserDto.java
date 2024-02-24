package com.withmore.event.form.dto;

import lombok.Data;

@Data
public class FormSpecifiedUserDto {
    /*指定用户学号、工号*/
    private String specifiedNumber;
    /*用户类型*/
    private String specifiedType;
}
