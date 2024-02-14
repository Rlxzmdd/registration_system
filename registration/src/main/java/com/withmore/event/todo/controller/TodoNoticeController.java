// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.event.todo.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.javaweb.system.common.BaseQuery;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.JwtUtil;
import com.withmore.event.todo.dto.NoticePushDto;
import com.withmore.event.todo.entity.TodoNotice;
import com.withmore.event.todo.query.TodoNoticeQuery;
import com.withmore.event.todo.service.ITodoNoticeService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 待办事项-通知信息表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-07-20
 */
@Slf4j
@RestController
@RequestMapping("/todo_notice")
public class TodoNoticeController extends BaseController {

    @Autowired
    private ITodoNoticeService todoNoticeService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:todo_notice:index")
    @GetMapping("/index")
    public JsonResult index(TodoNoticeQuery query) {
        return todoNoticeService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "待办事项-通知信息表", logType = LogType.INSERT)
    @RequiresPermissions("sys:todo_notice:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody TodoNotice entity) {
        return todoNoticeService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param Id 记录ID
     * @return
     */
    @GetMapping("/info/{Id}")
    public JsonResult info(@PathVariable("Id") Integer Id) {
        return todoNoticeService.info(Id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "待办事项-通知信息表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:todo_notice:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody TodoNotice entity) {
        return todoNoticeService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param Ids 记录ID
     * @return
     */
    @Log(title = "待办事项-通知信息表", logType = LogType.DELETE)
    @RequiresPermissions("sys:todo_notice:drop")
    @DeleteMapping("/delete/{Ids}")
    public JsonResult delete(@PathVariable("Ids") Integer[] Ids) {
        return todoNoticeService.deleteByIds(Ids);
    }

    /**
     * 小程序推送通知
     *
     * @param notice 通知参数
     * @param token  用户Token
     * @return
     */
    @RequiresPermissions("sys:todo_notice:add")
    @PostMapping("/push")
    public JsonResultS push(NoticePushDto notice, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return todoNoticeService.release(notice, dto);
    }

    /**
     * 小程序用户查询通知
     *
     * @param baseQuery 分页参数
     * @param token     用户Token
     * @return
     */
    @GetMapping("/query/list")
    public JsonResultS list(BaseQuery baseQuery, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return todoNoticeService.list(baseQuery, dto);
    }


    /**
     * 小程序用户查询自己发布的通知
     *
     * @param baseQuery 分页参数
     * @param token     用户Token
     * @return
     */

    @GetMapping("/query/myself")
    public JsonResultS myself(BaseQuery baseQuery, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return todoNoticeService.myself(baseQuery, dto);
    }
}