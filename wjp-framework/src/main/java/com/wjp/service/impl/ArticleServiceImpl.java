package com.wjp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjp.entity.Article;
import com.wjp.mapper.ArticleMapper;
import com.wjp.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * @author wangjingpeng
 * @description 针对表【wjp_article(文章表)】的数据库操作Service实现
 * @createDate 2023-09-13 10:11:21
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

}




