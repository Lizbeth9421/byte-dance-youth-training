package com.ict.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ict.async.AsyncManager;
import com.ict.async.factory.AsyncFactor;
import com.ict.domain.dto.VideoInfo;
import com.ict.domain.entity.Video;
import com.ict.domain.model.LoginUser;
import com.ict.security.TokenService;
import com.ict.service.UserInfoService;
import com.ict.service.VideoService;
import com.ict.util.RedisCache;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.ict.domain.entity.UserFavourite;
import com.ict.mapper.UserFavouriteMapper;
import com.ict.service.UserFavouriteService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ict.constant.RedisConstants.USER_FAVOURITE_LIST;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/22/15:33
 */
@Service
public class UserFavouriteServiceImpl implements UserFavouriteService {

    @Resource
    private UserFavouriteMapper userFavouriteMapper;

    @Autowired
    private VideoService videoService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserInfoService infoService;

    @Override
    public int insert(UserFavourite record) {
        return userFavouriteMapper.insert(record);
    }

    @Override
    public int insertSelective(UserFavourite record) {
        return userFavouriteMapper.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VideoInfo> getFavouriteListInfo(final Long userId) {
        //先从缓存获取
        List<VideoInfo> videoInfos = redisCache.getCacheObject(USER_FAVOURITE_LIST + userId);
        if (ObjectUtil.isNull(videoInfos) || videoInfos.isEmpty()) {
            List<Long> favouriteListId = userFavouriteMapper.getFavouriteList(userId);
            List<VideoInfo> favouriteListInfo = videoService.getVideoInfoByVideoId(favouriteListId);
            //异步重构缓存
            AsyncManager.me().execute(AsyncFactor.constructUserFavouriteList(favouriteListInfo, userId));
            return favouriteListInfo;
        }
        return videoInfos;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void like(final String token, final Long video_id, final Integer action_type) {
        //获取用户信息
        LoginUser loginUser = tokenService.getLoginUser(token);
        //获取视频信息
        Video video = videoService.selectByPrimaryKey(video_id);
        //判断是否是当前用户发布的视频
        UserFavouriteService proxy = (UserFavouriteService) AopContext.currentProxy();
        if (action_type == 1) {
            //是当前用户发布的视频
            proxy.takeALike(loginUser, video, loginUser.getUserId().equals(video.getUserId()));
        } else {
            proxy.cancelLike(loginUser, video, loginUser.getUserId().equals(video.getUserId()));
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @SuppressWarnings("all")
    public void takeALike(LoginUser loginUser, Video video, boolean isMySelf) {
        //视频表中的点赞数+1
        videoService.increaseFavouriteCountByVideoId(video.getId());
        //用户视频的点赞关联表插入
        userFavouriteMapper.insert(new UserFavourite(loginUser.getUserId(), video.getId()));
        //用户信息表的点赞数+1
        infoService.increaseFavouriteCount(loginUser.getUserId());
        if (isMySelf) {
            //被点赞数+1
            infoService.increaseTotalFavourite(loginUser.getUserId());
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @SuppressWarnings("unchecked")
    public void cancelLike(LoginUser loginUser, Video video, boolean isMySelf) {
        //视频表中的点赞数-1
        videoService.decreaseFavouriteCountByVideoId(video.getId());
        //用户视频的点赞关联表删除
        userFavouriteMapper.delete(loginUser.getUserId(), video.getId());
        infoService.decreaseFavouriteCount(loginUser.getUserId());
        //用户信息表的点赞数-1
        if (isMySelf) {
            //被点赞数-1
            infoService.decreaseTotalFavourite(loginUser.getUserId());
        }
    }

}
