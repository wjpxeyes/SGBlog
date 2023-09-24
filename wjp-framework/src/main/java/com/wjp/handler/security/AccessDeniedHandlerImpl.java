package com.wjp.handler.security;

import com.wjp.domain.AppHttpCodeEnum;
import com.wjp.domain.ResponseResult;
import com.wjp.util.JSONUtils;
import com.wjp.util.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        try {
            WebUtils.renderString(httpServletResponse, JSONUtils.obj2json(result));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
