package com.withmore.event.todo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.withmore.event.todo.entity.FormPermission;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 待办事项-表单权限 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@DS("registration")
@Mapper
public interface FormPermissionMapper extends BaseMapper<FormPermission> {

}
