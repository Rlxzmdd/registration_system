// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.permission.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.common.utils.ResultCodeEnum;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.user.permission.dto.PermissionNodeFilterDto;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.permission.mapper.PermissionNodeMapper;
import com.withmore.user.permission.query.PermissionNodeQuery;
import com.withmore.user.permission.service.IPermissionNodeService;
import com.withmore.user.permission.utils.PermissionConvert;
import com.withmore.user.permission.vo.permissionnode.PermissionNodeInfoVo;
import com.withmore.user.permission.vo.permissionnode.PermissionNodeListVo;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.withmore.user.student.mapper.StudentPermissionMapper;
import com.withmore.user.teacher.mapper.TeacherPermissionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 权限节点表-待办事项可视权限 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-07-27
 */
@Service
public class PermissionNodeServiceImpl extends BaseServiceImpl<PermissionNodeMapper, PermissionNode> implements IPermissionNodeService {

    @Autowired
    private PermissionNodeMapper permissionNodeMapper;

    @Autowired
    private TeacherPermissionMapper teacherPermissionMapper;

    @Autowired
    private StudentPermissionMapper studentPermissionMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        PermissionNodeQuery permissionNodeQuery = (PermissionNodeQuery) query;
        // 查询条件
        QueryWrapper<PermissionNode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<PermissionNode> page = new Page<>(permissionNodeQuery.getPage(), permissionNodeQuery.getLimit());
        IPage<PermissionNode> pageData = permissionNodeMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            PermissionNodeListVo permissionNodeListVo = Convert.convert(PermissionNodeListVo.class, x);
            return permissionNodeListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取详情Vo
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        PermissionNode entity = (PermissionNode) super.getInfo(id);
        // 返回视图Vo
        PermissionNodeInfoVo permissionNodeInfoVo = new PermissionNodeInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, permissionNodeInfoVo);
        return permissionNodeInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(PermissionNode entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateTime(DateUtils.now());
            entity.setUpdateUser(1);
        } else {
            entity.setCreateTime(DateUtils.now());
            entity.setCreateUser(1);
        }
        return super.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(PermissionNode entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

    @Override
    public JsonResultS list(AuthToken2CredentialDto dto) {
        List<PermissionNode> nodes = PermissionConvert.convert2Nodes(dto);
        return JsonResultS.success(PermissionConvert.permissionNode2PermissionSimpleVo(nodes));
    }

    @Override
    public JsonResultS filter(AuthToken2CredentialDto dto) {
        List<PermissionNode> nodes = PermissionConvert.convert2Nodes(dto);
        if (nodes.size() == 0) {
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0312);
        }
        List<PermissionNodeFilterDto> rows = permissionNodeMapper.getPermissionNodeFilter(nodes);
        Map<String, Map<String, List<String>>> dict = PermissionConvert.permissionNode2FilterDict(rows, nodes);
        return JsonResultS.success(dict);
    }
}