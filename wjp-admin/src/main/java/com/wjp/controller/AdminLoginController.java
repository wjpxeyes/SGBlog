package com.wjp.controller;


import com.wjp.domain.ResponseResult;
import com.wjp.entity.SysUser;
import com.wjp.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;

    @PostMapping("/user/login")
    public ResponseResult adminLogin(@RequestBody SysUser sysUser) {
        return adminLoginService.login(sysUser);
    }

    @GetMapping("getInfo")
    public ResponseResult AdminInfo() {
        return adminLoginService.adminInfo();
    }

    @GetMapping("/getRouters")
    public ResponseResult routers() {
        return adminLoginService.routers();
    }

    @PostMapping("/user/logout")
    public ResponseResult logout() {
        return adminLoginService.logout();
    }
}
