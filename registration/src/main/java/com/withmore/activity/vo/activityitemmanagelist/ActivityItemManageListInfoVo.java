// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.activity.vo.activityitemmanagelist;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 活动管理员信息表表单Vo
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@Data
public class ActivityItemManageListInfoVo {

    /**
     * 活动管理员信息表ID
     */
    private Integer id;

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

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 
     */
    private Integer createUser;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 
     */
    private Integer updateUser;

    /**
     * 是否有效
     */
    private Integer mark;

}