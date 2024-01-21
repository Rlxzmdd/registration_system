// +----------------------------------------------------------------------
// | JavaWeb_Vue_Pro前后端分离旗舰版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2019~2020 南京JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <1175401194@qq.com>
// +----------------------------------------------------------------------

package com.withmore.resource.img.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.utils.*;
import com.javaweb.system.common.BaseQuery;
import com.javaweb.system.common.BaseServiceImpl;
import com.withmore.common.dto.AliYunOSSDto;
import com.withmore.common.dto.AuthToken2CredentialDto;
import com.withmore.common.utils.aliyun.AliYunOSSUtil;
import com.withmore.resource.img.entity.ResourceImg;
import com.withmore.resource.img.mapper.ResourceImgMapper;
import com.withmore.resource.img.query.ResourceImgQuery;
import com.withmore.resource.img.query.ResourceUuidQuery;
import com.withmore.resource.img.service.IResourceImgService;
import com.withmore.resource.img.vo.resourceimg.ResourceImgInfoVo;
import com.withmore.resource.img.vo.resourceimg.ResourceImgListVo;
import com.withmore.resource.img.vo.resourceimg.ResourceImgSimpleVo;
import com.withmore.user.permission.entity.PermissionNode;
import com.withmore.user.permission.utils.PermissionConvert;
import com.withmore.user.permission.utils.PermissionJudge;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 图片资源表 服务类实现
 * </p>
 *
 * @author Cheney
 * @since 2021-08-03
 */
@Service
public class ResourceImgServiceImpl extends BaseServiceImpl<ResourceImgMapper, ResourceImg> implements IResourceImgService {

    @Autowired
    private ResourceImgMapper resourceImgMapper;

    @Autowired
    private AliYunOSSUtil aliYunOSSUtil;

    @Autowired
    private UploadUtils uploadUtils;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ResourceImgQuery resourceImgQuery = (ResourceImgQuery) query;
        // 查询条件
        QueryWrapper<ResourceImg> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<ResourceImg> page = new Page<>(resourceImgQuery.getPage(), resourceImgQuery.getLimit());
        IPage<ResourceImg> pageData = resourceImgMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            ResourceImgListVo resourceImgListVo = Convert.convert(ResourceImgListVo.class, x);
            return resourceImgListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取详情Vo
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        ResourceImg entity = (ResourceImg) super.getInfo(id);
        // 返回视图Vo
        ResourceImgInfoVo resourceImgInfoVo = new ResourceImgInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, resourceImgInfoVo);
        return resourceImgInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(ResourceImg entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateTime(DateUtils.now());
            entity.setUpdateUser(1);
        } else {
            entity.setCreateTime(DateUtils.now());
            entity.setCreateUser(1);
        }
        return super.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(ResourceImg entity) {
        entity.setUpdateTime(DateUtils.now());
        entity.setUpdateUser(1);
        entity.setMark(0);
        return super.delete(entity);
    }

    @Override
    public JsonResultS del(String uuid, AuthToken2CredentialDto dto) {
        UpdateWrapper<ResourceImg> wrapper = new UpdateWrapper<>();
        wrapper.eq("uuid", uuid);
        wrapper.eq("create_number", dto.getNumber());
        wrapper.eq("mark", 1);
        wrapper.set("mark", 0);
        return update(wrapper) ? JsonResultS.success() : JsonResultS.error();
    }

    @Override
    public JsonResultS upload(HttpServletRequest request, AuthToken2CredentialDto dto) {
        Map<String, Object> map = uploadUtils.uploadFile(request, "notice");
        List<String> fileLocalPath = (List<String>) map.get("image");
        log.warn(String.valueOf(map.get("error")));
        if (!"".equals(String.valueOf(map.get("error"))) || fileLocalPath.size() == 0) {
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0700);
        }

        // 从本地缓存目录中读取文件
        String targetPath = uploadUtils.getFileAbsolutePath(fileLocalPath.get(0));
        File file = new File(targetPath);

        // 获取文件后缀
        String suffix = file.getName().substring(file.getName().lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        // 计算图片MD5
        String md5;
        try {
            md5 = DigestUtils.md5DigestAsHex(FileUtil.getInputStream(file));
        } catch (IOException e) {
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0700);
        }
        // 检查是否存在相同文件，存在直接返回引用UUID
        QueryWrapper<ResourceImg> wrapper = new QueryWrapper<>();
        wrapper.eq("md5", md5);
        wrapper.eq("mark", 1);
        ResourceImg existsImage = getOne(wrapper);
        if (existsImage != null) {
            return JsonResultS
                    .success(ResourceImgSimpleVo.builder()
                            .uuid(existsImage.getUuid())
                            .url(existsImage.getUrl())
                            .build());
        }

        // 上传至OSS
        String ossFileName = uuid + suffix;
        AliYunOSSDto aliYunOSSDto = aliYunOSSUtil.uploadFile(file, aliYunOSSUtil.getImagesPath() + "/notice", ossFileName);
        if (!aliYunOSSDto.isSuccess()) {
            return JsonResultS.error(ResultCodeEnum.USER_ERROR_A0606);
        }
        ResourceImg img = new ResourceImg()
                .setMd5(md5)
                .setUrl(aliYunOSSDto.getUrl())
                .setUuid(uuid)
                .setCreateNumber(dto.getNumber());
        resourceImgMapper.insert(img);

        return JsonResultS
                .success(ResourceImgSimpleVo.builder()
                        .uuid(img.getUuid())
                        .url(img.getUrl())
                        .build());
    }

    @Override
    public JsonResultS gets(ResourceUuidQuery param, AuthToken2CredentialDto dto) {
//        List<PermissionNode> nodes = PermissionConvert.convert2Nodes(dto);

        List<String> urls = new ArrayList<>();
        QueryWrapper<ResourceImg> wrapper = new QueryWrapper<>();
        wrapper.in("uuid", param.getUuid());
        //        wrapper.eq("create_number", dto.getNumber());
        wrapper.eq("mark", 1);
        List<ResourceImg> resourceImg = resourceImgMapper.selectList(wrapper);
        for (ResourceImg img :
                resourceImg) {
            urls.add(img.getUrl());
            //            if (PermissionJudge.judge(img.getCreateNumber(), nodes) || dto.getNumber().equalsIgnoreCase(img.getCreateNumber())) {
            //                urls.add(img.getUrl());
            //            }
        }
        return JsonResultS.success(urls);
    }
}