package com.ict.mapper;

import com.ict.domain.dto.VideoInfo;
import com.ict.domain.entity.UserFavourite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/22/15:33
 */
@Mapper
public interface UserFavouriteMapper {
    int insert(UserFavourite record);

    int insertSelective(UserFavourite record);


    /**
     * 根据用户id获取点赞过的视频
     *
     * @param userId 用户id
     * @return
     */
    List<Long> getFavouriteList(@Param("userId") Long userId);

}