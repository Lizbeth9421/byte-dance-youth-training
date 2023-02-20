package com.ict.domain.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/19/22:54
 */
@Data
public class UserInfo implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 关注总数
     */
    private Integer followCount;

    /**
     * 粉丝总数
     */
    private Integer followerCount;

    /**
     * 是否关注
     */
    private Byte isFollow;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户个人页顶部大图
     */
    private String backgroundImage;

    /**
     * 个人简介
     */
    private String signature;

    /**
     * 获赞数
     */
    private Integer totalFavorited;

    /**
     * 作品数
     */
    private Integer workCount;

    /**
     * 喜欢数
     */
    private Integer favoriteCount;

    public UserInfo() {
    }

    private static final long serialVersionUID = 3911255650485738676L;
}