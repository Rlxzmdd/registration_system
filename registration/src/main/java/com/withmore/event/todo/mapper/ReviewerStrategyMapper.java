// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.todo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.withmore.event.todo.entity.ReviewerStrategy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 表单审核策略表 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-08-25
 */
@DS("registration")
@Mapper
public interface ReviewerStrategyMapper extends BaseMapper<ReviewerStrategy> {

}
