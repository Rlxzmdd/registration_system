package com.withmore.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 阿里云OSS上传结果集
 *
 * @author jason
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AliYunOSSDto {

    /**
     * 上传是否成功
     */
    private boolean success;

    /**
     * 上传的文件名（如果使用自定义文件路径，会返回完整的路径+文件名，例：dir/abc.png）
     */
    private String fileName;

    /**
     * 上传成功的返回URL
     */
    private String url;

    /**
     * 提示信息
     */
    private String msg;

}