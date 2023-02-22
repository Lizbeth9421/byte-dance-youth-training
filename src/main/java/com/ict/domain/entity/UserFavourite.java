package com.ict.domain.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/22/15:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFavourite implements Serializable {
    /**
    * 用户id
    */
    private Long userId;

    /**
    * 用户点赞的视频id
    */
    private Long videoId;

    private static final long serialVersionUID = 1L;
}