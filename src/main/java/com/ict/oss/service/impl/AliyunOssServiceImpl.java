package com.ict.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.ict.exception.ServiceException;
import com.ict.oss.AliyunOssConfiguration;
import com.ict.oss.service.OssService;
import com.ict.util.FileNameUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/21/13:08
 */
@Service
@Slf4j
public class AliyunOssServiceImpl implements OssService {

    @Autowired
    private OSS ossClient;

    @Autowired
    private AliyunOssConfiguration ossConfiguration;


    @Override
    public String uploadFile(final MultipartFile file, final String resourceGroup) {
        String actuallyUrl = "";
        try {
            //文件原始名字
            String filename = file.getOriginalFilename();
            String newFileName = FileNameUtil.createFileNameUseTime(filename);
            InputStream inputStream = file.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentDisposition("inline;filename=" + filename);
            ossClient.putObject(ossConfiguration.getBucketName(), resourceGroup + newFileName, inputStream, objectMetadata);
            actuallyUrl = ossConfiguration.getBucketDomain() + "/" + resourceGroup + newFileName;
            log.info(actuallyUrl+"===============================");
        } catch (IOException e) {
            log.error("文件上传失败！");
            log.error(e.toString());
            throw new ServiceException("文件上传失败！");
        }
        return actuallyUrl;
    }
}
