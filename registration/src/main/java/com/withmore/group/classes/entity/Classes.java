// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.classes.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * <p>
 * 班级信息表
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("entity_classes")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class) // 加入Jackson 序列化与反序列化风格
public class Classes extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 所属学院索引
     */
    private Integer collegeId;

    /**
     * 所属专业索引
     */
    private Integer majorId;

    /**
     * 班主任工号
     */
    private String teacherNumber;

    /**
     * 辅导员工号
     */
    private String counselorNumber;

    /**
     * 助辅学号
     */
    private String assistantNumber;

}