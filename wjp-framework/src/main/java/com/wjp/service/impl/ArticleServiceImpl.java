package com.wjp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.constants.SystemConstants;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.vo.ArticleListVo;
import com.wjp.domain.vo.ArticleVo;
import com.wjp.domain.vo.HotArticleVo;
import com.wjp.entity.Article;
import com.wjp.entity.ArticleList;
import com.wjp.mapper.ArticleMapper;
import com.wjp.service.ArticleService;
import com.wjp.util.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_article(文章表)】的数据库操作Service实现
 * @createDate 2023-09-13 10:11:21
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public ResponseResult getHotArticleList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        wrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(SystemConstants.HOT_ARTICLE_PAGE, SystemConstants.HOT_ARTICLE_PAGE_SIZE);
        page(page, wrapper);
        List<Article> articleList = page.getRecords();
        List<HotArticleVo> hotArticleVos = BeanCopyUtil.copyBeanList(articleList, HotArticleVo.class);
        return ResponseResult.okResult(hotArticleVos);
    }

    @Override
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, Long categoryId) {
        if (categoryId == 0) {
            List<ArticleList> articleList = articleMapper.getArticleList(pageNum - 1, pageSize, 0L);
            ArticleListVo articleListVo = new ArticleListVo(articleList.size(), articleList);
            return ResponseResult.okResult(articleListVo);
        }
        List<ArticleList> articleList = articleMapper.getArticleList(pageNum - 1, pageSize, categoryId);
        ArticleListVo articleListVo = new ArticleListVo(articleList.size(), articleList);
        return ResponseResult.okResult(articleListVo);
    }

    @Override
    public ResponseResult getArticle(Long id) {
        ArticleVo article = articleMapper.getArticle(id);
        Integer viewCount = (Integer) redisTemplate.opsForHash().get("article:viewCount", id.toString());
        return ResponseResult.okResult(article);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        redisTemplate.boundHashOps("article:viewCount").increment(id.toString(), 1L);
        return ResponseResult.okResult();
    }

}




