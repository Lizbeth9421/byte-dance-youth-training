package com.ict.domain.model;

import lombok.Data;

import javax.validation.constraints.Max;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/20/22:12
 */
@Data
public class RegisterBody {

    @Max(value = 32, message = "最长32个字符")
    private String username;

    @Max(value = 32, message = "最长32个字符")
    private String password;
}
