package com.withmore.event.form.constant;

import java.util.Date;

/**
 * 表单可计算`当前所处`状态声明
 * 声明接口需拥有 startTime , endTime 属性
 * 并实现两个get方法，可为lombok自动生成
 */
public interface FormAvailableStatus {
    Date getStartTime();

    Date getEndTime();
}
