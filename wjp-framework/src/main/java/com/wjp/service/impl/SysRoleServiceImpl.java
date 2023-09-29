package com.wjp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.entity.SysRole;
import com.wjp.mapper.SysRoleMapper;
import com.wjp.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author wangjingpeng
 * @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
 * @createDate 2023-09-29 16:53:57
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<String> selectRoles(Long id) {
        if (id == 1L)
            return Collections.singletonList("admin");
        return sysRoleMapper.getRoles(id);
    }
}




