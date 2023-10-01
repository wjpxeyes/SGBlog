package com.wjp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjp.domain.vo.ArticleVo;
import com.wjp.entity.Article;
import com.wjp.entity.ArticleList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_article(文章表)】的数据库操作Mapper
 * @createDate 2023-09-13 10:11:21
 * @Entity com.wjp.domain.Article
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    List<ArticleList> getArticleList(@Param("page") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("categoryId") Long categoryId);

    ArticleVo getArticle(@Param("id") Long id);

    List<String> getArticleTags(@Param("id") Long id);
}




