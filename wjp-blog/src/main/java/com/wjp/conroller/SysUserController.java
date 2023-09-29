package com.wjp.conroller;


import com.wjp.annotation.SystemLog;
import com.wjp.domain.ResponseResult;
import com.wjp.entity.SysUser;
import com.wjp.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;


    @SystemLog(BusinessName = "用户信息查询")
    @GetMapping("/userInfo")
    public ResponseResult userInfo() {
        return sysUserService.userInfo();
    }

    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody SysUser sysUser) {
        return sysUserService.updateUserInfo(sysUser);
    }

    @PostMapping("/register")
    public ResponseResult registerUser(@RequestBody SysUser sysUser) {
        return sysUserService.registerUser(sysUser);
    }
}
