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
import com.javaweb.system.entity.Level;
import com.javaweb.system.mapper.LevelMapper;
import com.javaweb.system.query.LevelQuery;
import com.javaweb.system.service.ILevelService;
import com.javaweb.system.vo.level.LevelInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 职级表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-02
 */
@Service
public class LevelServiceImpl extends BaseServiceImpl<LevelMapper, Level> implements ILevelService {

    @Autowired
    private LevelMapper levelMapper;

    /**
     * 获取职级列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        LevelQuery levelQuery = (LevelQuery) query;
        // 查询条件
        QueryWrapper<Level> queryWrapper = new QueryWrapper<>();
        // 职级名称
        if (!StringUtils.isEmpty(levelQuery.getName())) {
            queryWrapper.like("name", levelQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        IPage<Level> page = new Page<>(levelQuery.getPage(), levelQuery.getLimit());
        IPage<Level> pageData = levelMapper.selectPage(page, queryWrapper);
        return JsonResult.success(pageData);
    }

    /**
     * 获取职级列表
     *
     */
    @Override
    public JsonResult getLevelList() {
        QueryWrapper<Level> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");
        List<Level> levelList = list(queryWrapper);
        return JsonResult.success(levelList);
    }

    /**
     * 导出Excel
     *
     * @param levelQuery 查询条件
     */
    @Override
    public List<LevelInfoVo> exportExcel(LevelQuery levelQuery) {
        // 查询条件
        QueryWrapper<Level> queryWrapper = new QueryWrapper<>();
        // 职级名称
        if (!StringUtils.isEmpty(levelQuery.getName())) {
            queryWrapper.like("name", levelQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        List<Level> levelList = levelMapper.selectList(queryWrapper);
        List<LevelInfoVo> levelInfoVoList = new ArrayList<>();
        if (!levelList.isEmpty()) {
            levelList.forEach(item -> {
                LevelInfoVo levelInfoVo = new LevelInfoVo();
                BeanUtils.copyProperties(item, levelInfoVo);
                levelInfoVoList.add(levelInfoVo);
            });
        }
        return levelInfoVoList;
    }
}
