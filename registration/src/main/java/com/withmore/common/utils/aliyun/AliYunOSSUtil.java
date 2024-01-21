package com.withmore.common.utils.aliyun;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.*;
import com.withmore.common.dto.AliYunOSSDto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Component
public class AliYunOSSUtil {

    /**
     * 阿里云 oss 站点
     */
    @Value("${aliYun.oss.endpoint}")
    private String endpoint;

    /**
     * 阿里云 oss 公钥
     */
    @Value("${aliYun.oss.accessKeyId}")
    private String accessKeyId;

    /**
     * 阿里云 oss 私钥
     */
    @Value("${aliYun.oss.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 阿里云 oss 文件根目录
     */
    @Value("${aliYun.oss.bucketName}")
    private String bucketName;

    /**
     * 上传文件大小(M)
     */
    @Value("${aliYun.oss.maxSize}")
    private Long maxSize;

    /**
     * OSS 图片素材保存目录
     */
    @Value("${aliYun.oss.imagesPath}")
    private String imagesPath;

    /**
     * 过期时间
     */
    @Value("${aliYun.oss.expire}")
    private Integer expire;

    /**
     * 是否生成带过期时间的资源链接
     */
    @Value("${aliYun.oss.isExpire}")
    private boolean isExpire;

    /**
     * 资源域名
     */
    @Value("${aliYun.oss.resourceDomain}")
    private String resourceDomain;

    /**
     * oss 工具客户端
     */
    private OSSClient ossClient;


    /**
     * 上传文件-自定义路径
     *
     * @param file     上传文件
     * @param fileName 上传至OSS的文件完整路径，例：cf/abc.png
     *                 上传至根目录，例：abc.png
     * @return
     */
    public AliYunOSSDto uploadFile(File file, String dirPath, String fileName) {
        // 文件流
        InputStream inputStream = FileUtil.getInputStream(file);
        // 获取文件类型
        String fileType = FileUtil.getType(file);
        // 文件保存路径
        String filePath = String.format("%s/%s", dirPath, fileName);
        // 上传文件
        return uploadInputStream(inputStream, fileType, filePath);
    }

    /**
     * 上传文件-自定义路径
     *
     * @param file     上传文件
     * @param fileName 上传至OSS的文件完整路径，例：cf/abc.png
     *                 上传至根目录，例：abc.png
     * @return
     */
    public AliYunOSSDto uploadFile(MultipartFile file, String dirPath, String fileName) {
        // 文件流
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return new AliYunOSSDto(false, null, null, e.getMessage());
        }
        // 获取文件类型
        String fileType = file.getContentType();
        // 文件保存路径
        String filePath = String.format("%s/%s", dirPath, fileName);
        // 上传文件
        return uploadInputStream(inputStream, fileType, filePath);
    }

    /**
     * 上传文件-自定义路径
     *
     * @param inputStream 上传文件流
     * @param fileType    文件类型，例：png
     * @param fileName    上传至OSS的文件完整路径，例：cf/abc.png
     *                    上传至根目录，例：abc.png
     * @return
     */
    public AliYunOSSDto uploadInputStream(InputStream inputStream, String fileType, String fileName) {
        if (inputStream == null) {
            return new AliYunOSSDto(false, null, null, "文件不能为空");
        }
        if (StrUtil.isBlank(fileName)) {
            return new AliYunOSSDto(false, null, null, "文件名不能为空");
        }
        // 上传文件最大值 MB->bytes
        long maxSize = this.maxSize * 1024 * 1024;
        // 本次上传文件的大小
        long fileSize = 0;
        try {
            fileSize = inputStream.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileSize <= 0 || fileSize > maxSize) {
            return new AliYunOSSDto(false, null, null, "文件超过最大限制");
        }
        // 上传文件
        return putFile(inputStream, fileType, fileName);
    }

    /**
     * 上传文件
     *
     * @param input
     * @param fileType
     * @param fileName
     * @return
     */
    private AliYunOSSDto putFile(InputStream input, String fileType, String fileName) {

        try {
            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();
            // 设置上传内容类型
            meta.setContentType(fileType);
            //被下载时网页的缓存行为
            meta.setCacheControl("no-cache");
            //创建上传请求
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, input, meta);
            //上传文件
            ossClient.putObject(request);
            //获取上传成功的文件地址
            return new AliYunOSSDto(true, fileName, getOssUrl(fileName), "上传成功");
        } catch (OSSException | ClientException e) {
            e.printStackTrace();
            return new AliYunOSSDto(false, fileName, null, e.getMessage());
        }
    }

    /**
     * 根据文件名生成文件的访问地址（带过期时间）
     *
     * @param fileName
     * @return
     */
    public String getOssUrl(String fileName) {
        // 生成过期时间
        if (isExpire) {
            long expireEndTime = System.currentTimeMillis() + expire * 1000;
            Date expiration = new Date(expireEndTime);// 生成URL
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName);
            generatePresignedUrlRequest.setExpiration(expiration);
            URL url = ossClient.generatePresignedUrl(generatePresignedUrlRequest);
            return url.toString();
        }
        return String.format("%s/%s" , resourceDomain ,fileName);
    }

    /**
     * 通过文件名下载文件
     *
     * @param fileName      要下载的文件名（OSS服务器上的）
     *                      例如：4DB049D0604047989183CB68D76E969D.jpg
     * @param localFileName 本地要创建的文件名（下载到本地的）
     *                      例如：C:\Users\Administrator\Desktop\test.jpg
     */
    public void downloadFile(String fileName, String localFileName) {
        // 下载OSS文件到指定目录。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, fileName), new File(localFileName));
    }

    /**
     * 通过文件名获取文件流
     *
     * @param fileName 要下载的文件名（OSS服务器上的）
     *                 例如：4DB049D0604047989183CB68D76E969D.jpg
     */
    public InputStream getInputStream(String fileName) {

        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        return ossClient.getObject(new GetObjectRequest(bucketName, fileName)).getObjectContent();
    }

    /**
     * 通过文件名获取byte[]
     *
     * @param fileName 要下载的文件名（OSS服务器上的）
     *                 例如：4DB049D0604047989183CB68D76E969D.jpg
     */
    public byte[] getBytes(String fileName) {
        InputStream inputStream = getInputStream(fileName);
        FastByteArrayOutputStream fastByteArrayOutputStream = IoUtil.read(inputStream);
        return fastByteArrayOutputStream.toByteArray();
    }

    /**
     * 根据文件名删除文件
     *
     * @param fileName 需要删除的文件名
     * @return boolean 是否删除成功
     * 例如：4DB049D0604047989183CB68D76E969D.jpg
     */
    public boolean deleteFile(String fileName) {

        try {
            if (bucketName == null || fileName == null) {
                return false;
            }
            GenericRequest request = new DeleteObjectsRequest(bucketName).withKey(fileName);
            ossClient.deleteObject(request);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 列举所有的文件url
     */
    public List<String> urlList() {
        List<String> list = new ArrayList<>();
        // 构造ListObjectsRequest请求
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
        // 列出文件
        ObjectListing listing = ossClient.listObjects(listObjectsRequest);
        // 遍历所有文件
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            // 把key全部转化成可以访问的url
            String url = getOssUrl(objectSummary.getKey());
            list.add(url);
        }
        return list;
    }
}
