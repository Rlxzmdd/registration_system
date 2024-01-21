package com.withmore.event.todo.vo.todonotice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class TodoNoticeDetailsVo {
    private Integer id;
    /*标题*/
    private String title;
    /*内容*/
    private String content;
    /*发布人*/
    private String publisher;

    /*发布时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "GMT+8")
    private Date releaseTime;

    /*图片OSS链接*/
    private List<String> images;
}
