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
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.common.utils.StringUtils;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.activity.dto.UserIdentityDto;
import com.withmore.activity.entity.ActivityExamine;
import com.withmore.activity.mapper.ActivityExamineMapper;
import com.withmore.activity.mapper.ActivityItemInfoMapper;
import com.withmore.activity.query.ActivityClassQuery;
import com.withmore.activity.query.ActivityDataPermissionNodeQuery;
import com.withmore.activity.query.ActivityExamineListQuery;
import com.withmore.activity.query.ActivityExamineQuery;
import com.withmore.activity.service.IActivityExamineService;
import com.withmore.activity.vo.activity.*;
import com.withmore.activity.vo.activityexamine.ActivityExamineInfoVo;
import com.withmore.activity.vo.activityexamine.ActivityExamineListVo;
import com.withmore.common.constant.Constant;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.student.entity.Student;
import com.withmore.user.student.mapper.StudentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
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

    @Autowired
    private StudentMapper studentMapper;


    /**
     * 获取数据列表
     *
     * @param query 查询条件
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
     */
    @Override
    public JsonResult delete(ActivityExamine entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }

    @DS("registration") /*使用事务的方法必须单独指定方法查询的DataBase*/
    @Transactional
    public void insert(ActivityExamine entity) {
        activityExamineMapper.insertExamine(entity);
    }

    /**
     * 添加一条核销记录
     */
    @Override
    public JsonResultS examine(AuthToken2CredentialDto dto, Integer activityId, AuthToken2CredentialDto examinedDto) {
        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(activityId, dto.getNumber(), dto.getType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        if (ac == null) {
            return JsonResultS.error("活动不存在或权限不足");
        }
        if (ac.getIsExamine() != 1)
            return JsonResultS.error("权限不足");

        if (ac.getIsApply() == 1) {
            ActivityInfoManageVo examinedAc = activityItemInfoMapper.getActivity(activityId, dto.getNumber(), dto.getType(),
                    Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
            if (examinedAc.getIsSignup() != 1)
                return JsonResultS.error("当前用户未报名");
            if (examinedAc.getIsApproval() != 1)
                return JsonResultS.error("当前用户报名未通过");
        }
        ActivityExamineInfoVo ae = activityExamineMapper.selectOneExamine(activityId, examinedDto.getNumber(), examinedDto.getType(), 1);
        // 已核销过 并且当前活动不允许重新核销
        if (ac.getIsRepeat() != 1)
            if (ae != null)
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
     */
    @Override
    public JsonResultS getExamineList(AuthToken2CredentialDto dto, ActivityExamineListQuery query) {
        // fixme query 分页数据未传入，通过为分页数据添加默认值解决
        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(query.getActivityId(), dto.getNumber(), dto.getType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        if (ac == null) {
            return JsonResultS.error("活动不存在或权限不足");
        }
        if (ac.getIsManager() == 0 && ac.getIsOwner() == 0)
            return JsonResultS.error("权限不足");
        if (query.getPage() == null) {
            query.setPage(1);
            query.setLimit(20);
        }
        Page<ActivityExamineVo> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ActivityExamineVo> pageData = activityExamineMapper.getExamineList(page, query.getActivityId(), 1);
        return JsonResultS.success(pageData);
    }

//    根据顺序获取之后的核销记录
    @Override
    public JsonResultS getExamineListBySerial(AuthToken2CredentialDto dto, ActivityExamineListQuery query) {
        // todo 添加权限判断
//        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(query.getActivityId(), dto.getNumber(), dto.getType(),
//                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
//        if (ac == null) {
//            return JsonResultS.error("活动不存在或权限不足");
//        }
//        if (ac.getIsManager() == 0 && ac.getIsOwner() == 0)
//            return JsonResultS.error("权限不足");

        Page<ActivityExamineVo> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ActivityExamineVo> pageData = activityExamineMapper.getExamineListBySerial(page, query.getActivityId(),query.getSerialNum(),1);
        return JsonResultS.success(pageData);
    }


    /**
     * 获取核销情况列表
     */
    @Override
    public JsonResultS getExamineListSelf(AuthToken2CredentialDto dto, BaseQuery query) {
        // 获取数据列表
        Page<ActivityExamineVo> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ActivityExamineVo> pageData = activityExamineMapper.getExamineListSelf(
                page,
                dto.getNumber(), dto.getType(), 1);
        return JsonResultS.success(pageData);
    }

    @Override
    public JsonResultS exportExamineExcelFilter(AuthToken2CredentialDto dto, ActivityDataPermissionNodeQuery query, List<PermissionNode> nodes) {
        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(query.getActivityId(), dto.getNumber(), dto.getType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        if (ac == null) {
            return JsonResultS.error("活动不存在或权限不足");
        }
        if (ac.getIsRead() != 1)
            return JsonResultS.error("权限不足");
        List<ActivityClassExamineExcelVo> vo = activityExamineMapper.exportExamineByNode(query.getActivityId(), nodes);
        return JsonResultS.success(vo);
    }

    @Override
    public JsonResultS queryExamineExcelFilter(AuthToken2CredentialDto dto, ActivityDataPermissionNodeQuery query, List<PermissionNode> nodes) {
        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(query.getActivityId(), dto.getNumber(), dto.getType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        if (ac == null) {
            return JsonResultS.error("活动不存在或权限不足");
        }
        if (ac.getIsRead() != 1)
            return JsonResultS.error("权限不足");
        Page<ActivityClassExamineExcelVo> page = new Page<>(query.getPage(), query.getLimit());
        IPage<ActivityClassExamineExcelVo> pageData = activityExamineMapper.queryExamineByNode(page, query.getActivityId(), nodes);
        return JsonResultS.success(pageData);
    }

    @Override
    public JsonResultS queryExamineNumByClass(AuthToken2CredentialDto dto, Integer activityId, ActivityClassQuery query) {
        // todo 加入权限判断
        List<ActivityClassExamineVo> list = activityExamineMapper.queryExamineNumByClass(activityId, query);
        return JsonResultS.success(list);
    }

    @Override
    public JsonResultS queryExamineNumByCollege(AuthToken2CredentialDto dto, Integer activityId, ActivityClassQuery query) {
        // todo 加入权限判断
        List<ActivityCollegeExamineVo> list = activityExamineMapper.queryExamineNumByCollege(activityId, query);
        return JsonResultS.success(list);
    }

    @Override
    public JsonResultS queryDayHourExamine(AuthToken2CredentialDto dto, Integer activityId, String dateTime) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String nowDate = format.format(new Date());
        // todo 加入权限判断
        List<ActivityExamineHourVo> list = activityExamineMapper.queryDayHourExamine(activityId, dateTime);
        return JsonResultS.success(list);
    }

    @Override
    public JsonResultS identity(UserIdentityDto param, Integer activityID) {
        String name = "\\" + param.getName().replace("*", "");
        name = StringUtils.unicode2String(name) + "%";
        String idPrefix = StringUtils.substring(param.getIdCard(), 0, 3) + "%";
        String idSuffix = "%" + StringUtils.substring(param.getIdCard(), param.getIdCard().length() - 4, param.getIdCard().length());
        Student student = studentMapper.getStudentIdentity(name, idPrefix, idSuffix, "21%");
        // 直接返回给审核机200 即使未找到该学生
        if (student == null) {
            return JsonResultS.success();
        }

        examine(new AuthToken2CredentialDto("20190161", Constant.TOKEN_USER_TYPE_TEACHER),
                activityID
                , new AuthToken2CredentialDto(student.getStuNumber(), Constant.TOKEN_USER_TYPE_STUDENT));

        return JsonResultS.success();

    }
}