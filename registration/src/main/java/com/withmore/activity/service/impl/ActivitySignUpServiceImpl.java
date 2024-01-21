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
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.activity.entity.ActivityExamine;
import com.withmore.activity.entity.ActivityItemInfo;
import com.withmore.activity.entity.ActivitySignUp;
import com.withmore.activity.mapper.ActivityItemInfoMapper;
import com.withmore.activity.mapper.ActivitySignUpMapper;
import com.withmore.activity.query.ActivitySignUpQuery;
import com.withmore.activity.service.IActivitySignUpService;
import com.javaweb.system.utils.ShiroUtils;
import com.withmore.activity.vo.activity.ActivityInfoManageVo;
import com.withmore.activity.vo.activitysignup.ActivitySignUpInfoVo;
import com.withmore.activity.vo.activitysignup.ActivitySignUpListVo;
import com.javaweb.common.utils.DateUtils;
import com.javaweb.common.utils.JsonResult;
import com.withmore.common.constant.Constant;
import com.withmore.common.dto.AuthToken2CredentialDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
  * <p>
  * 活动报名信息表 服务类实现
  * </p>
  *
  * @author Zhous
  * @since 2021-08-07
  */
@Service
public class ActivitySignUpServiceImpl extends BaseServiceImpl<ActivitySignUpMapper, ActivitySignUp> implements IActivitySignUpService {

    @Autowired
    private ActivitySignUpMapper activitySignUpMapper;

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
        ActivitySignUpQuery activitySignUpQuery = (ActivitySignUpQuery) query;
        // 查询条件
        QueryWrapper<ActivitySignUp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<ActivitySignUp> page = new Page<>(activitySignUpQuery.getPage(), activitySignUpQuery.getLimit());
        IPage<ActivitySignUp> pageData = activitySignUpMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            ActivitySignUpListVo activitySignUpListVo = Convert.convert(ActivitySignUpListVo.class, x);
            return activitySignUpListVo;
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
        ActivitySignUp entity = (ActivitySignUp) super.getInfo(id);
        // 返回视图Vo
        ActivitySignUpInfoVo activitySignUpInfoVo = new ActivitySignUpInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, activitySignUpInfoVo);
        return activitySignUpInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(ActivitySignUp entity) {
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
    public JsonResult delete(ActivitySignUp entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }


    /**
     * 根据activityId获取报名情况
     * @param activityId
     * @return
     */
    @Override
    public List<ActivitySignUpListVo> getListByActivityId(Integer activityId) {
        List<ActivitySignUpListVo> detailsList = activitySignUpMapper.getSignUpListByActivityId(
                activityId,
                Constant.TOKEN_USER_TYPE_STUDENT,
                Constant.TOKEN_USER_TYPE_TEACHER);
        return detailsList;
    }

    /**
     * 报名活动
     *
     * @param activityId
     * @param dto
     * @return
     */
    public JsonResultS signUp(AuthToken2CredentialDto dto,Integer activityId){
        ActivityInfoManageVo ac = activityItemInfoMapper.getActivity(activityId, dto.getNumber(), dto.getType(),
                Constant.TOKEN_USER_TYPE_STUDENT, Constant.TOKEN_USER_TYPE_TEACHER);
        if (ac == null)
            return JsonResultS.error("活动不存在");
        if (ac.getIsApply() == 0)
            return JsonResultS.error("当前活动无需报名");
        if (ac.getIsSignup() == 0)
            return JsonResultS.error("当前用户已报名过活动");

        QueryWrapper<ActivitySignUp> signUpExist = new QueryWrapper<>();
        signUpExist.eq("activity_item_id", activityId);
        signUpExist.eq("is_approval", 1);
        Integer signUpCount = activitySignUpMapper.selectCount(signUpExist);

        if (signUpCount >= ac.getMaxNum())
            return JsonResultS.error("报名人数已达到上线");

        ActivitySignUp entity = (new ActivitySignUp())
                                    .setActivityItemId(activityId)
                                    .setUserNumber(dto.getNumber())
                                    .setUserType(dto.getType());
        activitySignUpMapper.insert(entity);

        return JsonResultS.success();
    }
}