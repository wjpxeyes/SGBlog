package com.wjp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjp.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangjingpeng
 * @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
 * @createDate 2023-09-29 16:53:57
 * @Entity com.wjp.entity.SysRole
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<String> getRoles(@Param("id") Long id);

    void addRole2Menu(@Param("id") Long id, @Param("list") List<Long> menuIds);

    void deleteRole2Menu(Long id);
}




