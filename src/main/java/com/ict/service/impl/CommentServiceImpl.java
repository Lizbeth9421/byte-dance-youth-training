package com.ict.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.ict.domain.dto.CommentInfo;
import com.ict.domain.entity.UserInfo;
import com.ict.domain.model.CommentBody;
import com.ict.domain.model.LoginUser;
import com.ict.exception.ServiceException;
import com.ict.security.TokenService;
import com.ict.service.UserInfoService;
import com.ict.service.VideoService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.ict.domain.entity.Comment;
import com.ict.mapper.CommentMapper;
import com.ict.service.CommentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.jsf.FacesContextUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/23/9:55
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private UserInfoService infoService;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Comment record) {
        return commentMapper.insert(record);
    }

    @Override
    public int insertSelective(Comment record) {
        return commentMapper.insertSelective(record);
    }

    @Override
    public Comment selectByPrimaryKey(Long id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return commentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        return commentMapper.updateByPrimaryKey(record);
    }

    @Override
    public CommentInfo comment(CommentBody commentBody) {
        LoginUser loginUser = tokenService.getLoginUser(commentBody.getToken());
        Long videoId = Long.valueOf(commentBody.getVideo_id());
        assert loginUser != null;
        CommentService proxy = (CommentService) AopContext.currentProxy();
        //判断是写评论还是删除评论
        if (Integer.valueOf(commentBody.getAction_type()).equals(1)) {
            return proxy.tokeComment(loginUser.getUserId(), videoId, commentBody.getComment_text());
        } else {
            return proxy.deleteComment(loginUser.getUserId(), videoId, Long.valueOf(commentBody.getComment_id()));
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public CommentInfo tokeComment(final Long userId, final Long videoId, final String comment_text) {
        //评论表插入信息
        Comment comment = generateComment(videoId, userId, comment_text);
        commentMapper.insertSelective(comment);
        //视频表中的评论总数+1
        videoService.increaseCommentCountByVideoId(videoId);
        //返回评论信息
        return generateCommentInfo(comment.getId(), infoService.getUserInfo(userId), comment_text, comment.getCreateDate());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public CommentInfo deleteComment(final Long userId, final Long videoId, Long comment_id) {
        //查询要删除的评论消息
        Comment comment = commentMapper.selectByPrimaryKey(comment_id);
        //判断该评论是否是当前用户发布的
        if (!userId.equals(comment.getUserId())) {
            throw new ServiceException("你不能删除别人的评论！");
        }
        //删除评论表信息
        commentMapper.deleteByPrimaryKey(comment_id);
        //视频表中的评论总数-1
        videoService.decreaseCommentCountByVideoId(videoId);
        //返回删除评论信息
        return generateCommentInfo(comment_id, infoService.getUserInfo(userId), comment.getContent(), comment.getCreateDate());
    }

    @Override
    public List<CommentInfo> getCommentsList(final Long video_id) {
        return commentMapper.getCommentsList(video_id);
    }

    private Comment generateComment(Long videoId, Long userId, String content) {
        Comment comment = new Comment();
        comment.setVideoId(videoId);
        comment.setUserId(userId);
        comment.setContent(content);
        comment.setCreateDate(new Date());
        return comment;
    }

    private CommentInfo generateCommentInfo(Long commentId, UserInfo userInfo, String content, Date create_date) {
        CommentInfo info = new CommentInfo();
        info.setId(commentId);
        info.setUser(userInfo);
        info.setContent(content);
        //info.setCreate_date(DateUtil.format(create_date, "yyyy-MM-dd HH:mm:ss"));
        info.setCreateDate(create_date);
        return info;
    }

}

