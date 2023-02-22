package com.ict.controller;

import com.ict.domain.AjaxResult;
import com.ict.service.UserFavouriteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/22/15:33
 */
@RestController
@RequestMapping("/douyin/favorite")
public class FavouriteController {
    @Resource
    private UserFavouriteService favouriteService;


    @GetMapping("/list/")
    public AjaxResult getFavouriteListInfo(@RequestParam("user_id") Long user_id,
                                           @RequestParam("token") String token) {
        return AjaxResult.success("获取成功").put("video_list", favouriteService.getFavouriteListInfo(user_id));
    }
}
