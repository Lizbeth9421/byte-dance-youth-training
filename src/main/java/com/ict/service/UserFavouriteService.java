package com.ict.service;

import com.ict.domain.dto.VideoInfo;
import com.ict.domain.entity.UserFavourite;
import com.ict.domain.entity.Video;
import com.ict.domain.model.LoginUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/22/15:33
 */
public interface UserFavouriteService {


    int insert(UserFavourite record);

    int insertSelective(UserFavourite record);


    /**
     * 根据用户id获取点赞过的视频信息
     *
     * @param userId 用户id
     * @return
     */
    List<VideoInfo> getFavouriteListInfo(@Param("userId") Long userId);

    /**
     * 点赞/取消点赞
     *
     * @param token       令牌
     * @param video_id    视频id
     * @param action_type 操作类型 1-点赞，2-取消点赞
     */
    void like(String token, Long video_id, Integer action_type);

    /**
     * 点赞
     *
     * @param loginUser 用户信息
     * @param video     视频
     * @param isMySelf  是否是自己发布的视频
     */
    void takeALike(LoginUser loginUser, Video video, boolean isMySelf);

    /**
     * 取消点赞
     *
     * @param loginUser 用户信息
     * @param video     视频
     * @param isMySelf  是否是自己发布的视频
     */
    void cancelLike(LoginUser loginUser, Video video, boolean isMySelf);
}
