package com.ict.service;

import com.ict.domain.AjaxResult;
import com.ict.domain.model.LoginBody;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/18/20:59
 */
public interface LoginService {
    /**
     * 登录方法
     *
     * @param loginBody
     * @return
     */
    AjaxResult login(LoginBody loginBody);
}
