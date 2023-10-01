package com.wjp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.RoleDto;
import com.wjp.domain.vo.ListVo;
import com.wjp.domain.vo.RoleTreeVo;
import com.wjp.domain.vo.RoleVo;
import com.wjp.entity.SysMenu;
import com.wjp.entity.SysRole;
import com.wjp.mapper.SysRoleMapper;
import com.wjp.service.SysMenuService;
import com.wjp.service.SysRoleService;
import com.wjp.util.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public List<String> selectRoles(Long id) {
        if (id == 1L)
            return Collections.singletonList("admin");
        return sysRoleMapper.getRoles(id);
    }

    @Override
    public ResponseResult getRoleList(Integer pageNum, Integer pageSize, String roleName, String status) {
        Page<SysRole> sysRolePage = new Page<>();
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRole::getStatus, status)
                .like(SysRole::getRoleName, roleName)
                .orderByAsc(SysRole::getRoleSort);
        page(sysRolePage, wrapper);
        List<RoleVo> roleVos = BeanCopyUtil.copyBeanList(sysRolePage.getRecords(), RoleVo.class);
        ListVo<RoleVo> listVo = new ListVo<>(sysRolePage.getTotal(), roleVos);
        return ResponseResult.okResult(listVo);
    }

    @Override
    public ResponseResult updateRole(Long roleId, String status) {
        SysRole sysRole = new SysRole();
        sysRole.setId(roleId);
        sysRole.setStatus(status);
        update(sysRole, null);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getRoleTree() {
        List<RoleTreeVo> tree = getTree();
        return ResponseResult.okResult(tree);
    }

    private List<RoleTreeVo> getTree() {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getParentId, 0);
        List<SysMenu> list = sysMenuService.list(wrapper);

        List<SysMenu> sysMenus = sysMenuService.list();
        List<RoleTreeVo> roleTreeVos = BeanCopyUtil.copyBeanList(list, RoleTreeVo.class);
        for (RoleTreeVo roleTreeVo : roleTreeVos) {
            List<SysMenu> collect = sysMenus.stream()
                    .filter(menu -> menu.getParentId().equals(roleTreeVo.getId()))
                    .collect(Collectors.toList());
            List<RoleTreeVo> vos = BeanCopyUtil.copyBeanList(collect, RoleTreeVo.class);
            roleTreeVo.setChildren(vos);
        }
        return roleTreeVos;
    }

    @Override
    public ResponseResult addTree(RoleDto roleDto) {
        SysRole sysRole = BeanCopyUtil.copyBean(roleDto, SysRole.class);
        save(sysRole);
        sysRoleMapper.addRole2Menu(sysRole.getId(), roleDto.getMenuIds());
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getRole(Long id) {
        SysRole sysRole = getById(id);
        RoleVo roleVo = BeanCopyUtil.copyBean(sysRole, RoleVo.class);
        return ResponseResult.okResult(roleVo);
    }

    @Override
    public ResponseResult getRoleTreeById(Long id) {
        List<RoleTreeVo> tree = getTree();
        HashMap<String, Object> map = new HashMap<>();
        map.put("menus", tree);
        List<String> roles = sysRoleMapper.getRoles(id);
        map.put("checkedKeys", roles);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult updateRoleInfo(RoleDto roleDto) {
        SysRole sysRole = BeanCopyUtil.copyBean(roleDto, SysRole.class);
        UpdateWrapper<SysRole> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", roleDto.getId());
        update(sysRole, wrapper);
        sysRoleMapper.deleteRole2Menu(sysRole.getId());
        sysRoleMapper.addRole2Menu(sysRole.getId(), roleDto.getMenuIds());

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteRole(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }


}




