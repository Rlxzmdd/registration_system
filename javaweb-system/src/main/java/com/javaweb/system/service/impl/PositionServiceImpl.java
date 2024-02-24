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
import com.javaweb.system.entity.Position;
import com.javaweb.system.mapper.PositionMapper;
import com.javaweb.system.query.PositionQuery;
import com.javaweb.system.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 岗位表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-02
 */
@Service
public class PositionServiceImpl extends BaseServiceImpl<PositionMapper, Position> implements IPositionService {

    @Autowired
    private PositionMapper positionMapper;

    /**
     * 获取岗位列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        PositionQuery positionQuery = (PositionQuery) query;
        // 查询条件
        QueryWrapper<Position> queryWrapper = new QueryWrapper<>();
        // 岗位名称
        if (!StringUtils.isEmpty(positionQuery.getName())) {
            queryWrapper.like("name", positionQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        IPage<Position> page = new Page<>(positionQuery.getPage(), positionQuery.getLimit());
        IPage<Position> pageData = positionMapper.selectPage(page, queryWrapper);
        return JsonResult.success(pageData);
    }

    /**
     * 获取岗位列表
     *
     */
    @Override
    public JsonResult getPositionList() {
        QueryWrapper<Position> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");
        List<Position> positionList = list(queryWrapper);
        return JsonResult.success(positionList);
    }
}
