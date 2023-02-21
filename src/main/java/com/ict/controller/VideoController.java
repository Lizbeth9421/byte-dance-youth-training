package com.ict.controller;

import com.ict.domain.AjaxResult;
import com.ict.domain.model.VideoUploadBody;
import com.ict.service.VideoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/21/14:13
 */
@RestController
@RequestMapping("/douyin")
public class VideoController {

    @Resource
    private VideoService videoService;


    @PostMapping("/publish/action/")
    public AjaxResult contribute(VideoUploadBody uploadBody) {
        videoService.contribute(uploadBody);
        return AjaxResult.success("投稿成功");
    }


}
