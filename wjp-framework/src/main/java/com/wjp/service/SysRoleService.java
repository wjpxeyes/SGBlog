package com.wjp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.RoleDto;
import com.wjp.entity.SysRole;

import java.util.List;

/**
 * @author wangjingpeng
 * @description 针对表【sys_role(角色信息表)】的数据库操作Service
 * @createDate 2023-09-29 16:53:57
 */
public interface SysRoleService extends IService<SysRole> {

    List<String> selectRoles(Long id);

    ResponseResult getRoleList(Integer pageNum, Integer pageSize, String roleName, String status);

    ResponseResult updateRole(Long roleId, String status);

    ResponseResult getRoleTree();

    ResponseResult addTree(RoleDto roleDto);

    ResponseResult getRole(Long id);

    ResponseResult getRoleTreeById(Long id);

    ResponseResult updateRoleInfo(RoleDto roleDto);

    ResponseResult deleteRole(Long id);
}
