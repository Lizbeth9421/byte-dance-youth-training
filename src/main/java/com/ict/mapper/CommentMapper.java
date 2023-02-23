package com.ict.mapper;

import com.ict.domain.dto.CommentInfo;
import com.ict.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/23/14:07
 */
@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    /**
     * 获取评论列表
     * @param video_id 视频id
     * @return
     */
    List<CommentInfo> getCommentsList(Long video_id);
}