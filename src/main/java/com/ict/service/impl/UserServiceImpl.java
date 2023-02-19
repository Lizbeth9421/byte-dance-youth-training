package com.ict.service.impl;

import com.ict.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ict.domain.entity.User;
import com.ict.mapper.UserMapper;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/11/16:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Long userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User selectByUserName(final String username) {
        return userMapper.selectByUserName(username);
    }

}
