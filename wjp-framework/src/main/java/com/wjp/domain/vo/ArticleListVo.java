package com.wjp.domain.vo;


import com.wjp.entity.ArticleList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListVo {
    private Integer total;
    private List<ArticleList> rows;
}
