package com.ict.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/18/16:43
 */
@Component
@ConfigurationProperties(prefix = "dousheng.jwt")
public class JwtInformation {

    /**
     * 过期时间 天
     */
    private long expire;

    /**
     * 密钥
     */
    private String secret;

    /**
     * 请求头
     */
    private String header;

    public long getExpire() {
        return expire;
    }

    public void setExpire(final long expire) {
        this.expire = expire;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(final String secret) {
        this.secret = secret;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(final String header) {
        this.header = header;
    }
}
