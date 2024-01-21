package com.withmore.user.student.query;

import com.javaweb.system.common.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentListQuery extends BaseQuery {
    private String classes;
    private String college;
    private String major;
}
