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
import com.javaweb.system.entity.ConfigData;
import com.javaweb.system.mapper.ConfigDataMapper;
import com.javaweb.system.query.ConfigDataQuery;
import com.javaweb.system.service.IConfigDataService;
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
public class ConfigDataServiceImpl extends BaseServiceImpl<ConfigDataMapper, ConfigData> implements IConfigDataService {

    @Autowired
    private ConfigDataMapper configDataMapper;

    /**
     * 获取配置列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ConfigDataQuery configQuery = (ConfigDataQuery) query;
        // 查询条件
        QueryWrapper<ConfigData> queryWrapper = new QueryWrapper<>();
        // 配置ID
        if (StringUtils.isNotNull(configQuery.getConfigId())) {
            queryWrapper.eq("config_id", configQuery.getConfigId());
        }
        // 配置标题
        if (!StringUtils.isEmpty(configQuery.getTitle())) {
            queryWrapper.like("title", configQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        IPage<ConfigData> page = new Page<>(configQuery.getPage(), configQuery.getLimit());
        IPage<ConfigData> pageData = configDataMapper.selectPage(page, queryWrapper);
        return JsonResult.success(pageData);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult setStatus(ConfigData entity) {
        ConfigData config = new ConfigData();
        config.setId(entity.getId());
        config.setStatus(entity.getStatus());
        return super.setStatus(config);
    }

}
