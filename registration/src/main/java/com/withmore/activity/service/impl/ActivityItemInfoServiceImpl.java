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
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.*;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.activity.constant.ActivityItemInfoConstant;
import com.withmore.activity.entity.ActivityItemInfo;
import com.withmore.activity.entity.ActivityItemManageList;
import com.withmore.activity.mapper.ActivityItemInfoMapper;
import com.withmore.activity.mapper.ActivityItemManageListMapper;
import com.withmore.activity.query.ActivityItemInfoQuery;
import com.withmore.activity.query.ActivityManageListQuery;
import com.withmore.activity.service.IActivityItemInfoService;
import com.javaweb.system.utils.ShiroUtils;
import com.withmore.activity.vo.activity.ActivityInfoManageVo;
import com.withmore.activity.vo.activity.ActivityInfoVo;
import com.withmore.activity.vo.activityiteminfo.ActivityItemInfoInfoVo;
import com.withmore.activity.vo.activityiteminfo.ActivityItemInfoListVo;
import com.withmore.common.constant.Constant;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.event.todo.entity.TodoNotice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
  * <p>
  * 活动信息表 服务类实现
  * </p>
  *
  * @author Zhous
  * @since 2021-08-07
  */
@Service
public class ActivityItemInfoServiceImpl extends BaseServiceImpl<ActivityItemInfoMapper, ActivityItemInfo> implements IActivityItemInfoService {

    @Autowired
    private ActivityItemInfoMapper activityItemInfoMapper;

    @Autowired
    private ActivityItemManageListMapper activityItemManageListMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ActivityItemInfoQuery activityItemInfoQuery = (ActivityItemInfoQuery) query;
        // 查询条件
        QueryWrapper<ActivityItemInfo> queryWrapper = new QueryWrapper<>();
        // 活动标题
        if (!StringUtils.isEmpty(activityItemInfoQuery.getTitle())) {
            queryWrapper.like("title", activityItemInfoQuery.getTitle());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        Page<ActivityItemInfo> page = new Page<>(activityItemInfoQuery.getPage(), activityItemInfoQuery.getLimit());
        IPage<ActivityItemInfo> pageData = activityItemInfoMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            ActivityItemInfoListVo activityItemInfoListVo = Convert.convert(ActivityItemInfoListVo.class, x);
            return activityItemInfoListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取跟用户有关的活动列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResultS getActivityList(AuthToken2CredentialDto dto, BaseQuery query) {
        // 获取数据列表
        Page<ActivityInfoManageVo> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ActivityInfoManageVo> pageData = activityItemInfoMapper.
                getActivityList(
                        page,
                        dto.getNumber(),
                        dto.getType(),
                        Constant.TOKEN_USER_TYPE_STUDENT,
                        Constant.TOKEN_USER_TYPE_TEACHER);
//        pageData.convert(x -> {
//            ActivityInfoVo activityInfoVo = Convert.convert(ActivityInfoVo.class, x);
//            return activityInfoVo;
//        });
        return JsonResultS.success(pageData);
    }

    /**
     * 获取详情Vo
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        ActivityItemInfo entity = (ActivityItemInfo) super.getInfo(id);
        // 返回视图Vo
        ActivityItemInfoInfoVo activityItemInfoInfoVo = new ActivityItemInfoInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, activityItemInfoInfoVo);
        return activityItemInfoInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(ActivityItemInfo entity) {
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
     * @return
     */
    @Override
    public JsonResult delete(ActivityItemInfo entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }


    public JsonResultS delActivityById(AuthToken2CredentialDto dto, Integer id){
        if (StringUtils.isEmpty(id)) {
            return JsonResultS.error("ID不能为空");
        }
        // 设置Mark=0
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("mark", 0);
        updateWrapper.eq("id", id);
        updateWrapper.eq("creator_number", dto.getNumber());
        updateWrapper.eq("creator_type", dto.getType());
        System.out.println(updateWrapper);
        boolean result = update(updateWrapper);
        if (!result) {
            return JsonResultS.error("没有权限删除此活动");
        }
        return JsonResultS.success();
    }
    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    public JsonResultS push(AuthToken2CredentialDto dto, ActivityItemInfo entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            // 更新
            entity.setUpdateTime(DateUtils.now());
            if(!isOwner(entity.getId(),dto.getNumber(),dto.getType()))
                return JsonResultS.error("","活动不存在，或者活动所有者非当前用户");
            int result = activityItemInfoMapper.updateById(entity);
            return JsonResultS.success("","成功修改活动信息");
        } else {
            // 创建
           //entity.setCreateTime(DateUtils.now());
            entity.setCreatorNumber(dto.getNumber()).setCreatorType(dto.getType());
            int result = activityItemInfoMapper.insert(entity);
            ActivityItemManageList aMange = (new ActivityItemManageList())
                    .setActivityItemId(entity.getId())
                    .setManagerNumber(entity.getCreatorNumber())
                    .setManagerType(entity.getCreatorType())
                    .setInviterNumber(dto.getNumber())
                    .setInviterType(dto.getType())
                    .setIsRead(1)
                    .setIsModify(1)
                    .setIsExamine(1)
                    .setIsInvite(1);
            activityItemManageListMapper.insert(aMange);
            return JsonResultS.success();
        }
    }

    public JsonResultS getDetail(AuthToken2CredentialDto dto, Integer activityId){
        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(activityId,dto.getNumber(),dto.getType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);

        return JsonResultS.success(ac);
    }


    private boolean isOwner(Integer id,String number,String type){
        QueryWrapper<ActivityItemInfo> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq("id", id);
        QueryWrapper.eq("creator_number", number);
        QueryWrapper.eq("creator_type", type);
        QueryWrapper.eq("mark", 1);
        return activityItemInfoMapper.selectOne(QueryWrapper) != null;
    }

}