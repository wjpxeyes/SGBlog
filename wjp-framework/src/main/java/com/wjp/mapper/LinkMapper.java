package com.wjp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjp.entity.Link;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_link(友链)】的数据库操作Mapper
 * @createDate 2023-09-23 22:22:55
 * @Entity com.wjp.entity.Link
 */

@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}




