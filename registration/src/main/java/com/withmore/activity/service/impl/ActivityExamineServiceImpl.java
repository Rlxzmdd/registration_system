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
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.*;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.javaweb.system.entity.Level;
import com.javaweb.system.query.LevelQuery;
import com.javaweb.system.vo.level.LevelInfoVo;
import com.withmore.activity.entity.ActivityExamine;
import com.withmore.activity.mapper.ActivityExamineMapper;
import com.withmore.activity.mapper.ActivityItemInfoMapper;
import com.withmore.activity.query.ActivityDataPermissionNodeQuery;
import com.withmore.activity.query.ActivityExamineListQuery;
import com.withmore.activity.query.ActivityExamineQuery;
import com.withmore.activity.service.IActivityExamineService;
import com.withmore.activity.vo.activity.ActivityClassExamineExcelVo;
import com.withmore.activity.vo.activity.ActivityExamineVo;
import com.withmore.activity.vo.activity.ActivityInfoManageVo;
import com.withmore.activity.vo.activityexamine.ActivityExamineInfoVo;
import com.withmore.activity.vo.activityexamine.ActivityExamineListVo;
import com.withmore.common.constant.Constant;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.permission.utils.PermissionConvert;
import com.withmore.user.permission.utils.PermissionJudge;
import com.withmore.user.student.vo.student.StudentDetailsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
  * <p>
  * 活动核销表 服务类实现
  * </p>
  *
  * @author Zhous
  * @since 2021-08-07
  */
@Service
public class ActivityExamineServiceImpl extends BaseServiceImpl<ActivityExamineMapper, ActivityExamine> implements IActivityExamineService {

    @Autowired
    private ActivityExamineMapper activityExamineMapper;

    @Autowired
    private ActivityItemInfoMapper activityItemInfoMapper;
    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ActivityExamineQuery activityExamineQuery = (ActivityExamineQuery) query;
        // 查询条件
        QueryWrapper<ActivityExamine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<ActivityExamine> page = new Page<>(activityExamineQuery.getPage(), activityExamineQuery.getLimit());
        IPage<ActivityExamine> pageData = activityExamineMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            ActivityExamineListVo activityExamineListVo = Convert.convert(ActivityExamineListVo.class, x);
            return activityExamineListVo;
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
        ActivityExamine entity = (ActivityExamine) super.getInfo(id);
        // 返回视图Vo
        ActivityExamineInfoVo activityExamineInfoVo = new ActivityExamineInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, activityExamineInfoVo);
        return activityExamineInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(ActivityExamine entity) {
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
    public JsonResult delete(ActivityExamine entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    @DS("registration") /*使用事务的方法必须单独指定方法查询的DataBase*/
    @Transactional
    public void insert(ActivityExamine entity){
        activityExamineMapper.insertExamine(entity);
    }

    /**
     * 添加一条核销记录
     * @param dto
     * @param activityId
     * @param examinedDto
     * @return
     */
    @Override
    public JsonResultS examine(AuthToken2CredentialDto dto, Integer activityId, AuthToken2CredentialDto examinedDto) {
        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(activityId,dto.getNumber(),dto.getType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        if (ac == null){
            return JsonResultS.error("活动不存在或权限不足");
        }
        if (ac.getIsExamine()!=1)
            return JsonResultS.error("权限不足");

        if (ac.getIsApply()==1){
            ActivityInfoManageVo examinedAc = activityItemInfoMapper.getActivity(activityId,dto.getNumber(),dto.getType(),
                    Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
            if(examinedAc.getIsSignup() != 1)
                return JsonResultS.error("当前用户未报名");
            if(examinedAc.getIsApproval() != 1)
                return JsonResultS.error("当前用户报名未通过");
        }
        ActivityExamineInfoVo ae = activityExamineMapper.selectOneExamine(activityId,examinedDto.getNumber(),examinedDto.getType(),1);
        // 已核销过 并且当前活动不允许重新核销
        if(ac.getIsRepeat() != 1)
            if(ae != null)
                return JsonResultS.error("当前用户已核销");
        ActivityExamine entity = (new ActivityExamine())
                .setActivityItemId(activityId)
                .setExaminedNumber(examinedDto.getNumber())
                .setExaminedType(examinedDto.getType())
                .setExaminerNumber(dto.getNumber())
                .setExaminerType(dto.getType())
                .setExamineTime(DateUtils.now());
        activityExamineMapper.insertExamine(entity);
        return JsonResultS.success(entity);
    }

    /**
     * 获取核销情况列表
     * @param dto
     * @param query
     * @return
     */
    @Override
    public JsonResultS getExamineList(AuthToken2CredentialDto dto, ActivityExamineListQuery query){
        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(query.getActivityId(), dto.getNumber(), dto.getType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        if (ac == null) {
            return JsonResultS.error("活动不存在或权限不足");
        }
        if (ac.getIsManager() == 0 && ac.getIsOwner() == 0)
            return JsonResultS.error("权限不足");
        List<ActivityExamineVo> result = activityExamineMapper.getExamineList(query.getActivityId(),1);
        return JsonResultS.success(result);
    }


    /**
     * 获取核销情况列表
     * @param dto
     * @param query
     * @return
     */
    @Override
    public JsonResultS getExamineListSelf(AuthToken2CredentialDto dto, BaseQuery query){
        // 获取数据列表
        Page<ActivityExamineVo> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ActivityExamineVo> pageData = activityExamineMapper.getExamineListSelf(
                page,
                dto.getNumber(), dto.getType(),1);
        return JsonResultS.success(pageData);
    }

    @Override
    public JsonResultS exportExamineExcelFilter(AuthToken2CredentialDto dto, ActivityDataPermissionNodeQuery query, List<PermissionNode> nodes) {
        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(query.getActivityId(),dto.getNumber(),dto.getType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        if (ac == null){
            return JsonResultS.error("活动不存在或权限不足");
        }
        if (ac.getIsRead()!=1)
            return JsonResultS.error("权限不足");
        List<ActivityClassExamineExcelVo> vo = activityExamineMapper.exportExamineByNode(query.getActivityId(), nodes);
        return JsonResultS.success(vo);
    }
    @Override
    public JsonResultS queryExamineExcelFilter(AuthToken2CredentialDto dto, ActivityDataPermissionNodeQuery query, List<PermissionNode> nodes) {
        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(query.getActivityId(),dto.getNumber(),dto.getType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        if (ac == null){
            return JsonResultS.error("活动不存在或权限不足");
        }
        if (ac.getIsRead()!=1)
            return JsonResultS.error("权限不足");
        Page<ActivityClassExamineExcelVo> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ActivityClassExamineExcelVo> pageData = activityExamineMapper.queryExamineByNode(page, query.getActivityId(), nodes);
        return JsonResultS.success(pageData);
    }
}