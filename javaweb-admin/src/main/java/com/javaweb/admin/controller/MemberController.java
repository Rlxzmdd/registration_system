// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.admin.controller;


import com.javaweb.admin.entity.Member;
import com.javaweb.admin.query.MemberQuery;
import com.javaweb.admin.service.IMemberService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.system.common.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/member")
public class MemberController extends BaseController {

    @Autowired
    private IMemberService memberService;

    /**
     * 获取会员列表
     *
     * @param memberQuery 查询条件
     */
    @RequiresPermissions("sys:member:index")
    @GetMapping("/index")
    public JsonResult index(MemberQuery memberQuery) {
        return memberService.getList(memberQuery);
    }

    /**
     * 添加会员
     *
     * @param entity 实体对象
     */
    @Log(title = "会员管理", logType = LogType.INSERT)
    @RequiresPermissions("sys:member:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody Member entity) {
        return memberService.edit(entity);
    }

    /**
     * 编辑会员
     *
     * @param entity 实体对象
     */
    @Log(title = "会员管理", logType = LogType.UPDATE)
    @RequiresPermissions("sys:member:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody Member entity) {
        return memberService.edit(entity);
    }

    /**
     * 删除会员
     *
     * @param memberIds 会员ID
     */
    @Log(title = "会员管理", logType = LogType.DELETE)
    @RequiresPermissions("sys:member:delete")
    @DeleteMapping("/delete/{memberIds}")
    public JsonResult delete(@PathVariable("memberIds") Integer[] memberIds) {
        return memberService.deleteByIds(memberIds);
    }

    /**
     * 设置状态
     *
     * @param entity 实体对象
     */
    @Log(title = "会员管理", logType = LogType.STATUS)
    @RequiresPermissions("sys:member:status")
    @PutMapping("/status")
    public JsonResult status(@RequestBody Member entity) {
        return memberService.setStatus(entity);
    }

}
