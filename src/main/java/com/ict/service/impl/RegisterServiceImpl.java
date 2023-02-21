package com.ict.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.ict.domain.AjaxResult;
import com.ict.domain.entity.User;
import com.ict.domain.entity.UserInfo;
import com.ict.domain.model.LoginBody;
import com.ict.domain.model.RegisterBody;
import com.ict.service.LoginService;
import com.ict.service.RegisterService;
import com.ict.service.UserInfoService;
import com.ict.service.UserService;
import com.ict.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.ict.constant.CommonConstants.USER_PREFIX;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/20/22:19
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private LoginService loginService;


    @Resource
    private UserService userService;

    @Resource
    private UserInfoService infoService;


    @Override
    public AjaxResult register(final RegisterBody registerBody) {
        String username = registerBody.getUsername();
        String password = registerBody.getPassword();
        //加密后的密码
        String encryptPassword = SecurityUtil.encryptPassword(password);
        User user = generateUser(username, encryptPassword);
        userService.insertSelective(user);
        UserInfo userInfo = generateUserInfo(user.getUserId());
        infoService.insertSelective(userInfo);
        return loginService.login(new LoginBody(username, password));
    }


    private User generateUser(String username, String password) {
        return new User(username, password, new Date(), new Date());
    }

    private UserInfo generateUserInfo(Long userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setName(USER_PREFIX + RandomUtil.randomString(10));
        userInfo.setFollowCount(0);
        userInfo.setFollowerCount(0);
        userInfo.setIsFollow(false);
        // TODO: 2023/2/20 设置默认图片
        userInfo.setAvatar("");
        userInfo.setBackgroundImage("");
        userInfo.setSignature("这个人很懒，什么都没留下！");
        userInfo.setTotalFavorited(0);
        userInfo.setWorkCount(0);
        userInfo.setFavoriteCount(0);
        return userInfo;
    }
}
