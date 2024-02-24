package com.withmore.event.form.constan;

import lombok.Getter;

@Getter
public enum FormCurrentStatus {
    /*表单状态 - 暂未未开始表单填写*/
    TODO_FORM_WAIT("wait"),
    /*表单状态 - 正在表单收集时期*/
    TODO_FORM_PROCESS("process"),
    /*表单状态 - 结束收集*/
    TODO_FORM_END("end"),
    /*我的填写状态 - 完成填写*/
    TODO_FORM_FINISH("finish"),
    /*我的填写状态 - 未填写*/
    TODO_FORM_UNDONE("undone");

    String flag;

    FormCurrentStatus(String flag) {
        this.flag = flag;
    }

}
