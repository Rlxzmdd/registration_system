package com.withmore.event.todo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("event_form_permission")
public class FormPermission extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /*表单权限节点ID*/
    private Integer permissionId;

    /*表单ID*/
    private Integer formId;
}
