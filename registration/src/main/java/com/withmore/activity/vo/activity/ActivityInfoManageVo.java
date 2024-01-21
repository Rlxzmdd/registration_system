package com.withmore.activity.vo.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ActivityInfoManageVo extends ActivityInfoVo{
    /**
     * 是否允许下载数据
     */
    private Integer isRead;
    /**
     * 是否允许修改
     */
    private Integer isModify;
    /**
     * 是否允许核销人员
     */
    private Integer isExamine;
    /**
     * 是否允许邀请管理员
     */
    private Integer isInvite;
}
