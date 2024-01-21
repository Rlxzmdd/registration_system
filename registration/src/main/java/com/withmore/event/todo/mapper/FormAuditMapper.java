package com.withmore.event.todo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.withmore.event.todo.entity.FormAudit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 待办事项-表单审核状态表 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Mapper
@DS("registration")
public interface FormAuditMapper extends BaseMapper<FormAudit> {
    FormAudit getLastFormAudit(@Param("uuid") String uuid);
}
