package com.withmore.activity.vo.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.withmore.activity.entity.ActivityItemManageList;
import com.withmore.activity.vo.activityexamine.ActivityExamineListVo;
import com.withmore.activity.vo.activityitemmanagelist.ActivityItemManageListInfoVo;
import com.withmore.activity.vo.activityitemmanagelist.ActivityItemManageListListVo;
import com.withmore.activity.vo.activityitemprofile.ActivityItemProfileInfoVo;
import com.withmore.activity.vo.activitysignup.ActivitySignUpListVo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ActivityInfoVo {

    /**
     * 活动信息表ID
     */
    private Integer id;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动小标题
     */
    private String location;

    /**
     * 创建活动者
     */
    private String creatorNumber;
    private String creatorType;
    private String realName;

    /**
     * 是否长期开放
     */
    private Integer isDurative;

    /**
     * 是否需要报名
     */
    private Integer isApply;

    /**
     * 是否允许重新核销
     */
    private Integer isRepeat;

    /**
     * 是否允许转发
     */
    private Integer isShare;

    /**
     * 最大人数
     */
    private Integer maxNum;

    /**
     * 报名开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTimeStart;

    /**
     * 报名结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applyTimeEnd;

    /**
     * 活动开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date activityTimeStart;

    /**
     * 活动结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date activityTimeEnd;

    private Integer isOwner;

    private Integer isManager;

    private Integer isExaminer;

    private Integer isSignup;
    private Integer isApproval;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
