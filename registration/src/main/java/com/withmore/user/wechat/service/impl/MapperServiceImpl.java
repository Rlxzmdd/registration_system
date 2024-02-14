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
import com.withmore.user.wechat.constant.MapperConstant;
import com.withmore.user.wechat.entity.Mapper;
import com.withmore.user.wechat.mapper.MapperMapper;
import com.withmore.user.wechat.query.MapperQuery;
import com.withmore.user.wechat.service.IMapperService;
import com.javaweb.system.utils.ShiroUtils;
import com.withmore.user.wechat.vo.mapper.MapperInfoVo;
import com.withmore.user.wechat.vo.mapper.MapperListVo;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 小程序kv映射表 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-08-24
 */
@Service
public class MapperServiceImpl extends BaseServiceImpl<MapperMapper, Mapper> implements IMapperService {

    @Autowired
    private MapperMapper mapperMapper;

    /**
     * 获取数据列表
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        MapperQuery mapperQuery = (MapperQuery) query;
        // 查询条件
        QueryWrapper<Mapper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<Mapper> page = new Page<>(mapperQuery.getPage(), mapperQuery.getLimit());
        IPage<Mapper> pageData = mapperMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            MapperListVo mapperListVo = Convert.convert(MapperListVo.class, x);
            return mapperListVo;
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
        Mapper entity = (Mapper) super.getInfo(id);
        // 返回视图Vo
        MapperInfoVo mapperInfoVo = new MapperInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, mapperInfoVo);
        return mapperInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Mapper entity) {
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
    public JsonResult delete(Mapper entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

    @Override
    public JsonResultS simple() {
        QueryWrapper<Mapper> wrapper = new QueryWrapper<>();
        wrapper.eq("mark", 1);
        List<Mapper> list = mapperMapper.selectList(wrapper);
        Map<String, String> result = new HashMap<>() {{
            for (Mapper mapper :
                    list) {
                put(mapper.getMapperKey(), mapper.getMapperValue());
            }
        }};
        return JsonResultS.success(result);
    }
}