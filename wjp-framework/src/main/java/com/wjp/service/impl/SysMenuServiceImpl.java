package com.wjp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.vo.AdminMenuVo;
import com.wjp.domain.vo.SysMenuVo;
import com.wjp.entity.SysMenu;
import com.wjp.mapper.SysMenuMapper;
import com.wjp.service.SysMenuService;
import com.wjp.util.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangjingpeng
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2023-09-29 16:53:34
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<String> selectMenuPerms(Long id) {
        if (id == 1L)
            return sysMenuMapper.adminPerms();
        List<String> list = sysMenuMapper.selectMenuPerms(id);
        return list;

    }

    @Override
    public List<SysMenuVo> getMenus(Long id) {
        List<SysMenuVo> sysMenuVos = null;
        //如果是管理员，正常的都要要写
        if (id == 1L) {
            sysMenuVos = sysMenuMapper.selectMenuRouter();
        } else {
            sysMenuVos = sysMenuMapper.menuRouter(id);
        }
        return setChildren(sysMenuVos, 0L);
    }

    @Override
    public ResponseResult menuList(String status, String menuName) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(SysMenu::getMenuName, menuName)
                .eq(SysMenu::getStatus, status)
                .eq(SysMenu::getDelFlag, 0)
                .orderByAsc(SysMenu::getParentId, SysMenu::getOrderNum);
        List<SysMenu> list = list(wrapper);
        List<AdminMenuVo> adminMenuVos = BeanCopyUtil.copyBeanList(list, AdminMenuVo.class);
        return ResponseResult.okResult(adminMenuVos);

    }

    @Override
    public ResponseResult addMenu(SysMenu sysMenu) {
        save(sysMenu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getMenu(Long id) {
        SysMenu sysMenu = getById(id);
        AdminMenuVo adminMenuVo = BeanCopyUtil.copyBean(sysMenu, AdminMenuVo.class);
        return ResponseResult.okResult(adminMenuVo);
    }

    @Override
    public ResponseResult updateMenu(SysMenu sysMenu) {
        saveOrUpdate(sysMenu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenu(Long id) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getParentId, id);
        wrapper.eq(SysMenu::getDelFlag, 0);
        List<SysMenu> list = list(wrapper);
        if (list.size() > 0)
            return ResponseResult.errorResult(500, "存在子菜单不允许删除");
        removeById(id);
        return ResponseResult.okResult();
    }

    private List<SysMenuVo> setChildren(List<SysMenuVo> sysMenuVos, Long parentId) {

        List<SysMenuVo> collect = sysMenuVos.stream().filter(m -> m.getParentId().equals(parentId))
                .map(m -> m.setChildren(getChildren(m, sysMenuVos)))
                .collect(Collectors.toList());
        return collect;
    }

    private List<SysMenuVo> getChildren(SysMenuVo menu, List<SysMenuVo> menus) {
        return menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
    }
}




