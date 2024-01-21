// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.wechat.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.config.CommonConfig;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.entity.Role;
import com.javaweb.system.mapper.RoleMapper;
import com.withmore.user.wechat.constant.RouteConstant;
import com.withmore.user.wechat.entity.Route;
import com.withmore.user.wechat.mapper.RouteMapper;
import com.withmore.user.wechat.query.RoleRouteQuery;
import com.withmore.user.wechat.query.RouteQuery;
import com.withmore.user.wechat.service.IRouteService;
import com.javaweb.system.utils.ShiroUtils;
import com.withmore.user.wechat.vo.route.RouteInfoVo;
import com.withmore.user.wechat.vo.route.RouteListVo;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.withmore.user.wechat.vo.wechat.RoleRouteVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 微信小程序路由表 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-08-28
 */
@Service
public class RouteServiceImpl extends BaseServiceImpl<RouteMapper, Route> implements IRouteService {

    @Autowired
    private RouteMapper routeMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        RouteQuery routeQuery = (RouteQuery) query;
        // 查询条件
        QueryWrapper<Route> queryWrapper = new QueryWrapper<>();
        // 页面类型
        if (!StringUtils.isEmpty(routeQuery.getType())) {
            queryWrapper.eq("type", routeQuery.getType());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<Route> page = new Page<>(routeQuery.getPage(), routeQuery.getLimit());
        IPage<Route> pageData = routeMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            RouteListVo routeListVo = Convert.convert(RouteListVo.class, x);
            return routeListVo;
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
        Route entity = (Route) super.getInfo(id);
        // 返回视图Vo
        RouteInfoVo routeInfoVo = new RouteInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, routeInfoVo);
        return routeInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Route entity) {
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
    public JsonResult delete(Route entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

    @Override
    public JsonResultS queryRoleRoute(RoleRouteQuery query) {
        // 获取管理员ID
        QueryWrapper<Role> adminWrapper = new QueryWrapper<>();
        adminWrapper.eq("code","admin");
        adminWrapper.eq("mark" ,1 );
        Role admin = roleMapper.selectOne(adminWrapper);

        QueryWrapper<Route> wrapper = new QueryWrapper<>();
        wrapper.select("distinct route_name ,url_name , type , icon");
        // 如果是管理员，获取全部路由
        if (!query.getRole().contains(admin.getId())){
            wrapper.in("role_id", query.getRole());
        }
        wrapper.eq("mark", 1);
        List<Route> routes = routeMapper.selectList(wrapper);
        List<RoleRouteVo> routeVos = new ArrayList<>();
        routes.forEach(x -> {
            routeVos.add(new RoleRouteVo()
                    .setRouteName(x.getRouteName())
                    .setUrlName(x.getUrlName())
                    .setType(x.getType())
                    .setIcon(x.getIcon()));
        });
        return JsonResultS.success(routeVos);
    }
}