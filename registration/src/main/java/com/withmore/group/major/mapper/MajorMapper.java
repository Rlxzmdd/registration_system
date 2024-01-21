// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.major.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.withmore.group.major.entity.Major;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 专业信息表 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@DS("registration")
public interface MajorMapper extends BaseMapper<Major> {

}
