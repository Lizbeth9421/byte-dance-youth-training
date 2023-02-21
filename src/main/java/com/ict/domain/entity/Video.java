package com.ict.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/21/14:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video implements Serializable {
    private Long id;

    /**
     * 作责id
     */
    private Long userId;

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
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}