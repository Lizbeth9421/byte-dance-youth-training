package com.ict.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ict.domain.entity.UserInfo;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/23/12:24
 */
@Data
public class CommentInfo {

    private Long id;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private UserInfo user;
}
