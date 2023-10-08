package com.wjp.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.UserDto;
import com.wjp.domain.dto.UserStatusDto;
import com.wjp.domain.vo.AdminUserVo;
import com.wjp.domain.vo.ListVo;
import com.wjp.domain.vo.UserInfoVo;
import com.wjp.domain.vo.UserRoleInfoVo;
import com.wjp.entity.SysRole;
import com.wjp.entity.SysUser;
import com.wjp.mapper.SysUserMapper;
import com.wjp.service.SysRoleService;
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
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleService sysRoleService;

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

    //后台获取用户列表
    @Override
    public ResponseResult userList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        Page<SysUser> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(userName), SysUser::getNickName, userName)
                .like(StrUtil.isNotBlank(phonenumber), SysUser::getPhonenumber, phonenumber)
                .eq(StrUtil.isNotBlank(status), SysUser::getStatus, status);
        page(page, wrapper);
        List<SysUser> records = page.getRecords();
        List<AdminUserVo> adminUserVos = BeanCopyUtil.copyBeanList(records, AdminUserVo.class);
        return ResponseResult.okResult(new ListVo<>(page.getTotal(), adminUserVos));
    }

    @Override
    public ResponseResult addUser(UserDto userDto) {
        SysUser sysUser = BeanCopyUtil.copyBean(userDto, SysUser.class);
        save(sysUser);
        Long id = sysUser.getId();
        sysUserMapper.addUserRoles(id, userDto.getRoleIds());
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteUser(Long id) {
        Long userId = SecurityUtils.getUserId();
        if (userId.equals(id))
            return ResponseResult.errorResult(404, "不能删除当前用户");
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUserRoleInfo(Long id) {
        List<Long> roleIds = sysUserMapper.getUserRoles(id);
        List<SysRole> roleList = sysRoleService.list();
        UserRoleInfoVo infoVo = new UserRoleInfoVo(roleIds, roleList);
        return ResponseResult.okResult(infoVo);
    }

    @Override
    public ResponseResult changeUserStatus(UserStatusDto statusDto) {
        SysUser sysUser = new SysUser();
        sysUser.setStatus(statusDto.getStatus());
        sysUser.setId(statusDto.getUserId());
        updateById(sysUser);
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
