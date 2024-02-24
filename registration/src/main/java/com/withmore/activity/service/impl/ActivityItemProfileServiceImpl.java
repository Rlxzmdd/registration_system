// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.activity.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.activity.entity.ActivityItemProfile;
import com.withmore.activity.mapper.ActivityItemProfileMapper;
import com.withmore.activity.query.ActivityItemProfileQuery;
import com.withmore.activity.service.IActivityItemProfileService;
import com.withmore.activity.vo.activityitemprofile.ActivityItemProfileInfoVo;
import com.withmore.activity.vo.activityitemprofile.ActivityItemProfileListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
  * <p>
  * 活动正文表 服务类实现
  * </p>
  *
  * @author Zhous
  * @since 2021-08-07
  */
@Service
public class ActivityItemProfileServiceImpl extends BaseServiceImpl<ActivityItemProfileMapper, ActivityItemProfile> implements IActivityItemProfileService {

    @Autowired
    private ActivityItemProfileMapper activityItemProfileMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ActivityItemProfileQuery activityItemProfileQuery = (ActivityItemProfileQuery) query;
        // 查询条件
        QueryWrapper<ActivityItemProfile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<ActivityItemProfile> page = new Page<>(activityItemProfileQuery.getPage(), activityItemProfileQuery.getLimit());
        IPage<ActivityItemProfile> pageData = activityItemProfileMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            ActivityItemProfileListVo activityItemProfileListVo = Convert.convert(ActivityItemProfileListVo.class, x);
            return activityItemProfileListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取详情Vo
     *
     * @param id 记录ID
     */
    @Override
    public Object getInfo(Serializable id) {
        ActivityItemProfile entity = (ActivityItemProfile) super.getInfo(id);
        // 返回视图Vo
        ActivityItemProfileInfoVo activityItemProfileInfoVo = new ActivityItemProfileInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, activityItemProfileInfoVo);
        return activityItemProfileInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     */
    @Override
    public JsonResult edit(ActivityItemProfile entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateTime(DateUtils.now());
        } else {
            entity.setCreateTime(DateUtils.now());
        }
        return super.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     */
    @Override
    public JsonResult delete(ActivityItemProfile entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

}