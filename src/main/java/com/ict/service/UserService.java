package com.ict.service;

import com.ict.domain.entity.User;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/11/16:04
 */
public interface UserService {


    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    /**
     * 根据用户名选择
     *
     * @param username 用户名
     * @return
     */
    User selectByUserName(String username);

}
