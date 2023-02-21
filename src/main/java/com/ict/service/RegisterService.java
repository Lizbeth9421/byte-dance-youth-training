package com.ict.service;

import com.ict.domain.AjaxResult;
import com.ict.domain.model.RegisterBody;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/20/22:14
 */
public interface RegisterService {

    /**
     * 注册用户
     *
     * @param registerBody
     * @return
     */
    AjaxResult register(RegisterBody registerBody);

}
