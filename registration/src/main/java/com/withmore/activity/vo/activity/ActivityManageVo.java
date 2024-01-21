package com.withmore.activity.vo.activity;

import com.withmore.activity.vo.activityitemmanagelist.ActivityItemManageListInfoVo;
import lombok.Data;

@Data
public class ActivityManageVo extends ActivityItemManageListInfoVo {

    public String realName;

    /**
     * 核销数量
     */
    public Integer examineNum;
}
