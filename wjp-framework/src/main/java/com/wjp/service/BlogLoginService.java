package com.wjp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjp.domain.ResponseResult;
import com.wjp.entity.SysUser;

/**
 * @author wangjingpeng
 * @description 针对表【sys_user(用户表)】的数据库操作Service
 * @createDate 2023-09-24 10:18:48
 */
public interface BlogLoginService extends IService<SysUser> {

    ResponseResult login(SysUser user);

    ResponseResult logout();
}
