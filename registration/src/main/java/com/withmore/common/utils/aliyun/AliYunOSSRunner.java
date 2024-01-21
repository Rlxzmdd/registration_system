package com.withmore.common.utils.aliyun;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class AliYunOSSRunner implements ApplicationRunner {

    @Autowired
    private AliYunOSSUtil aliYunOSSUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        aliYunOSSUtil.setOssClient(
                new OSSClient(aliYunOSSUtil.getEndpoint(),
                        new DefaultCredentialProvider(aliYunOSSUtil.getAccessKeyId(), aliYunOSSUtil.getAccessKeySecret()),
                        new ClientConfiguration()));
    }
}
