// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.javaweb.common.service.impl;

import com.javaweb.common.service.IUploadService;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文件上传 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-30
 */
@Service
public class UploadServiceImpl implements IUploadService {

    @Autowired
    private UploadUtils uploadUtils;

    /**
     * 上传图片
     *
     * @param request 网络请求
     * @param name    目录名
     */
    @Override
    public JsonResult uploadImage(HttpServletRequest request, String name) {
        UploadUtils uploadUtils = new UploadUtils();
        Map<String, Object> result = uploadUtils.uploadFile(request, name);
        List<String> imageList = (List<String>) result.get("image");
        String imageUrl = CommonUtils.getImageURL(imageList.get(imageList.size() - 1));
        return JsonResult.success(imageUrl, "上传成功");
    }

    /**
     * 上传文件
     *
     * @param request 网络请求
     * @param name    目录名
     */
    @Override
    public JsonResult uploadFile(HttpServletRequest request, String name) {
        UploadUtils uploadUtils = new UploadUtils();
        uploadUtils.setDirName("files");
        Map<String, Object> result = uploadUtils.uploadFile(request, name);
        List<String> nameList = (List<String>) result.get("name");
        List<String> imageList = (List<String>) result.get("image");
        String imageUrl = CommonUtils.getImageURL(imageList.get(imageList.size() - 1));
        Map<String, Object> map = new HashMap<>();
        map.put("fileName", nameList.get(nameList.size() - 1));
        map.put("fileUrl", imageUrl);
        return JsonResult.success(map, "上传成功");
    }

}
