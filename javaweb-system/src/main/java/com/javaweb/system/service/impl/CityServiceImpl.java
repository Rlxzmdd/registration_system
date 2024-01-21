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
import com.javaweb.system.entity.City;
import com.javaweb.system.mapper.CityMapper;
import com.javaweb.system.query.CityQuery;
import com.javaweb.system.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 高德城市表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-03
 */
@Service
public class CityServiceImpl extends BaseServiceImpl<CityMapper, City> implements ICityService {

    @Autowired
    private CityMapper cityMapper;

    /**
     * 获取城市列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        CityQuery cityQuery = (CityQuery) query;
        // 查询条件
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        // 父级ID
        if (StringUtils.isNull(cityQuery.getPid())) {
            queryWrapper.eq("pid", 0);
        } else {
            queryWrapper.eq("pid", cityQuery.getPid());
        }
        // 城市名称
        if (!StringUtils.isEmpty(cityQuery.getName())) {
            queryWrapper.like("name", cityQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询分页数据
        List<City> cityList = cityMapper.selectList(queryWrapper);
        cityList.forEach(item -> {
            if (item.getLevel() <= 2) {
                item.setHasChildren(true);
            }
        });
        return JsonResult.success(cityList);
    }
}
