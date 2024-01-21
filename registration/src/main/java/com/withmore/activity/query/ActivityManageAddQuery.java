package com.withmore.activity.query;

import com.javaweb.system.common.BaseQuery;
import lombok.Data;


@Data
public class ActivityManageAddQuery extends ActivityManageListQuery {
    private String userNumber;
    private String userType;

    private Integer isRead;
    private Integer isModify;
    private Integer isExamine;
    private Integer isInvite;
}
