package com.ict.async.factory;

import cn.hutool.extra.spring.SpringUtil;
import com.ict.domain.dto.VideoInfo;
import com.ict.domain.entity.UserInfo;
import com.ict.util.RedisCache;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static com.ict.constant.CommonConstants.CACHE_TIME;
import static com.ict.constant.RedisConstants.USER_FAVOURITE_LIST;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/22/16:29
 */
@Slf4j
public class AsyncFactor {

    /**
     * 构建用户的喜欢列表缓存
     *
     * @param list   列表
     * @param userId 用户id
     * @return
     */
    public static TimerTask constructUserFavouriteList(List<VideoInfo> list, Long userId) {
        return new TimerTask() {
            @Override
            public void run() {
                RedisCache redisCache = SpringUtil.getBean(RedisCache.class);
                redisCache.setCacheObject(USER_FAVOURITE_LIST + userId, list, CACHE_TIME, TimeUnit.HOURS);
                log.info("构建用户喜欢列表缓存完成！");
            }
        };
    }

    /**
     * 构建用户信息缓存
     *
     * @param userInfo 用户信息
     * @param key      rediskey
     * @return
     */
    public static TimerTask constructUserInfo(UserInfo userInfo, String key) {
        return new TimerTask() {
            @Override
            public void run() {
                RedisCache redisCache = SpringUtil.getBean(RedisCache.class);
                redisCache.setCacheObject(key, userInfo, CACHE_TIME, TimeUnit.HOURS);
                log.info("构建用户信息缓存完成！");
            }
        };
    }


}
