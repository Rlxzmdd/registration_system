package com.withmore.event.todo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.javaweb.system.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("event_todo_notice_img")
public class TodoNoticeImg extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /*图片资源UUID*/
    private String uuid;

    /*通知ID*/
    private Integer noticeId;
}
