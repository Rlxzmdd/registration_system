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
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.activity.entity.ActivityItemManageList;
import com.withmore.activity.mapper.ActivityItemInfoMapper;
import com.withmore.activity.mapper.ActivityItemManageListMapper;
import com.withmore.activity.query.ActivityItemManageListQuery;
import com.withmore.activity.query.ActivityManageAddQuery;
import com.withmore.activity.query.ActivityManageListQuery;
import com.withmore.activity.service.IActivityItemManageListService;
import com.withmore.activity.vo.activity.ActivityInfoManageVo;
import com.withmore.activity.vo.activity.ActivityManageVo;
import com.withmore.activity.vo.activityitemmanagelist.ActivityItemManageListInfoVo;
import com.withmore.activity.vo.activityitemmanagelist.ActivityItemManageListListVo;
import com.withmore.common.constant.Constant;
import com.withmore.common.dto.AuthToken2CredentialDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 活动管理员信息表 服务类实现
 * </p>
 *
 * @author Zhous
 * @since 2021-08-07
 */
@Service
public class ActivityItemManageListServiceImpl extends BaseServiceImpl<ActivityItemManageListMapper, ActivityItemManageList> implements IActivityItemManageListService {

    @Autowired
    private ActivityItemManageListMapper activityItemManageListMapper;

    @Autowired
    private ActivityItemInfoMapper activityItemInfoMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ActivityItemManageListQuery activityItemManageListQuery = (ActivityItemManageListQuery) query;
        // 查询条件
        QueryWrapper<ActivityItemManageList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<ActivityItemManageList> page = new Page<>(activityItemManageListQuery.getPage(), activityItemManageListQuery.getLimit());
        IPage<ActivityItemManageList> pageData = activityItemManageListMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            ActivityItemManageListListVo activityItemManageListListVo = Convert.convert(ActivityItemManageListListVo.class, x);
            return activityItemManageListListVo;
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
        ActivityItemManageList entity = (ActivityItemManageList) super.getInfo(id);
        // 返回视图Vo
        ActivityItemManageListInfoVo activityItemManageListInfoVo = new ActivityItemManageListInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, activityItemManageListInfoVo);
        return activityItemManageListInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     */
    @Override
    public JsonResult edit(ActivityItemManageList entity) {
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
     */
    @Override
    public JsonResult delete(ActivityItemManageList entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

    @Override
    public JsonResultS getManageListSelf(AuthToken2CredentialDto dto, ActivityManageListQuery query) {
        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(query.getActivityId(), dto.getNumber(), dto.getType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        if (ac == null) {
            return JsonResultS.error("活动不存在");
        }
        if (ac.getIsManager() == 0 && ac.getIsOwner() == 0)
            return JsonResultS.error("无权限");
        // 查询条件

        // 获取数据列表
        Page<ActivityManageVo> page = new Page<>(query.getPage(), query.getLimit());
        return JsonResultS.success(
                activityItemManageListMapper.getManagerListByActivityId(
                        page,
                        query.getActivityId(),
                        Constant.TOKEN_USER_TYPE_STUDENT,
                        Constant.TOKEN_USER_TYPE_TEACHER
                ));
    }

    @Override
    public JsonResultS addManager(AuthToken2CredentialDto dto, ActivityManageAddQuery query) {
        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(query.getActivityId(), dto.getNumber(), dto.getType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        if (ac == null) {
            return JsonResultS.error("活动不存在");
        }
        if (ac.getIsInvite() == 0)
            return JsonResultS.error("无权限");
        // 查询条件
        ActivityInfoManageVo addAc = activityItemInfoMapper.getActivity(query.getActivityId(), query.getUserNumber(), query.getUserType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        if(addAc != null)
            if (addAc.getIsOwner() == 1 || addAc.getIsManager() == 1)
                return JsonResultS.error("用户已经是活动管理员");
        if (query.getIsRead() > ac.getIsRead() ||
                query.getIsModify() > ac.getIsModify() ||
                query.getIsInvite() > ac.getIsInvite() ||
                query.getIsExamine() > ac.getIsExamine())
            return JsonResultS.error("单项权限超出当前用户可以添加的权限");
        ActivityItemManageList aMange = (new ActivityItemManageList())
                .setActivityItemId(query.getActivityId())
                .setManagerNumber(query.getUserNumber())
                .setManagerType(query.getUserType())
                .setInviterNumber(dto.getNumber())
                .setInviterType(dto.getType())
                .setIsRead(query.getIsRead())
                .setIsModify(query.getIsModify())
                .setIsExamine(query.getIsExamine())
                .setIsInvite(query.getIsInvite());
        activityItemManageListMapper.insert(aMange);
        return JsonResultS.success();
    }

    @Override
    public JsonResultS delManager(AuthToken2CredentialDto dto, ActivityManageAddQuery query) {
        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(query.getActivityId(), dto.getNumber(), dto.getType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        System.out.println(query);
        System.out.println("asd");
        if (ac == null) {
            return JsonResultS.error("活动不存在");
        }
        if (ac.getIsInvite() == 0)
            return JsonResultS.error("无权限");
        QueryWrapper<ActivityItemManageList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activity_item_id", query.getActivityId());
        queryWrapper.eq("manager_number", query.getUserNumber());
        queryWrapper.eq("manager_type", query.getUserType());
        queryWrapper.eq("mark", 1);

        ActivityItemManageList aManger = activityItemManageListMapper.selectOne(queryWrapper);
        if(aManger == null)
            return JsonResultS.error("管理员不存在");
        aManger.setMark(0);
        activityItemManageListMapper.updateById(aManger);
        return JsonResultS.success();
    }
}