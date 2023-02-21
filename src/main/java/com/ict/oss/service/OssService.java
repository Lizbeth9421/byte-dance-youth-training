package com.ict.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/21/13:07
 */
public interface OssService {

    /**
     * 上传文件
     *
     * @param file          文件
     * @param resourceGroup 资源组
     * @return 完整路径
     */
    String uploadFile(MultipartFile file, String resourceGroup);


}
