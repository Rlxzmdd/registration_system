// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.entity.Dept;
import com.javaweb.system.mapper.DeptMapper;
import com.javaweb.system.query.DeptQuery;
import com.javaweb.system.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-03
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 获取部门列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        DeptQuery deptQuery = (DeptQuery) query;
        // 查询条件
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        // 部门名称
        if (!StringUtils.isEmpty(deptQuery.getName())) {
            queryWrapper.like("name", deptQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        List<Dept> deptList = deptMapper.selectList(queryWrapper);
        return JsonResult.success(deptList);
    }

    /**
     * 获取部门列表
     *
     */
    @Override
    public JsonResult getDeptList() {
        // 查询条件
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        List<Dept> deptList = deptMapper.selectList(queryWrapper);
        return JsonResult.success(deptList);
    }
}
