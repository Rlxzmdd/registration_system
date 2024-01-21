// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.activity.vo.activityitemprofile;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 活动正文表列表Vo
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@Data
public class ActivityItemProfileListVo {

    /**
    * 活动正文表ID
    */
    private Integer id;

    /**
     * 
     */
    private Integer activityItemId;

    /**
     * 富文本的活动介绍
     */
    private String richText;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 
     */
    private Integer createUser;

    /**
     * 
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