package com.wjp.runner;

import com.wjp.entity.Article;
import com.wjp.mapper.ArticleMapper;
import com.wjp.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class RedisRunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        writeViewCount();
    }

    private void writeViewCount() {
        List<Article> articleList = articleMapper.selectList(null);
        Map<String, Integer> map = articleList.stream().collect(Collectors.toMap(article -> article.getId().toString(),
                article -> article.getViewCount().intValue()));
        redisCache.setCacheMap("article:viewCount", map);
    }
}
