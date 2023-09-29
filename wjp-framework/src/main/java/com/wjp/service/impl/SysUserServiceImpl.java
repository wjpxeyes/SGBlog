package com.wjp.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.vo.UserInfoVo;
import com.wjp.entity.SysUser;
import com.wjp.mapper.SysUserMapper;
import com.wjp.service.SysUserService;
import com.wjp.util.BeanCopyUtil;
import com.wjp.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult userInfo() {
        Long userId = SecurityUtils.getUserId();
        SysUser sysUser = getById(userId);
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(sysUser, UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(SysUser sysUser) {
        updateById(sysUser);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult registerUser(SysUser sysUser) {
        if (!Validator.isEmail(sysUser.getEmail())) {
            throw new RuntimeException("异常1");
        }
        if (StrUtil.isBlank(sysUser.getUserName())) {
            throw new RuntimeException("异常2");
        }
        if (StrUtil.isBlank(sysUser.getNickName())) {
            throw new RuntimeException("异常3");
        }
        if (StrUtil.isBlank(sysUser.getPassword())) {
            throw new RuntimeException("异常4");
        }
        if (userInfoExist(sysUser)) {
            throw new RuntimeException("异常5");
        }
        //对密码进行加密
        String encode = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(encode);
        save(sysUser);
        return ResponseResult.okResult();
    }

    private boolean userInfoExist(SysUser sysUser) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName, sysUser.getUserName())
                .or()
                .eq(SysUser::getEmail, sysUser.getEmail())
                .or()
                .eq(SysUser::getNickName, sysUser.getNickName());
        List<SysUser> sysUserList = list(wrapper);
        return sysUserList.size() != 0;
    }
}
