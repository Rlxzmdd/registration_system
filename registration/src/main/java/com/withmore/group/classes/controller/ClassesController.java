// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.group.classes.controller;

import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.javaweb.system.common.BaseQuery;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.JwtUtil;
import com.withmore.group.classes.entity.Classes;
import com.withmore.group.classes.query.ClassesQuery;
import com.withmore.group.classes.service.IClassesService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 班级信息表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Slf4j
@RestController
@RequestMapping("/classes")
public class ClassesController extends BaseController {

    @Autowired
    private IClassesService classesService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @RequiresPermissions("sys:classes:index")
    @GetMapping("/index")
    public JsonResult index(ClassesQuery query) {
        return classesService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     */
    @Log(title = "班级信息表", logType = LogType.INSERT)
    @RequiresPermissions("sys:classes:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Classes entity) {
        log.warn(entity.toString());
        return classesService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param classesId 记录ID
     */
    @GetMapping("/info/{classesId}")
    public JsonResult info(@PathVariable("classesId") Integer classesId) {
        return classesService.info(classesId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     */
    @Log(title = "班级信息表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:classes:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Classes entity) {
        return classesService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param classesIds 记录ID
     */
    @Log(title = "班级信息表", logType = LogType.DELETE)
    @RequiresPermissions("sys:classes:drop")
    @DeleteMapping("/delete/{classesIds}")
    public JsonResult delete(@PathVariable("classesIds") Integer[] classesIds) {
        return classesService.deleteByIds(classesIds);
    }

    /**
     * 查询可访问的班级内的简略班级信息
     *
     * @param baseQuery 分页参数
     * @param token     用户token
     */
    @RequiresPermissions("sys:classes:index")
    @GetMapping("/query/list")
    public JsonResultS list(BaseQuery baseQuery, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return classesService.list(baseQuery, dto);
    }

    /**
     * 查询指定班级的简略信息
     *
     * @param id    班级ID
     * @param token 用户token
     */
    @RequiresPermissions("sys:classes:index")
    @GetMapping("/query/simple/{id}")
    public JsonResultS simple(@PathVariable String id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return classesService.simple(Integer.valueOf(id), dto);
    }

    /**
     * 查询指定班级的班主任、辅导员、助辅等联系信息
     *
     * @param id    班级ID
     * @param token 用户Token
     */
    @GetMapping("/query/contact/{id}")
    public JsonResultS contact(@PathVariable String id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return classesService.contact(id);
    }

    /**
     * 获取班级、专业、学院字典
     *
     */
    @GetMapping("/query/dict")
    public JsonResultS dict() {
        return classesService.dict();
    }
}