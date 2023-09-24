package com.wjp.handler.security;

import com.wjp.domain.AppHttpCodeEnum;
import com.wjp.domain.ResponseResult;
import com.wjp.util.JSONUtils;
import com.wjp.util.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        try {
            WebUtils.renderString(httpServletResponse, JSONUtils.obj2json(result));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
