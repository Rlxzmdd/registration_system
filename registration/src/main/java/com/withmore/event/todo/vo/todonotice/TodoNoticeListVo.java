// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.todo.vo.todonotice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 待办事项-通知信息表列表Vo
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Data
public class TodoNoticeListVo {

    /**
    * 待办事项-通知信息表ID
    */
    private Integer id;

    /**
     * 代办事项标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 是否在客户端显示此通知
     */
    private Integer isShow;

    /**
     * 客户接收权限
     */
    private String permissions;

    /**
     * 发起者工号/学号
     */
    private String initiatorNumber;

    /**
     * 发起者用户类型
     */
    private String initiatorType;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 创建记录用户
     */
    private Integer createUser;

    /**
     * 更新记录用户
     */
    private Integer updateUser;

    /**
     * 记录有效性
     */
    private Integer mark;

}