package com.ict.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/11/16:04
 */
@Data
public class User implements Serializable{
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JSONField(serialize = false)
    private String password;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public User() {
    }

    public User(final String username, final String password, final Date createTime, final Date updateTime) {
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    private static final long serialVersionUID = 1L;

}