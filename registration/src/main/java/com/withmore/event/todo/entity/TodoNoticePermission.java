package com.withmore.event.todo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.javaweb.system.common.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("event_todo_notice_permission")
public class TodoNoticePermission extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /*通知权限节点ID*/
    private Integer permissionId;

    /*通知ID*/
    private Integer noticeId;
}
