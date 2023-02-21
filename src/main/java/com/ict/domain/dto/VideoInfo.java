package com.ict.domain.dto;

import com.ict.domain.entity.UserInfo;
import lombok.Data;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/21/19:57
 */
@Data
public class VideoInfo {

    private Long id;

    /**
     * 作者信息
     */
    private UserInfo author;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频播放地址
     */
    private String playUrl;

    /**
     * 视频封面地址
     */
    private String coverUrl;

    /**
     * 视频的点赞总数
     */
    private Integer favoriteCount;

    /**
     * 视频的评论总数
     */
    private Integer commentCount;


    /**
     * 当前用户是否点赞
     */
    private boolean isFavorite;

}
