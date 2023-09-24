package com.wjp.conroller;


import com.wjp.domain.ResponseResult;
import com.wjp.entity.SysUser;
import com.wjp.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody SysUser user) {
        return blogLoginService.login(user);
    }
}