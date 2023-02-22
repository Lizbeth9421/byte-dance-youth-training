package com.ict.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ict.async.AsyncManager;
import com.ict.async.factory.AsyncFactor;
import com.ict.domain.dto.VideoInfo;
import com.ict.service.VideoService;
import com.ict.util.RedisCache;
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

    @Resource
    private VideoService videoService;

    @Autowired
    private RedisCache redisCache;

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

}
