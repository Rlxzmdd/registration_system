// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.form.vo.reviewerstrategy;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 表单审核策略表表单Vo
 * </p>
 *
 * @author Cheney
 * @since 2021-08-25
 */
@Data
public class ReviewerStrategyInfoVo {

    /**
     * 表单审核策略表ID
     */
    private Integer id;

    /**
     * 表单Key
     */
    private String formKey;

    /**
     * 策略规则
     */
    private String strategy;

    /**
     * 规则对应的审核流id
     */
    private Integer reviewerId;

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
     * 创建用户
     */
    private Integer createUser;

    /**
     * 更新用户
     */
    private Integer updateUser;

    /**
     * 记录有效性
     */
    private Integer mark;

}