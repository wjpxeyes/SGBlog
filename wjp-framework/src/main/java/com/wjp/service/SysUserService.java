package com.wjp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.UserDto;
import com.wjp.domain.dto.UserStatusDto;
import com.wjp.entity.SysUser;


public interface SysUserService extends IService<SysUser> {
    ResponseResult userInfo();

    ResponseResult updateUserInfo(SysUser sysUser);

    ResponseResult registerUser(SysUser sysUser);

    ResponseResult userList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status);

    ResponseResult addUser(UserDto userDto);

    ResponseResult deleteUser(Long id);

    ResponseResult getUserRoleInfo(Long id);

    ResponseResult changeUserStatus(UserStatusDto statusDto);
}
