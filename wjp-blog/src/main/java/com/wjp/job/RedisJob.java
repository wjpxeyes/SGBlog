package com.wjp.job;


import com.wjp.entity.Article;
import com.wjp.service.ArticleService;
import com.wjp.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RedisJob {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0/40 * * * * ?")
    public void redis2mysql() {
        Map<String, Integer> map = redisCache.getCacheMap("article:viewCount");
        List<Article> collect = map.entrySet().stream().map(entry ->
                new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue())
        ).collect(Collectors.toList());
        articleService.updateBatchById(collect);

    }
}
