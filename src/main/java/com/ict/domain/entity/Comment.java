package com.ict.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/23/14:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private Long id;

    /**
     * 视频id
     */
    private Long videoId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    private static final long serialVersionUID = 1L;
}