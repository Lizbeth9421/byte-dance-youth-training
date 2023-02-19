package com.ict.domain.model;

import lombok.Data;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/18/19:55
 */
@Data
public class LoginBody {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
