package com.withmore.activity.query;

import lombok.Data;


@Data
public class ActivityManageDelQuery extends ActivityManageListQuery {
    private String userNumber;
    private String userType;
}
