package com.withmore.event.todo.dto;

import lombok.Data;

import java.util.List;

@Data
public class FormAuditRetractParamDto {
    private String formKey;
    private List<String> submitNumber;
}
