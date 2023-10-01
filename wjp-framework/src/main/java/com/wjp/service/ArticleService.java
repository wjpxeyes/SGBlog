package com.wjp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjp.domain.ResponseResult;
import com.wjp.domain.vo.AdminArticleInfoVo;
import com.wjp.entity.Article;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_article(文章表)】的数据库操作Service
 * @createDate 2023-09-13 10:11:21
 */


public interface ArticleService extends IService<Article> {

    ResponseResult getHotArticleList();

    ResponseResult getArticleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticle(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult getArticleList(Integer pageNum, Integer pageSize, String title, String summary);

    ResponseResult getArticleInfo(Long id);

    ResponseResult updateArticle(AdminArticleInfoVo articleInfoVo);

    ResponseResult deleteArticle(Long id);
}
