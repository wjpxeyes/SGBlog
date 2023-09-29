package com.wjp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjp.domain.ResponseResult;
import com.wjp.entity.SysUser;

public interface AdminLoginService extends IService<SysUser> {
    ResponseResult login(SysUser user);

    ResponseResult logout();

    ResponseResult adminInfo();

    ResponseResult routers();
}
