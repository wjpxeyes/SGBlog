package com.wjp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjp.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjingpeng
 * @description 针对表【sys_user(用户表)】的数据库操作Mapper
 * @createDate 2023-09-24 10:18:48
 * @Entity com.wjp.entity.SysUser
 */

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}




