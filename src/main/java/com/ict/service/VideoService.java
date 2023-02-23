package com.ict.service;

import com.ict.domain.dto.VideoInfo;
import com.ict.domain.entity.Video;
import com.ict.domain.model.VideoUploadBody;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/21/14:14
 */
public interface VideoService {


    int deleteByPrimaryKey(Long id);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    /**
     * 投稿接口
     *
     * @param uploadBody
     */
    void contribute(VideoUploadBody uploadBody);

    /**
     * 获取用户的发布列表
     *
     * @param token
     * @param userId
     * @return
     */
    List<VideoInfo> getPublishList(String token, Integer userId);

    /**
     * 获得投稿列表
     * 有令牌就封装是否点赞字段
     * 没用就不封装该字段
     *
     * @param token 令牌
     * @return
     */
    List<VideoInfo> getFeedList(String token);

    /**
     * 根据视频id列表获取具体视频信息
     *
     * @param list 视频id列表
     * @return
     */
    List<VideoInfo> getVideoInfoByVideoId(List<Long> list);

    /**
     * 视频点赞数+1
     *
     * @param video_id 视频id
     * @return
     */
    Integer increaseFavouriteCountByVideoId(Long video_id);


    /**
     * 视频点赞数-1
     *
     * @param video_id 视频id
     * @return
     */
    Integer decreaseFavouriteCountByVideoId(Long video_id);

    /**
     * 视频评论数+1
     *
     * @param video_id 视频id
     * @return
     */
    Integer increaseCommentCountByVideoId(Long video_id);

    /**
     * 视频评论数-1
     *
     * @param video_id 视频id
     * @return
     */
    Integer decreaseCommentCountByVideoId(Long video_id);
}
