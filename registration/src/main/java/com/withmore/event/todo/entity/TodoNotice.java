// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.todo.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 待办事项-通知信息表
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("event_todo_notice")
public class TodoNotice extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
    // private Integer isShow;
    private Boolean isShow;

    /**
     * 发起者工号/学号
     */
    private String initiatorNumber;

    /**
     * 发起者用户类型
     */
    private String initiatorType;

}