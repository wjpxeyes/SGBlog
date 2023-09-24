package com.wjp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.entity.SysUser;
import com.wjp.mapper.SysUserMapper;
import com.wjp.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @author wangjingpeng
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2023-09-24 10:18:48
 */


@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

}




