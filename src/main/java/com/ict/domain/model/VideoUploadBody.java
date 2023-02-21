package com.ict.domain.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/21/14:16
 */
@Data
public class VideoUploadBody {

    private MultipartFile data;

    private String token;

    private String title;

}
