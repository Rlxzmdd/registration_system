// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.resource.img.service;

import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.JsonResultS;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.resource.img.entity.ResourceImg;
import com.javaweb.system.common.IBaseService;
import com.withmore.resource.img.query.ResourceUuidQuery;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>
 * 图片资源表 服务类
 * </p>
 *
 * @author Cheney
 * @since 2021-08-03
 */
public interface IResourceImgService extends IBaseService<ResourceImg> {

    /**
     * 上传文件图片至阿里云OSS
     *
     * @param request 请求
     * @param dto     用户凭据
     * @return
     */
    JsonResultS upload(HttpServletRequest request, AuthToken2CredentialDto dto);

    /**
     * 逻辑删除用户上传的图片
     *
     * @param uuid 图片UUID
     * @param dto  用户凭据
     * @return
     */
    JsonResultS del(String uuid, AuthToken2CredentialDto dto);

    /**
     * 获取一组UUID 所对应的OSS链接
     *
     * @param param 查询参数
     * @param dto   用户凭据
     * @return
     */
    JsonResultS gets(ResourceUuidQuery param, AuthToken2CredentialDto dto);
}