package com.withmore.activity.vo.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javaweb.common.annotation.Excel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


//用于传递 某天以小时为单位的扫码量
@Data
public class ActivityExamineHourVo {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date day;

    private Integer hour;

    private Integer count;

}
