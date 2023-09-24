package com.wjp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.vo.BlogLoginVo;
import com.wjp.domain.vo.UserInfoVo;
import com.wjp.entity.LoginUser;
import com.wjp.entity.SysUser;
import com.wjp.mapper.SysUserMapper;
import com.wjp.service.BlogLoginService;
import com.wjp.util.BeanCopyUtil;
import com.wjp.util.JwtUtil;
import com.wjp.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wangjingpeng
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2023-09-24 10:18:48
 */


@Service
public class BlogLoginServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(SysUser user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(token);
        if (authenticate == null)
            throw new RuntimeException("用户名或密码错误");
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getSysUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        redisCache.setCacheObject("blogLogin:" + userId, loginUser.getSysUser(), 3, TimeUnit.DAYS);
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(loginUser.getSysUser(), UserInfoVo.class);
        BlogLoginVo blogLoginVo = new BlogLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(blogLoginVo);

    }

    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getSysUser().getId();
        redisCache.deleteObject("blogLogin:" + id);
        return ResponseResult.okResult();
    }
}




