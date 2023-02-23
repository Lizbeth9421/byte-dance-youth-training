package com.ict.service;

import com.ict.domain.dto.CommentInfo;
import com.ict.domain.entity.Comment;
import com.ict.domain.model.CommentBody;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/23/9:55
 */
public interface CommentService {


    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    /**
     * 评论/删除评论
     *
     * @param commentBody
     * @return
     */
    CommentInfo comment(CommentBody commentBody);

    /**
     * 评论
     *
     * @param userId       用户id
     * @param videoId      视频id
     * @param comment_text 评论内容
     * @return
     */
    CommentInfo tokeComment(Long userId, Long videoId, String comment_text);

    /**
     * 删除评论
     *
     * @param userId     用户id
     * @param videoId    视频id
     * @param comment_id 评论id
     * @return
     */
    CommentInfo deleteComment(Long userId, Long videoId, Long comment_id);

    /**
     * 获取评论列表
     *
     * @param video_id 视频id
     * @return
     */
    List<CommentInfo> getCommentsList( Long video_id);
}

