package com.wjp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.vo.SysMenuVo;
import com.wjp.entity.SysMenu;

import java.util.List;

/**
 * @author wangjingpeng
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
 * @createDate 2023-09-29 16:53:34
 */
public interface SysMenuService extends IService<SysMenu> {

    List<String> selectMenuPerms(Long id);

    List<SysMenuVo> getMenus(Long id);

    ResponseResult menuList(String status, String menuName);

    ResponseResult addMenu(SysMenu sysMenu);

    ResponseResult getMenu(Long id);

    ResponseResult updateMenu(SysMenu sysMenu);

    ResponseResult deleteMenu(Long id);
}
