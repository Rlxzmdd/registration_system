// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.form.controller;

import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.javaweb.system.common.BaseQuery;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.JwtUtil;
import com.withmore.event.form.dto.FormCreateParamDto;
import com.withmore.event.form.entity.Form;
import com.withmore.event.form.service.IFormService;
import com.withmore.event.todo.query.TodoFormQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 表单模版 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Slf4j
@RestController
@RequestMapping("/form")
public class FormController extends BaseController {

    @Autowired
    private IFormService FormService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     */
    @RequiresPermissions("sys:todo_form:index")
    @GetMapping("/index")
    public JsonResult index(TodoFormQuery query) {
        return FormService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     */
    @Log(title = "待办事项-填报事项", logType = LogType.INSERT)
    @RequiresPermissions("sys:todo_form:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Form entity) {
        return FormService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param Id 记录ID
     */
    @GetMapping("/info/{Id}")
    public JsonResult info(@PathVariable("Id") Integer Id) {
        return FormService.info(Id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     */
    @Log(title = "待办事项-填报事项", logType = LogType.UPDATE)
    @RequiresPermissions("sys:todo_form:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Form entity) {
        return FormService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param Ids 记录ID
     */
    @Log(title = "待办事项-填报事项", logType = LogType.DELETE)
    @RequiresPermissions("sys:todo_form:drop")
    @DeleteMapping("/delete/{Ids}")
    public JsonResult delete(@PathVariable("Ids") Integer[] Ids) {
        return FormService.deleteByIds(Ids);
    }

    /**
     * 新建一个表单
     * content-type: application/json
     * 需要创建表单模版权限
     *
     * @param token 用户Token
     * @param param 表单参数
     */
    @RequiresPermissions("sys:todo_form:add")
    @PostMapping("/push")
    public JsonResultS push(@RequestBody FormCreateParamDto param,
                            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return FormService.push(param, dto);
    }

    /**
     * 分页获取表单简略信息列表
     *
     * @param baseQuery 分页参数
     * @param token     用户Token
     */
    @GetMapping("/query/list")
    public JsonResultS list(BaseQuery baseQuery, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return FormService.list(baseQuery, dto);
    }

    /**
     * 获取表单详情
     *
     * @param token 用户Token
     */
    @GetMapping("/query/detail/{formKey}")
    public JsonResultS detail(@PathVariable String formKey, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return FormService.detail(formKey, dto);
    }


}