package com.wjp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjp.domain.vo.SysMenuVo;
import com.wjp.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangjingpeng
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
 * @createDate 2023-09-29 16:53:34
 * @Entity com.wjp.entity.SysMenu
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<Long> getMenuIdList(@Param("id") Long roleId);

    List<String> selectMenuPerms(@Param("id") Long id);

    List<String> adminPerms();


    List<SysMenuVo> selectMenuRouter();

    List<SysMenuVo> menuRouter(Long id);
}




