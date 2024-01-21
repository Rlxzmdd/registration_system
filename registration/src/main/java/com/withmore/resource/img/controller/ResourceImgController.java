// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.resource.img.controller;

import com.javaweb.common.enums.LogType;
import com.javaweb.common.utils.JsonResultS;
import com.javaweb.system.common.BaseController;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.JwtUtil;
import com.withmore.resource.img.entity.ResourceImg;
import com.withmore.resource.img.query.ResourceImgQuery;
import com.withmore.resource.img.query.ResourceUuidQuery;
import com.withmore.resource.img.service.IResourceImgService;
import com.javaweb.common.annotation.Log;
import com.javaweb.common.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 图片资源表 前端控制器
 * </p>
 *
 * @author Cheney
 * @since 2021-08-03
 */
@Slf4j
@RestController
@RequestMapping("/resource_img")
public class ResourceImgController extends BaseController {

    @Autowired
    private IResourceImgService resourceImgService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @RequiresPermissions("sys:resource_img:index")
    @GetMapping("/index")
    public JsonResult index(ResourceImgQuery query) {
        return resourceImgService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "图片资源表", logType = LogType.INSERT)
    @RequiresPermissions("sys:resource_img:add")
    @PostMapping("/add")
    public JsonResult add(@RequestBody ResourceImg entity) {
        return resourceImgService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param Id 记录ID
     * @return
     */
    @GetMapping("/info/{Id}")
    public JsonResult info(@PathVariable("Id") Integer Id) {
        return resourceImgService.info(Id);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Log(title = "图片资源表", logType = LogType.UPDATE)
    @RequiresPermissions("sys:resource_img:edit")
    @PutMapping("/edit")
    public JsonResult edit(@RequestBody ResourceImg entity) {
        return resourceImgService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param Ids 记录ID
     * @return
     */
    @Log(title = "图片资源表", logType = LogType.DELETE)
    @RequiresPermissions("sys:resource_img:drop")
    @DeleteMapping("/delete/{Ids}")
    public JsonResult delete(@PathVariable("Ids") Integer[] Ids) {
        return resourceImgService.deleteByIds(Ids);
    }

    /**
     * 小程序上传图片至OSS
     * 返回图片素材表中UUID，映射resource_img表的OSS资源链接
     *
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public JsonResultS upload(HttpServletRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return resourceImgService.upload(request, dto);
    }

    /**
     * 小程序删除上传的图片
     * 根据UUID 进行逻辑删除
     *
     * @param uuid  图片资源UUID
     * @param token 用户Token
     * @return
     */
    @DeleteMapping("/del/{uuid}")
    public JsonResultS del(@PathVariable String uuid, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return resourceImgService.del(uuid, dto);
    }

    @GetMapping("/gets")
    public JsonResultS gets(ResourceUuidQuery param, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        AuthToken2CredentialDto dto = AuthToken2CredentialDto.create(jwtUtil, token);
        return resourceImgService.gets(param , dto);
    }
}