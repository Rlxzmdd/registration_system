package com.withmore.activity.query;

import com.javaweb.system.common.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Data
public class ActivityDataPermissionNodeQuery extends BaseQuery {
    public Integer activityId;
    public List<Integer> nodes;
}
