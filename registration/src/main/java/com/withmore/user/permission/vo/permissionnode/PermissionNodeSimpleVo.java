package com.withmore.user.permission.vo.permissionnode;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PermissionNodeSimpleVo {
    private String college;
    private String major;
    private String classes;
    private String single;
    private Integer permissionId;
}
