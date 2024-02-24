// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.user.student.controller;

import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.javaweb.system.common.BaseQuery;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.JwtUtil;
import com.withmore.user.student.entity.Student;
import com.withmore.user.student.query.StudentListQuery;
import com.withmore.user.student.query.StudentQuery;
import com.withmore.user.student.query.StudentSimpleQuery;
import com.withmore.user.student.service.IStudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 学生信息表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @RequiresPermissions("sys:student:index")
    @GetMapping("/index")
    public JsonResult index(StudentQuery query) {
        return studentService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     */
    @Log(title = "学生信息表", logType = LogType.INSERT)
    @RequiresPermissions("sys:student:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Student entity) {
        return studentService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param studentId 记录ID
     */
    @GetMapping("/info/{studentId}")
    public JsonResult info(@PathVariable("studentId") Integer studentId) {
        return studentService.info(studentId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     */
    @Log(title = "学生信息表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:student:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Student entity) {
        return studentService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param studentIds 记录ID
     */
    @Log(title = "学生信息表", logType = LogType.DELETE)
    @RequiresPermissions("sys:student:drop")
    @DeleteMapping("/delete/{studentIds}")
    public JsonResult delete(@PathVariable("studentIds") Integer[] studentIds) {
        return studentService.deleteByIds(studentIds);
    }

    /**
     * 获取学生详情信息
     *
     * @param token 用户Token
     */
    @GetMapping("/query/detail")
    public JsonResultS detail(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return studentService.details(dto);
    }

    /**
     * 获取学生简略信息
     *
     * @param token
     */
    @GetMapping("/query/simple")
    public JsonResultS simple(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return studentService.simple(dto);
    }

    /**
     * 获取可访问范围内的学生列表
     * !需要具有列表访问权限
     *
     * @param baseQuery 分页参数
     * @param token     用户Token
     */
    @RequiresPermissions("sys:student:index")
    @GetMapping("/query/list")
    public JsonResultS list(BaseQuery baseQuery, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return studentService.list(baseQuery, dto);
    }

    /**
     * 搜索指定学号的学生的简略信息
     *
     * @param param 学生学号/学生姓名(模糊匹配)
     */
    // @RequiresPermissions("sys:student:index")
    @GetMapping("/find/simple")
    public JsonResultS one(StudentSimpleQuery param, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return studentService.simpleAuth(param, dto);
    }

    /**
     * 搜索指定参数的学生简略信息
     *
     * @param baseQuery 筛选参数
     * @param token     用户Token
     */
    @RequiresPermissions("sys:student:index")
    @GetMapping("/find/list")
    public JsonResultS list(StudentListQuery baseQuery, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return studentService.listQuery(baseQuery, dto);
    }

    /**
     * 搜索指定学号的学生详细信息
     *
     * @param stuNumber 学生学号
     * @param token     用户Token
     */
    @RequiresPermissions("sys:student:index")
    @GetMapping("/find/detail/{stuNumber}")
    public JsonResultS detail(@PathVariable String stuNumber, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return studentService.details(stuNumber, dto);
    }

}