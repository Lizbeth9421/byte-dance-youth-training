package com.ict.controller;

import com.ict.domain.AjaxResult;
import com.ict.domain.model.VideoUploadBody;
import com.ict.service.VideoService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/publish/list/")
    public AjaxResult publishList(@RequestParam("token") String token,
                                  @RequestParam("user_id") Integer userId) {
        return AjaxResult.success("获取成功").put("video_list", videoService.getPublishList(token, userId));
    }

    @GetMapping("/feed")
    public AjaxResult getFeedList(@RequestParam(value = "token", required = false) String token,
                                  @RequestParam(value = "latest_time", required = false) String latest_time) {
        return AjaxResult.success("获取成功").put("video_list", videoService.getFeedList(token));
    }

}
