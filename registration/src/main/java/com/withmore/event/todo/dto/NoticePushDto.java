package com.withmore.event.todo.dto;

import com.withmore.event.todo.entity.TodoNotice;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
public class NoticePushDto extends TodoNotice {
    /*图片UUID*/
    private List<String> images = new ArrayList<>();
    /*权限节点ID*/
    private List<Integer> permissionId = new ArrayList<>();
}
