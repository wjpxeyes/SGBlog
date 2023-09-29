package com.wjp.conroller;


import com.wjp.domain.ResponseResult;
import com.wjp.entity.Comment;
import com.wjp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {

        return commentService.getCommentList("0", articleId, pageNum, pageSize);
    }

    @PostMapping
    public ResponseResult comment(@RequestBody Comment comment) {
        return commentService.comment(comment);
    }

    @GetMapping("linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.getCommentList("1", 1L, pageNum, pageSize);


    }

}
