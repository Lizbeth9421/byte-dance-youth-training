package com.ict.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ict.domain.dto.VideoInfo;
import com.ict.domain.model.LoginUser;
import com.ict.domain.model.VideoUploadBody;
import com.ict.exception.ServiceException;
import com.ict.oss.service.OssService;
import com.ict.security.TokenService;
import com.ict.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.ict.domain.entity.Video;
import com.ict.mapper.VideoMapper;
import com.ict.service.VideoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.ict.constant.CommonConstants.*;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/21/14:14
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoMapper videoMapper;

    @Autowired
    private OssService ossService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserInfoService infoService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return videoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Video record) {
        return videoMapper.insert(record);
    }

    @Override
    public int insertSelective(Video record) {
        return videoMapper.insertSelective(record);
    }

    @Override
    public Video selectByPrimaryKey(Long id) {
        return videoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Video record) {
        return videoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Video record) {
        return videoMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void contribute(VideoUploadBody uploadBody) {
        //验证令牌
        LoginUser loginUser = verifyLoginUser(uploadBody.getToken());
        String resourceGroup = BASE_UPLOAD_GROUP + VIDEO_UPLOAD_GROUP;
        //上传文件
        String fileUrl = ossService.uploadFile(uploadBody.getData(), resourceGroup);
        Video video = generateVideo(loginUser.getUserId(), fileUrl, uploadBody.getTitle());
        //添加视频信息
        videoMapper.insertSelective(video);
        //更新投稿者信息
        infoService.increaseWorkCountByUserId(loginUser.getUserId());
    }


    private LoginUser verifyLoginUser(String token) {
        LoginUser loginUser = tokenService.getLoginUser(token);
        if (ObjectUtil.isNull(loginUser)) {
            throw new ServiceException("非法访问！");
        }
        tokenService.verifyToken(loginUser);
        return loginUser;
    }

    private Video generateVideo(Long userId, String playUrl, String title) {
        Video video = new Video();
        video.setUserId(userId);
        video.setTitle(title);
        video.setPlayUrl(playUrl);
        video.setCoverUrl(playUrl + ALIYUN_OSS_IMAGE_PARAMS);
        video.setFavoriteCount(0);
        video.setCommentCount(0);
        video.setCreateTime(new Date());
        return video;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VideoInfo> getPublishList(final String token, final Integer userId) {
        return videoMapper.getPublishListByUserId(userId);
    }

    @Override
    public List<VideoInfo> getFeedList(final String token) {
        if (StrUtil.isNotBlank(token)) {
            Long userId = tokenService.getLoginUser(token).getUserId();
            return videoMapper.getFeedList(Math.toIntExact(userId));
        } else {
            return videoMapper.getFeedList(null);
        }

    }

    @Override
    public List<VideoInfo> getVideoInfoByVideoId(final List<Long> list) {
        return videoMapper.getVideoInfoByVideoId(list);
    }

}
