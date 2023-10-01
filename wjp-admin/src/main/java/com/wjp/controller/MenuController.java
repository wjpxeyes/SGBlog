package com.wjp.controller;


import com.wjp.domain.ResponseResult;
import com.wjp.entity.SysMenu;
import com.wjp.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("system/menu")
public class MenuController {
    @Autowired
    SysMenuService sysMenuService;

    @GetMapping("/list")
    public ResponseResult menuList(String status, String menuName) {
        return sysMenuService.menuList(status, menuName);
    }

    @PostMapping
    public ResponseResult addMenu(SysMenu sysMenu) {
        return sysMenuService.addMenu(sysMenu);
    }

    @GetMapping("/{id}")
    public ResponseResult getMenu(@PathVariable("id") Long id) {
        return sysMenuService.getMenu(id);
    }

    @PutMapping
    public ResponseResult updateMenu(SysMenu sysMenu) {
        if (sysMenu.getParentId() == sysMenu.getId())
            return ResponseResult.errorResult(500, "修改菜单'写博文'失败，上级菜单不能选择自己");
        return sysMenuService.updateMenu(sysMenu);
    }

    @DeleteMapping("/{menuId}")
    public ResponseResult deleteMenu(@PathVariable("menuId") Long id) {
        return sysMenuService.deleteMenu(id);
    }


}
