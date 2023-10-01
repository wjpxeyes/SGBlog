package com.wjp.controller;


import com.wjp.domain.ResponseResult;
import com.wjp.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/list")
    public ResponseResult userList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        return sysUserService.userList(pageNum, pageSize, userName, phonenumber, status);
    }

    @PostMapping
    public ResponseResult addUser() {
        return null;
    }

}
