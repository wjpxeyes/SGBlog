package com.wjp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjp.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_comment(评论表)】的数据库操作Mapper
 * @createDate 2023-09-24 17:05:34
 * @Entity com.wjp.entity.Comment
 */

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




