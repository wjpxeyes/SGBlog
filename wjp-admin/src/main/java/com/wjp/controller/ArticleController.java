package com.wjp.controller;


import com.wjp.domain.ResponseResult;
import com.wjp.domain.vo.AdminArticleInfoVo;
import com.wjp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    @GetMapping("/hotArticleList")
    public ResponseResult getHotArticleList() {
        return articleService.getHotArticleList();
    }


    //后台获取文章列表
    @GetMapping("/list")
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, String title, String summary) {
        return articleService.getArticleList(pageNum, pageSize, title, summary);
    }

    //后台查询文章详细信息
    @GetMapping("/{id}")
    public ResponseResult getArticleInfo(@PathVariable("id") Long id) {
        return articleService.getArticleInfo(id);
    }

    //更新文章列表
    @PutMapping
    public ResponseResult updateViewCount(@RequestBody AdminArticleInfoVo articleInfoVo) {
        return articleService.updateArticle(articleInfoVo);
    }

    //删除文章，逻辑删除
    @GetMapping("/{id}")
    public ResponseResult deleteArticle(@PathVariable("id") Long id) {
        return articleService.deleteArticle(id);
    }
}
