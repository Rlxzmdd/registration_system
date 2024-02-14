package com.withmore.activity.query;

import com.javaweb.system.common.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityExamineListQuery extends BaseQuery {
    private Integer activityId;
    private Integer serialNum;
}
