package com.ict.domain.dto;

import com.ict.domain.entity.UserInfo;
import lombok.Data;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/23/12:24
 */
@Data
public class CommentInfo {
    private UserInfo user;

    private Long id;

    private String content;

    private String create_date;
}
