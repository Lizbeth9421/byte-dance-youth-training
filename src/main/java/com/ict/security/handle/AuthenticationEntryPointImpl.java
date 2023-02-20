package com.ict.security.handle;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson2.JSON;
import com.ict.domain.AjaxResult;
import com.ict.util.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @Author: Lizbeth9421
 * @Date: 2023/02/18/22:16
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
                         final AuthenticationException e) throws IOException, ServletException {
        String msg = StrUtil.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.HTTP_UNAUTHORIZED, msg)));
    }
}
