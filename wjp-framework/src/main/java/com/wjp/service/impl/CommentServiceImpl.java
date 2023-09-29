package com.wjp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.vo.CommentListVo;
import com.wjp.domain.vo.CommentVo;
import com.wjp.entity.Comment;
import com.wjp.entity.SysUser;
import com.wjp.mapper.CommentMapper;
import com.wjp.mapper.SysUserMapper;
import com.wjp.service.CommentService;
import com.wjp.util.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_comment(评论表)】的数据库操作Service实现
 * @createDate 2023-09-24 17:05:34
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public ResponseResult getCommentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getArticleId, articleId);
        wrapper.eq(articleId != null, Comment::getRootId, -1);
        wrapper.eq(Comment::getType, commentType);
        Page<Comment> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        List<CommentVo> commentVos = toCommentVoList(page.getRecords());
        //查询所有根评论对应的子评论集合，并且赋值给对应的属性
        for (CommentVo commentVo : commentVos) {
            //查询对应的子评论
            List<CommentVo> children = getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children);
        }
        CommentListVo commentListVo = new CommentListVo((int) page.getTotal(), commentVos);

        return ResponseResult.okResult(commentListVo);
    }

    @Override
    public ResponseResult comment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())) {
            throw new RuntimeException("评论不能为空");
        }
        save(comment);
        return ResponseResult.okResult();
    }

    private List<CommentVo> getChildren(Long id) {
        Comment comment = getById(id);
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper);
        return toCommentVoList(comments);
    }

    private List<CommentVo> toCommentVoList(List<Comment> comments) {
        List<CommentVo> commentVos = BeanCopyUtil.copyBeanList(comments, CommentVo.class);
        for (CommentVo commentVo : commentVos) {
            SysUser sysUser = sysUserMapper.selectById(commentVo.getCreateBy());
            commentVo.setUsername(sysUser.getNickName());
            if (commentVo.getToCommentUserId() != -1) {
                SysUser sysUser1 = sysUserMapper.selectById(commentVo.getToCommentUserId());
                commentVo.setToCommentUserName(sysUser1.getNickName());
            }

        }

        return commentVos;

    }
}




