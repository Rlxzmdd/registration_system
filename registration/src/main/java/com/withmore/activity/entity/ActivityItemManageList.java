// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.activity.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 活动管理员信息表
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("activity_item_manage_list")

public class ActivityItemManageList extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    private Integer activityItemId;

    /**
     * 用户number
     */
    private String managerNumber;

    /**
     * 
     */
    private String managerType;

    /**
     * 邀请的用户
     */
    private String inviterNumber;

    /**
     * 
     */
    private String inviterType;

    /**
     * 是否可查看活动信息
     */
    private Integer isRead;

    /**
     * 是否可修改活动信息
     */
    private Integer isModify;

    /**
     * 是否可核销活动人员
     */
    private Integer isExamine;

    /**
     * 是否可邀请其他用户成为管理
     */
    private Integer isInvite;

    /**
     * 邀请时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date inviteTime;

}