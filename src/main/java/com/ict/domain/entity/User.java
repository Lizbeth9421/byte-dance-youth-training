package com.ict.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private static final long serialVersionUID = 1L;

}