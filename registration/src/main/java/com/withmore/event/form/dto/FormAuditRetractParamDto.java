package com.withmore.event.form.dto;

import lombok.Data;

import java.util.List;

@Data
public class FormAuditRetractParamDto {
    private String formKey;
    private List<String> submitNumber;
}
