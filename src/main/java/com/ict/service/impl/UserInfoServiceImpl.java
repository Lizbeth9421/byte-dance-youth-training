package com.ict.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.ict.async.AsyncManager;
import com.ict.async.factory.AsyncFactor;
import com.ict.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.ict.domain.entity.UserInfo;
import com.ict.mapper.UserInfoMapper;
import com.ict.service.UserInfoService;

import java.util.concurrent.TimeUnit;

import static com.ict.constant.RedisConstants.LOGIN_USER_INFO;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/19/22:53
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisCache redisCache;

    @Value("${dousheng.jwt.expire}")
    long expire;

    @Override
    public int deleteByPrimaryKey(Long userId) {
        return userInfoMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(UserInfo record) {
        return userInfoMapper.insertSelective(record);
    }

    @Override
    public UserInfo selectByPrimaryKey(Long userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public UserInfo selectUserInfoByUserId(final Integer user_id) {
        return userInfoMapper.selectUserInfoByUserId(user_id);
    }

    @Override
    public UserInfo getUserInfo(final Integer user_id) {
        String key = LOGIN_USER_INFO + user_id;
        UserInfo userInfo = redisCache.getCacheObject(key);
        if (ObjectUtil.isNull(userInfo)) {
            userInfo = selectUserInfoByUserId(user_id);
            //重构缓存
            AsyncManager.me().execute(AsyncFactor.constructUserInfo(userInfo, key));
        }
        return userInfo;
    }

    @Override
    public Integer increaseWorkCountByUserId(final Long userId) {
        return userInfoMapper.increaseWorkCountByUserId(userId);
    }

}

