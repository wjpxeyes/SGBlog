package com.wjp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjp.domain.ResponseResult;
import com.wjp.entity.Comment;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_comment(评论表)】的数据库操作Service
 * @createDate 2023-09-24 17:05:34
 */
public interface CommentService extends IService<Comment> {

    ResponseResult getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult comment(Comment comment);
}
