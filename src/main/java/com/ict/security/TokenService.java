package com.ict.security;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.ict.constant.CommonConstants;
import com.ict.constant.RedisConstants;
import com.ict.domain.model.LoginUser;
import com.ict.util.IpUtil;
import com.ict.util.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/11/17:33
 */
@Component
@Slf4j
public class TokenService {

    /**
     * 1s
     */
    private static final long MILLIS_SECOND = 1000;

    /**
     * 1分钟
     */
    private static final long MILLIS_MINUTE = MILLIS_SECOND * 60;

    /**
     * 1小时
     */
    private static final long MILLIS_HOUR = MILLIS_SECOND * 60;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;


    //@Autowired
    //private JwtInformation jwtInformation;

    //long expire = jwtInformation.getExpire();
    //
    //String secret = jwtInformation.getSecret();
    //
    //String header = jwtInformation.getHeader();


    long expire = 1;

    String secret = "Lizbeth9421";

    String header = "Authorization";

    /**
     * 获取当前登录用户的信息
     *
     * @param request 请求对象
     * @return
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        //获取请求中的token
        String token = this.getToken(request);
        if (StrUtil.isNotBlank(token)) {
            try {
                Claims claims = this.parseToken(token);
                String uuid = (String) claims.get(CommonConstants.LOGIN_USER_KEY);
                String userKey = getTokenKey(uuid);
                return (LoginUser) redisTemplate.opsForValue().get(userKey);
            } catch (Exception e) {
                log.error("解析令牌失败!");
            }
        }
        return null;
    }

    /**
     * 生成令牌
     *
     * @param loginUser 登录用户
     * @return
     */
    public String createToken(LoginUser loginUser) {
        String token = IdUtil.fastSimpleUUID();
        loginUser.setToken(token);
        this.setUserAgent(loginUser);
        this.refreshToken(loginUser);
        Map<String, Object> claims = new HashMap<>(1);
        claims.put(CommonConstants.LOGIN_USER_KEY, token);
        return this.createJwt(claims);
    }

    /**
     * 验证令牌有效期，相差不足1小时，自动刷新缓存
     *
     * @param loginUser
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_HOUR) {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String token) {
        if (StrUtil.isNotBlank(token)) {
            redisTemplate.delete(getTokenKey(token));
        }
    }

    /**
     * 生成jwt
     *
     * @param claims 数据声明
     * @return
     */
    public String createJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        //设置过期时间
        loginUser.setExpireTime(loginUser.getLoginTime() + expire * MILLIS_HOUR);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        //设置到redis中
        redisTemplate.opsForValue().set(userKey, loginUser, expire, TimeUnit.HOURS);
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    public void setUserAgent(LoginUser loginUser) {
        HttpServletRequest request = ServletUtils.getRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        String ip = IpUtil.getIP(request);
        loginUser.setIpaddr(ip);
        loginUser.setLoginLocation(IpUtil.formatAddress(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 根据uuid获取当前登录用户在redis的缓存key
     *
     * @param uuid
     * @return
     */
    private String getTokenKey(final String uuid) {
        return RedisConstants.LOGIN_TOKEN_KEY + uuid;
    }


    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * 获取请求中的token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getParameter(header);
        //先在获取请求参数，没有就在请求头获取
        return StrUtil.isNotBlank(token) ? token : request.getHeader(header);
    }

}
