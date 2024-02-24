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
import com.javaweb.system.entity.Config;
import com.javaweb.system.mapper.ConfigMapper;
import com.javaweb.system.query.ConfigQuery;
import com.javaweb.system.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 配置表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-06
 */
@Service
public class ConfigServiceImpl extends BaseServiceImpl<ConfigMapper, Config> implements IConfigService {

    @Autowired
    private ConfigMapper configMapper;

    /**
     * 获取配置列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ConfigQuery configQuery = (ConfigQuery) query;
        // 查询条件
        QueryWrapper<Config> queryWrapper = new QueryWrapper<>();
        // 配置名称
        if (!StringUtils.isEmpty(configQuery.getName())) {
            queryWrapper.like("name", configQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        IPage<Config> page = new Page<>(configQuery.getPage(), configQuery.getLimit());
        IPage<Config> pageData = configMapper.selectPage(page, queryWrapper);
        return JsonResult.success(pageData);
    }
}
