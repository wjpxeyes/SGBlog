package com.wjp.controller;


import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.RoleDto;
import com.wjp.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/list")
    public ResponseResult getRoleList(Integer pageNum, Integer pageSize, String roleName, String status) {
        return sysRoleService.getRoleList(pageNum, pageSize, roleName, status);
    }

    @PutMapping("/changeStatus")
    public ResponseResult updateRole(@RequestBody Long roleId, String status) {
        return sysRoleService.updateRole(roleId, status);
    }
    //获取菜单树接口

    @GetMapping("/treeselect")
    public ResponseResult getRoleTree() {
        return sysRoleService.getRoleTree();
    }

    @PostMapping
    public ResponseResult addTree(@RequestBody RoleDto roleDto) {
        return sysRoleService.addTree(roleDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getRole(@PathVariable("id") Long id) {
        return sysRoleService.getRole(id);
    }

    @GetMapping("/roleMenuTreeselect/{id}")
    public ResponseResult getRoleTreeById(@PathVariable("id") Long id) {
        return sysRoleService.getRoleTreeById(id);
    }

    @PutMapping
    public ResponseResult updateRoleInfo(@RequestBody RoleDto roleDto) {
        return sysRoleService.updateRoleInfo(roleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteRole(@PathVariable("id") Long id) {
        return sysRoleService.deleteRole(id);
    }

    @GetMapping("/listAllRole")
    public ResponseResult getUserRoleList() {
        return sysRoleService.getUserRoleList();
    }
}
