// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.classes.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.withmore.group.classes.dto.DictDto;
import com.withmore.group.classes.entity.Classes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.withmore.group.classes.vo.classes.ClassesContactVo;
import com.withmore.group.classes.vo.classes.ClassesSimpleVo;
import com.withmore.user.permission.entity.PermissionNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 班级信息表 Mapper 接口
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Mapper
@DS("registration")
public interface ClassesMapper extends BaseMapper<Classes> {
    /**
     * 获取班级简略信息列表
     *
     * @param objectPage 分页对象
     * @param nodes      权限节点
     * @return
     */
    IPage<ClassesSimpleVo> list(Page<ClassesSimpleVo> objectPage, @Param("nodes") List<PermissionNode> nodes);

    /**
     * 获取班级简略信息
     *
     * @param classesId 班级ID
     * @param nodes     权限节点
     * @return
     */
    ClassesSimpleVo simple(@Param("classesId") Integer classesId, @Param("nodes") List<PermissionNode> nodes);

    /**
     * 获取班级负责人联系方式
     *
     * @param id 班级ID
     * @return
     */
    List<ClassesContactVo> contact(@Param("id") String id);

    /**
     * 获取所有班级列表
     * @return
     */
    List<DictDto> dict();
}
