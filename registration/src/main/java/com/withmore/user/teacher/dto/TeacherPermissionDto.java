package com.withmore.user.teacher.dto;

import com.withmore.user.permission.entity.PermissionNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherPermissionDto extends PermissionNode {
    private String tchNumber;
}
