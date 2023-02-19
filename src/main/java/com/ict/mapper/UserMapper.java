package com.ict.mapper;

import com.ict.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/11/16:04
 */
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String username);
}