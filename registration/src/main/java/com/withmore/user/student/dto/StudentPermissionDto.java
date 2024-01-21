package com.withmore.user.student.dto;

import com.withmore.user.permission.entity.PermissionNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class StudentPermissionDto extends PermissionNode {
    private String stuNumber;
}
