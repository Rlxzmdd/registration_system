package com.withmore.activity.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ActivityInfo extends ActivityItemInfo{
    String realName;
}
