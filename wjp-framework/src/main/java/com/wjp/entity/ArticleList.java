package com.wjp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleList {
    private String categoryName;
    private Date createTime;
    private Long id;
    private String summary;
    private String thumbnail;
    private String title;
    private Integer viewCount;
}
