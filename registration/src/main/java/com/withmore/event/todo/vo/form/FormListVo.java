// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.todo.vo.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>
 * 表单模版 列表Vo
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Data
public class FormListVo {

    /**
    * 待办事项-填报事项ID
    */
    private Integer id;

    /**
     * 事项标题
     */
    private String title;

    /**
     * 发起人学号/工号
     */
    private String initiatorNumber;

    private String initiatorType;

    /**
     * 接收者权限
     */
    private String permissions;

    /**
     * 表单类型key
     */
    private String formKey;

    /**
     * 下一步审核人，NULL为无需审核
     */
    private String nextReviewer;

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