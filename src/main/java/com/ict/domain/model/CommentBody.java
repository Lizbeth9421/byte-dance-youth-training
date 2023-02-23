package com.ict.domain.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/23/12:22
 */
@Data
public class CommentBody {
    @NotBlank(message = "token不能为空")
    private String token;

    @NotBlank(message = "视频信息不能为空")
    private String video_id;

    @NotBlank(message = "操作类型不能为空")
    private String action_type;

    /**
     * 用户填写的评论内容，在action_type=1的时候使用
     */
    private String comment_text;

    /**
     * 要删除的评论id，在action_type=2的时候使用
     */
    private String comment_id;

}
