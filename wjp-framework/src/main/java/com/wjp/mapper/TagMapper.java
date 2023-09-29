package com.wjp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjp.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_tag(标签)】的数据库操作Mapper
 * @createDate 2023-09-29 14:49:30
 * @Entity com.wjp.entity.Tag
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}




