package com.ict.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/21/13:03
 */
@Configuration
@Data
public class AliyunOssConfiguration {
    private volatile static OSS ossClient;

    private volatile static OSSClientBuilder ossClientBuilder;

    @Value("${aliyun.endpoint}")
    private String endpoint;

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.bucketName}")
    private String bucketName;

    @Value("${aliyun.bucketDomain}")
    private String bucketDomain;


    @Bean
    @Scope("prototype")
    public OSS initOSSClient() {
        if (ossClient == null) {
            synchronized (AliyunOssConfiguration.class) {
                if (ossClient == null) {
                    ossClient = initOSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                }
            }
        }
        return ossClient;
    }

    public static OSSClientBuilder initOSSClientBuilder() {
        if (ossClientBuilder == null) {
            synchronized (AliyunOssConfiguration.class) {
                if (ossClientBuilder == null) {
                    ossClientBuilder = new OSSClientBuilder();
                }
            }
        }
        return ossClientBuilder;
    }
}
