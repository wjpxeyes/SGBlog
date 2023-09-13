package com.wjp.conroller;


import com.wjp.entity.Article;
import com.wjp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    @RequestMapping("/article")
    public List<Article> getArticleList() {
        return articleService.list();
    }
}
