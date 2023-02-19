package com.ict.controller;

import com.ict.domain.AjaxResult;
import com.ict.domain.model.LoginBody;
import com.ict.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/18/22:58
 */
@RestController
@RequestMapping("/douyin/user")
public class UserController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login/")
    public AjaxResult login(LoginBody loginBody) {
        return loginService.login(loginBody);
    }

    @GetMapping("/")
    public AjaxResult userInfo(@RequestParam("user_id") Integer user_id,
                               @RequestParam("token") String token) {
        return AjaxResult.success();
    }
}
