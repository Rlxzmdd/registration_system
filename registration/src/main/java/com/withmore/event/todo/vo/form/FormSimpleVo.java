package com.withmore.event.todo.vo.form;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class FormSimpleVo {
    private String formKey;
    private Integer id;
}
