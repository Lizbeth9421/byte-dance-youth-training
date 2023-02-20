package com.ict.mapper;

import com.ict.domain.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/19/22:54
 */
@Mapper
public interface UserInfoMapper {
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
    UserInfo selectUserInfoByUserId(@Param("user_id") Integer user_id);
}