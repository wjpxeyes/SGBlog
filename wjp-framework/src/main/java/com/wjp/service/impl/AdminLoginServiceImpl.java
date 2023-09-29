package com.wjp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.vo.AdminInfoVo;
import com.wjp.domain.vo.RoutersVo;
import com.wjp.domain.vo.SysMenuVo;
import com.wjp.domain.vo.UserInfoVo;
import com.wjp.entity.LoginUser;
import com.wjp.entity.SysUser;
import com.wjp.mapper.SysUserMapper;
import com.wjp.service.AdminLoginService;
import com.wjp.service.SysMenuService;
import com.wjp.service.SysRoleService;
import com.wjp.util.BeanCopyUtil;
import com.wjp.util.JwtUtil;
import com.wjp.util.RedisCache;
import com.wjp.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AdminLoginServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements AdminLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(SysUser user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(token);
        if (authenticate == null)
            throw new RuntimeException("用户名或密码错误");
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getSysUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        redisCache.setCacheObject("adminLogin:" + userId, loginUser.getSysUser(), 3, TimeUnit.DAYS);
        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseResult.okResult(map);

    }

    @Override
    public ResponseResult logout() {
        Long userId = SecurityUtils.getUserId();
        redisCache.deleteObject("adminLogin:" + userId);
        return ResponseResult.okResult();
    }

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public ResponseResult adminInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long id = loginUser.getSysUser().getId();
        List<String> role = sysRoleService.selectRoles(id);
        List<String> menuPerms = sysMenuService.selectMenuPerms(id);
        SysUser sysUser = loginUser.getSysUser();
        UserInfoVo userInfoVo = BeanCopyUtil.copyBean(sysUser, UserInfoVo.class);
        AdminInfoVo adminInfoVo = new AdminInfoVo(menuPerms, role, userInfoVo);
        return ResponseResult.okResult(adminInfoVo);
    }

    @Override
    public ResponseResult routers() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long id = loginUser.getSysUser().getId();
        List<SysMenuVo> sysMenuVos = sysMenuService.getMenus(id);
        RoutersVo routersVo = new RoutersVo(sysMenuVos);
        return ResponseResult.okResult(routersVo);
    }


}
