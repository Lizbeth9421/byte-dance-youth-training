package com.ict.service;

import com.ict.domain.entity.UserInfo;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/19/22:53
 */
public interface UserInfoService {


    int deleteByPrimaryKey(Long userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 根据用户id查询用户信息
     *
     * @param user_id 用户id
     * @return
     */
    UserInfo selectUserInfoByUserId(Integer user_id);

    /**
     * 获取用户信息
     *
     * @param user_id 用户id
     * @return
     */
    UserInfo getUserInfo(Integer user_id);


    /**
     * 增加用户作品数
     *
     * @param userId 用户id
     * @return
     */
    Integer increaseWorkCountByUserId(Long userId);


    /**
     * 用户信息的点赞数+1
     *
     * @param userId
     * @return
     */
    Integer increaseFavouriteCount(Long userId);

    /**
     * 用户信息的被点赞数+1
     *
     * @param userId
     * @return
     */
    Integer increaseTotalFavourite(Long userId);


    /**
     * 用户信息的点赞数-1
     *
     * @param userId
     * @return
     */
    Integer decreaseFavouriteCount(Long userId);

    /**
     * 用户信息的被点赞数-1
     *
     * @param userId
     * @return
     */
    Integer decreaseTotalFavourite(Long userId);

}

