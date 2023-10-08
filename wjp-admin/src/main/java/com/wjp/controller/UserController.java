package com.wjp.controller;


import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.UserDto;
import com.wjp.domain.dto.UserStatusDto;
import com.wjp.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseResult addUser(@RequestBody UserDto userDto) {
        return sysUserService.addUser(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable("id") Long id) {
        return sysUserService.deleteUser(id);
    }

    @GetMapping("{id}")
    public ResponseResult getUserRoleInfo(@PathVariable("id") Long id) {
        return sysUserService.getUserRoleInfo(id);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeUserStatus(@RequestBody UserStatusDto statusDto) {
        return sysUserService.changeUserStatus(statusDto);
    }
}
