package com.withmore.event.todo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.withmore.event.todo.entity.TodoNoticeImg;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 待办事项-通知图片 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@DS("registration")
@Mapper
public interface TodoNoticeImgMapper extends BaseMapper<TodoNoticeImg> {

}
