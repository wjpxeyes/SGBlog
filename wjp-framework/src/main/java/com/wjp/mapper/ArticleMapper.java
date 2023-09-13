package com.wjp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjp.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_article(文章表)】的数据库操作Mapper
 * @createDate 2023-09-13 10:11:21
 * @Entity com.wjp.domain.Article
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}




