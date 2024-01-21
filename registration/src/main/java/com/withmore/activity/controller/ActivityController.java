package com.withmore.activity.controller;

import com.aliyun.oss.common.utils.HttpHeaders;
import com.javaweb.common.utils.ExcelUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.query.LevelQuery;
import com.javaweb.system.vo.level.LevelInfoVo;
import com.withmore.activity.entity.ActivityItemInfo;
import com.withmore.activity.query.*;
import com.withmore.activity.service.*;
import com.withmore.activity.vo.activity.ActivityClassExamineExcelVo;
import com.withmore.activity.vo.activity.ActivityExamineVo;
import com.withmore.activity.vo.activity.ActivityInfoVo;
import com.withmore.activity.vo.activitysignup.ActivitySignUpListVo;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.JwtUtil;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.permission.utils.PermissionConvert;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 活动 前端控制器
 * </p>
 *
 * @author Zhous
 * @since 2021-08-05
 */
@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    @Autowired
    private IActivityItemInfoService activityItemInfoService;

    @Autowired
    private IActivityExamineService activityExamineService;

    @Autowired
    private IActivityItemManageListService activityItemManageListService;

    @Autowired
    private IActivitySignUpService activitySignUpService;

    @Autowired
    private JwtUtil jwtUtil;


    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    //@RequiresPermissions("sys:activity:query:list")
    @GetMapping("/query/list")
    public JsonResultS index(ActivityItemInfoQuery query, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return activityItemInfoService.getActivityList(dto, query);
    }

    /**
     * 获取活动详情
     *
     * @param activityId 活动id
     * @return
     */
    @GetMapping("/query/detail/{activityId}")
    public JsonResultS getDetail(@PathVariable("activityId") Integer activityId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return activityItemInfoService.getDetail(dto, activityId);
    }
    /**
     * 删除活动
     *
     * @param
     * @return
     */
    //@RequiresPermissions("sys:activity:del")
    @DeleteMapping("/del/{activityId}")
    public JsonResultS del(@PathVariable("activityId") Integer activityId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return activityItemInfoService.delActivityById(dto,activityId);
    }

    /**
     * 创建活动
     */
    //@RequiresPermissions("sys:activity:push")
    @PostMapping("/push")
    public JsonResultS push(ActivityItemInfo entity, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return activityItemInfoService.push(dto,entity);
    }

    /**
     * 报名活动
     */
    //@RequiresPermissions("sys:activity:push")
    @PostMapping("/signup/{activityId}")
    public JsonResultS signup(@PathVariable("activityId") Integer activityId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return activitySignUpService.signUp(dto,activityId);
    }

    /**
     * 核销活动
     */
    //@RequiresPermissions("sys:activity:push")
    @PostMapping("/examine/add")
    public JsonResultS examineAdd(ActivityExamineAddQuery eaQuery,
                                  @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if(!jwtUtil.verify(eaQuery.getQrcodeToken())){
            return JsonResultS.error("该二维码不是信息码或者已过期");
        }
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        AuthToken2CredentialDto examinedDto = AuthToken2CredentialDto.create(jwtUtil, eaQuery.getQrcodeToken());
        return activityExamineService.examine(dto,eaQuery.getActivityId(),examinedDto);
    }

    /**
     * 活动核销列表
     */
    //@RequiresPermissions("sys:activity:push")
    @GetMapping("/examine/query/list")
    public JsonResultS getExamineList(ActivityExamineListQuery query,
                                      @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return activityExamineService.getExamineList(dto, query);
    }

    /**
     * 查询活动当前用户的核销列表
     */
    //@RequiresPermissions("sys:activity:push")
    @GetMapping("/examine/query/self")
    public JsonResultS getExamineListSelf(BaseQuery query,
                                      @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return activityExamineService.getExamineListSelf(dto, query);
    }

    @GetMapping("/examine/export/excel/{activityId}")
    public JsonResultS exportExcel(ActivityExamineListQuery query, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        JsonResultS result = activityExamineService.getExamineList(dto,query);
        if(!result.getCode().equals("00000")){
            return result;
        }
        List<ActivityExamineVo> ExamineInfoVoList = (List<ActivityExamineVo>) result.getData();
        ExcelUtils<ActivityExamineVo> excelUtils = new ExcelUtils<ActivityExamineVo>(ActivityExamineVo.class);
        return JsonResultS.success(excelUtils.exportExcel(ExamineInfoVoList, "核销名单").getData());

    }

    /**
     * 查询活动的管理员名单
     */
    @GetMapping("/manage/query/list")
    public JsonResultS getExamineListSelf(ActivityManageListQuery query,
                                          @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return activityItemManageListService.getManageListSelf(dto, query);
    }



    /**
     * 添加活动的管理员
     */
    @PostMapping("/manage/add")
    public JsonResultS addManager(ActivityManageAddQuery query,
                                  @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return activityItemManageListService.addManager(dto, query);
    }

    /**
     * 删除活动的管理员
     */
    @DeleteMapping("/manage/del")
    public JsonResultS delManager(ActivityManageAddQuery query,
                                  @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return activityItemManageListService.delManager(dto, query);
    }

    @GetMapping("/data/examine/excel/{activityId}")
    public JsonResultS exportExamineExcel(ActivityDataPermissionNodeQuery query,
                                          @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        //todo 加载筛选器
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        List<PermissionNode> nodes = PermissionConvert.convert2Nodes(dto);
        JsonResultS result = activityExamineService.exportExamineExcelFilter(dto, query, nodes);
        List<ActivityClassExamineExcelVo> ExamineInfoVoList = (List<ActivityClassExamineExcelVo>) result.getData();
        ExcelUtils<ActivityClassExamineExcelVo> excelUtils = new ExcelUtils<ActivityClassExamineExcelVo>(ActivityClassExamineExcelVo.class);
        return JsonResultS.success(excelUtils.exportExcel(ExamineInfoVoList, "核销名单").getData());

    }

    @GetMapping("/data/examine/info/{activityId}")
    public JsonResultS queryExamineExcel(ActivityDataPermissionNodeQuery query,
                                          @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        //todo 加载筛选器
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        List<PermissionNode> nodes = PermissionConvert.convert2Nodes(dto);
        return activityExamineService.queryExamineExcelFilter(dto, query, nodes);

    }
}
