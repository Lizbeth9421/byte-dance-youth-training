package com.ict.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ict.domain.entity.User;
import com.ict.domain.model.LoginUser;
import com.ict.exception.ServiceException;
import com.ict.exception.UserPasswordNotMatchException;
import com.ict.security.AuthenticationContextHolder;
import com.ict.security.SecurityConfig;
import com.ict.service.UserService;
import com.ict.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/11/16:20
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectByUserName(username);
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
        //validate(user);
        return new LoginUser(user.getUserId(), username, user);
    }

    /**
     * 匹配密码
     *
     * @param user 数据库中的user信息
     * @return
     */
    private boolean validate(User user) {
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();
        boolean res = SecurityUtil.matchesPassword(password, user.getPassword());
        if (!res) {
            throw new UserPasswordNotMatchException();
        }
        return true;
    }
}
