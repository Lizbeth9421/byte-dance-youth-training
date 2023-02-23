package com.ict.mapper;

import com.ict.domain.dto.VideoInfo;
import com.ict.domain.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/21/14:14
 */
@Mapper
public interface VideoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    /**
     * 根据用户id查询发布列表
     *
     * @param userId 用户id
     * @return
     */
    List<VideoInfo> getPublishListByUserId(@Param("userId") Integer userId);


    /**
     * 获取视频列表
     *
     * @param userId
     * @return
     */
    List<VideoInfo> getFeedList(@Param("userId") Integer userId);

    /**
     * 根据视频id获取具体视频信息
     *
     * @param list 视频id
     * @return
     */
    List<VideoInfo> getVideoInfoByVideoId(@Param("list") List<Long> list);

    /**
     * 视频点赞数+1
     *
     * @param video_id 视频id
     * @return
     */
    Integer increaseFavouriteCountByVideoId(@Param("video_id") Long video_id);

    /**
     * 视频点赞数-1
     *
     * @param video_id 视频id
     * @return
     */
    Integer decreaseFavouriteCountByVideoId(@Param("video_id") Long video_id);

    /**
     * 视频评论数+1
     *
     * @param video_id 视频id
     * @return
     */
    Integer increaseCommentCountByVideoId(@Param("video_id") Long video_id);

    /**
     * 视频评论数-1
     *
     * @param video_id 视频id
     * @return
     */
    Integer decreaseCommentCountByVideoId(@Param("video_id") Long video_id);
}