package com.ict.exception;


/**
 * 用户密码不正确或不符合规范异常类
 *
 * @author ruoyi
 */
public class UserPasswordNotMatchException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("用户名或密码错误!", null);
    }
}
