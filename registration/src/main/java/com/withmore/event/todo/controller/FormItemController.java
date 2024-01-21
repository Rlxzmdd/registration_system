package com.withmore.event.todo.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.JwtUtil;
import com.withmore.event.todo.dto.FormPushParamDto;
import com.withmore.event.todo.dto.FormSpecifiedUserDto;
import com.withmore.event.todo.entity.FormItem;
import com.withmore.event.todo.query.FormItemQuery;
import com.withmore.event.todo.service.IFormItemService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import com.withmore.event.todo.vo.formItem.FormItemStatusDto;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 待办事项-用户提交的表单 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@RestController
@RequestMapping("/form_item")
public class FormItemController extends BaseController {

    @Autowired
    private IFormItemService formItemService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:form_item:index")
    @GetMapping("/index")
    public JsonResult index(FormItemQuery query) {
        return formItemService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "待办事项-用户提交的表单", logType = LogType.INSERT)
    @RequiresPermissions("sys:form_item:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody FormItem entity) {
        return formItemService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param Id 记录ID
     * @return
     */
    @GetMapping("/info/{Id}")
    public JsonResult info(@PathVariable("Id") Integer Id) {
        return formItemService.info(Id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "待办事项-用户提交的表单", logType = LogType.UPDATE)
    @RequiresPermissions("sys:form_item:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody FormItem entity) {
        return formItemService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param Ids 记录ID
     * @return
     */
    @Log(title = "待办事项-用户提交的表单", logType = LogType.DELETE)
    @RequiresPermissions("sys:form_item:drop")
    @DeleteMapping("/delete/{Ids}")
    public JsonResult delete(@PathVariable("Ids") Integer[] Ids) {
        return formItemService.deleteByIds(Ids);
    }

    /**
     * 获取指定的用户提交的表单数据
     * 需要表单数据
     *
     * @param formKey 表单Key
     * @param param   查询参数
     * @param token   用户Token
     * @return
     */
    @RequiresPermissions("sys:todo_form:index")
    @GetMapping("/query/specified/{formKey}")
    public JsonResultS specified(@PathVariable String formKey,
                                 FormSpecifiedUserDto param,
                                 @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return formItemService.specified(formKey, param, dto);
    }

    /**
     * 获取我提交的表单数据
     *
     * @param formKey 表单Key
     * @return
     */
    @GetMapping("/query/data/{formKey}")
    public JsonResultS query(@PathVariable String formKey,
                             @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return formItemService.queryForm(formKey, dto);
    }

    /**
     * 推送用户填写的表单数据
     *
     * @param formKey  表单Key
     * @param formData 表单数据
     * @param token    用户Token
     * @return
     */
    @PostMapping("/push/{formKey}")
    public JsonResultS push(@PathVariable String formKey,
                            @RequestBody FormPushParamDto formData,
                            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return formItemService.push(formKey, formData, dto);
    }

    /**
     * 获取用户提交过的表单及其表单的审核状态
     *
     * @return
     */
    @GetMapping("/query/status")
    public JsonResultS status(FormItemStatusDto param,
                              @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return formItemService.statusList(param, dto);
    }
}