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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.entity.OperLog;
import com.javaweb.system.mapper.OperLogMapper;
import com.javaweb.system.query.OperLogQuery;
import com.javaweb.system.service.IOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-31
 */
@Service
public class OperLogServiceImpl extends BaseServiceImpl<OperLogMapper, OperLog> implements IOperLogService {

    @Autowired
    private OperLogMapper operLogMapper;

    /**
     * 获取操作日志列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        OperLogQuery operLogQuery = (OperLogQuery) query;
        // 查询条件
        QueryWrapper<OperLog> queryWrapper = new QueryWrapper<>();
        // 操作用户
        if (!StringUtils.isEmpty(operLogQuery.getOperName())) {
            queryWrapper.like("oper_name", operLogQuery.getOperName());
        }
        // 模块名称
        if (!StringUtils.isEmpty(operLogQuery.getTitle())) {
            queryWrapper.like("title", operLogQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 查询分页数据
        IPage<OperLog> page = new Page<>(operLogQuery.getPage(), operLogQuery.getLimit());
        IPage<OperLog> pageData = operLogMapper.selectPage(page, queryWrapper);
        return JsonResult.success(pageData);
    }

    /**
     * 创建系统操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(OperLog operLog) {
        operLogMapper.insertOperlog(operLog);
    }

}
