package com.wjp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.constants.SystemConstants;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.dto.ArticleDto;
import com.wjp.domain.vo.*;
import com.wjp.entity.Article;
import com.wjp.entity.ArticleList;
import com.wjp.mapper.ArticleMapper;
import com.wjp.service.ArticleService;
import com.wjp.util.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 修改了artilceListVo，如果有问题，再改回去
     */
    @Override
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, Long categoryId) {
        if (categoryId == 0) {
            List<ArticleList> articleList = articleMapper.getArticleList(pageNum - 1, pageSize, 0L);
            ListVo<ArticleList> articleListVo = new ListVo<>((long) articleList.size(), articleList);
            return ResponseResult.okResult(articleListVo);
        }
        List<ArticleList> articleList = articleMapper.getArticleList(pageNum - 1, pageSize, categoryId);
        ListVo<ArticleList> articleListVo = new ListVo<>((long) articleList.size(), articleList);
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

    @Override
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, String title, String summary) {
        Page<Article> articlePage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(title), Article::getTitle, title)
                .like(StrUtil.isNotBlank(summary), Article::getSummary, summary)
                .eq(Article::getDelFlag, 0);
        page(articlePage, wrapper);
        List<Article> records = articlePage.getRecords();
        List<AdminArticleVo> articleVos = BeanCopyUtil.copyBeanList(records, AdminArticleVo.class);
        ListVo<AdminArticleVo> articleListVo = new ListVo<>(articlePage.getTotal(), articleVos);

        return ResponseResult.okResult(articleListVo);
    }


    @Override
    public ResponseResult getArticleInfo(Long id) {
        Article article = getById(id);
        AdminArticleInfoVo articleInfoVo = BeanCopyUtil.copyBean(article, AdminArticleInfoVo.class);
        articleInfoVo.setTags(articleMapper.getArticleTags(id));
        return ResponseResult.okResult(articleInfoVo);
    }


    //TODO 存在不能改标签的问题，建议不要改，不然系统太复杂，之后重写可以优化一下表结构
    @Override
    public ResponseResult updateArticle(AdminArticleInfoVo articleInfoVo) {
        Article article = BeanCopyUtil.copyBean(articleInfoVo, Article.class);
        saveOrUpdate(article);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteArticle(Long id) {
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult addArticle(ArticleDto articleDto) {
        Article article = BeanCopyUtil.copyBean(articleDto, Article.class);
        save(article);
        Long id = article.getId();
        articleMapper.addArticleTags(id, articleDto.getTags());
        return ResponseResult.okResult();
    }

}




